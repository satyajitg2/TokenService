package com.service.tokenisation.controller.detokenisation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.service.tokenisation.encryption.EncryptService;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.service.DetokenisationService;
import com.service.tokenisation.utility.CommonUtility;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.utility.SSLParser;
import com.service.tokenisation.utility.Transformer;
import com.service.tokenisation.model.DetokeniseRequestData;
import com.service.tokenisation.model.DetokeniseResponse;
import com.service.tokenisation.model.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Detokenisation class This class is entry point for detokenisation web service call
 * 
 * @author: Satyajit Singh
 * @Edited: Satyajit: Modified for new Request-Response
 * @version: 3
 * 
 */

@Path("/tokenservice")
public class Detokenisation {
	private static final Logger LOG = Logger.getLogger(Detokenisation.class);

	private StringBuilder emptyMessageString = new StringBuilder();
	private EncryptService encryptService = new EncryptService();
	private CommonUtility commonUtility = new CommonUtility();
	private DetokenisationController detokeniseController = new DetokenisationController();
	private DetokenisationService detokeniseService = new DetokenisationService();
	private String domainName;

	@Context
	private HttpServletRequest request;

	@Context
	public void setHttpServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@POST
	@Path("/detokenise")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response detokenisationMain(String jsonMessage) throws TokenException {
		LOG.info(Constants.START_DETOKENISATION);
		LOG.debug("Invoking detokenisationMain");
		boolean authorised = true;
		String detokeniseStringResponse = null;
		String encryptedDetokenResponse = null;
		DetokeniseRequestData detokeniseRequestData = new DetokeniseRequestData();
		Status statusObj = new Status();
		String systemName = "";

		/** Check if Authentication needs to be done or not */
		String authenticationReq = HibernateUtil.getAuthenticationReq();
		if (authenticationReq.equalsIgnoreCase(Constants.YES)) {
			SSLParser sslParser = new SSLParser();
			try {
				systemName = sslParser.verifySSL(request);
			} catch (Exception e) {
				LOG.error(Constants.EXCEPTION, e);
				DetokeniseResponse responseDataError = new DetokeniseResponse();
				statusObj.setErrorCode(Constants.API_NOAUTH);
				statusObj.setStatus(Constants.FAILED);
				statusObj.setReason(Constants.NOT_AUTHENTICATION_REASON);
				responseDataError.setStatus(statusObj);
				detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
				LOG.info(Constants.EXIT_DETOKENISATION);
				return Response.status(403).entity(detokeniseStringResponse).build();
			}
		}

		/** If Authenticate */
		try {
			detokeniseRequestData = Transformer.toJavaObjectForDetokenise(jsonMessage);
			domainName = detokeniseRequestData.getDomain();
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			DetokeniseResponse responseDataError = new DetokeniseResponse();
			responseDataError = getJSONErrorResponseToReturn(statusObj);
			detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
			LOG.info(Constants.EXIT_DETOKENISATION);
			return Response.status(400).entity(detokeniseStringResponse).build();
		}
		/** Check if detoken object in Request JSON */
		if (!detokenObjectPresent(jsonMessage)) {
			DetokeniseResponse responseDataError = new DetokeniseResponse();
			Status status = new Status();
			status.setStatus(Constants.FAILED);
			status.setErrorCode(Constants.INVALID_REQUEST);
			status.setReason("Detoken Object is missing");
			responseDataError.setStatus(status);
			detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
			LOG.info(Constants.EXIT_DETOKENISATION);
			return Response.status(422).entity(detokeniseStringResponse).build();
		}
		/** Check for empty fields in Request JSON */
		if (!checkForEmptyFields(detokeniseRequestData)) {
			DetokeniseResponse responseDataError = new DetokeniseResponse();
			responseDataError = getEmptyResponseToReturn(statusObj);
			detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
			LOG.info(Constants.EXIT_DETOKENISATION);
			return Response.status(422).entity(detokeniseStringResponse).build();
		}

		/** Validating and authorizing */
		try {
			if (authenticationReq.equalsIgnoreCase(Constants.YES)) {
				authorised = detokeniseService.isAuthorisedCallingSystem(detokeniseRequestData, systemName);
			} else {
				authorised = detokeniseService.isAuthorised(detokeniseRequestData);
			}
		} catch (TokenException e) {
			LOG.error(Constants.EXCEPTION, e);
			DetokeniseResponse responseDataError = new DetokeniseResponse();
			if (e.toString().contains(Constants.DB_UNAVAILABLE)) {
				statusObj.setErrorCode(Constants.DB_UNAVAILABLE);
				statusObj.setStatus(Constants.FAILED);
			} else {
				statusObj.setErrorCode(Constants.SYSTEM_ERROR);
				statusObj.setStatus(Constants.FAILED);
			}
			responseDataError.setStatus(statusObj);
			detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
			LOG.info(Constants.EXIT_DETOKENISATION);
			return Response.status(500).entity(detokeniseStringResponse).build();
		}

		/** If authorized */
		if (authorised) {
			try {
				detokeniseStringResponse = detokeniseController.processDetokenisation(jsonMessage, domainName);
			} catch (TokenException e) {
				LOG.error(Constants.EXCEPTION, e);
				DetokeniseResponse responseDataError = new DetokeniseResponse();
				statusObj = commonUtility.getStatus(statusObj, e.toString());
				responseDataError.setStatus(statusObj);
				detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
				LOG.info(Constants.EXIT_DETOKENISATION);
				return Response.status(500).entity(detokeniseStringResponse).build();
			}

			try {
				encryptedDetokenResponse = encryptService.getEncryptedDetokenResponseString(detokeniseStringResponse);
			} catch (TokenException e) {
				LOG.error(Constants.EXCEPTION, e);
				DetokeniseResponse responseDataError = new DetokeniseResponse();
				statusObj.setErrorCode(Constants.SYSTEM_ERROR);
				statusObj.setStatus(Constants.FAILED);
				responseDataError.setStatus(statusObj);
				detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
				LOG.info(Constants.EXIT_DETOKENISATION);
				return Response.status(500).entity(detokeniseStringResponse).build();
			}

			if (encryptedDetokenResponse.contains(Constants.VALIDATION_FAILED)) {
				LOG.info(Constants.EXIT_DETOKENISATION);
				return Response.status(422).entity(encryptedDetokenResponse).build();
			} else {
				LOG.info(Constants.EXIT_DETOKENISATION);
				return Response.status(200).entity(encryptedDetokenResponse).build();
			}
		} else {
			DetokeniseResponse responseDataError = new DetokeniseResponse();
			statusObj.setErrorCode(Constants.API_NOAUTH);
			statusObj.setStatus(Constants.FAILED);
			statusObj.setReason(Constants.NOT_AUTH_REASON);
			responseDataError.setStatus(statusObj);
			detokeniseStringResponse = Transformer.toJasonStringDetokenise(responseDataError);
			LOG.info(Constants.EXIT_DETOKENISATION);
			return Response.status(403).entity(detokeniseStringResponse).build();
		}
	}

	/**
	 * This method will check if detoken object is present in request or not
	 * 
	 * @param requestData
	 *            : Detoken Request
	 * @return boolean: True:- If found, False:- If not found
	 *
	 */
	private boolean detokenObjectPresent(String jsonMessage) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root;
			root = mapper.readTree(jsonMessage);
			root.elements();
			JsonNode dObject = root.path("detokenObject");
			if (dObject.isMissingNode()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			return false;
		}

	}

	/**
	 * This method will check if fields in Request are empty or null and create string with all empty/null fields
	 * 
	 * @param requestData
	 *            : Detoken Request Data Object
	 * @return boolean: True:- If any null or empty field is found, False:- If no empty/null value found
	 *
	 */
	public boolean checkForEmptyFields(DetokeniseRequestData detokeniseRequestData) {
		LOG.debug("Invoking checkForEmptyFields");

		commonUtility.checkIfEmpty(detokeniseRequestData.getEncryption().getAlg(), "Encryption Alg ", emptyMessageString);
		commonUtility.checkIfEmpty(detokeniseRequestData.getEncryption().getEncryption_key(), "Encryption Key",
				emptyMessageString);
		commonUtility.checkIfEmpty(detokeniseRequestData.getDomain(), "Domain", emptyMessageString);
		if (emptyMessageString.length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * Method will return error response if JSON is invalid
	 * 
	 * @param statusObj
	 *            : Status class object
	 * @return ResponseData Object: Response object to be returned from service
	 */
	public DetokeniseResponse getJSONErrorResponseToReturn(Status statusObj) {
		LOG.debug("Invoking getJSONErrorResponseToReturn");
		DetokeniseResponse responseDataError = new DetokeniseResponse();
		statusObj.setErrorCode(Constants.INVALID_REQUEST);
		statusObj.setStatus(Constants.FAILED);
		statusObj.setReason(Constants.NOT_VALID_JASON_REASON);
		responseDataError.setStatus(statusObj);
		return responseDataError;
	}

	/**
	 * Method will return error response if any field is empty
	 * 
	 * @param statusObj
	 *            : Status class object
	 * @return ResponseData Object: Response object to be returned from service
	 */
	public DetokeniseResponse getEmptyResponseToReturn(Status statusObj) {
		LOG.debug("Invoking getEmptyResponseToReturn");
		DetokeniseResponse responseDataError = new DetokeniseResponse();
		statusObj.setErrorCode(Constants.VALIDATION_FAILED);
		statusObj.setStatus(Constants.FAILED);
		statusObj.setReason(Constants.MISSING_MANDATORY_FIELDS + emptyMessageString.toString());
		responseDataError.setStatus(statusObj);
		return responseDataError;
	}

}
