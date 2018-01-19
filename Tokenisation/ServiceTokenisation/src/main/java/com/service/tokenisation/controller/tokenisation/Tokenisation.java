package com.service.tokenisation.controller.tokenisation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.service.TokenisationService;
import com.service.tokenisation.utility.CommonUtility;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.utility.SSLParser;
import com.service.tokenisation.utility.Transformer;
import com.service.tokenisation.model.RequestData;
import com.service.tokenisation.model.ResponseData;
import com.service.tokenisation.model.RetokeniseResponse;
import com.service.tokenisation.model.Status;

/**
 * Tokenisation class This class is entry point for tokenisation web service call
 * 
 * @author: Satyajit Singh
 * @version: 2 Modified For Performance Improvement 20 April 2016
 */
@Path("/tokenservice")
public class Tokenisation {

	private static final Logger LOG = Logger.getLogger(Tokenisation.class);
	private TokenisationController tokenisationController = new TokenisationController();
	private TokenisationService tokenisationService = new TokenisationService();
	private StringBuilder emptyMessage = new StringBuilder();
	private CommonUtility commonUtility = new CommonUtility();

	@Context
	private HttpServletRequest request;

	@Context
	public void setHttpServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * This method is entry point for Tokenisation Service request
	 * 
	 * @param jsonMessage
	 *            : Tokenisation Request JSON message
	 * @return javax.ws.rs.core.Response:Tokenisation Service JSON Response with status
	 */
	@POST
	@Path("/tokenise")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response tokenMain(String jsonMessage) throws TokenException {
		LOG.info(Constants.START_TOKENISATION);
		LOG.debug("Invoking tokenMain");
		String resultJsonString = null;
		boolean authorised = false;
		RequestData requestData = new RequestData();
		boolean isAuthenticate = true;
		String systemName = "";
		Status statusObj = new Status();
		/** Authentication stub */

		String authenticationReq = HibernateUtil.getAuthenticationReq();
		if (authenticationReq.equalsIgnoreCase(Constants.YES)) {
			SSLParser sslParser = new SSLParser();
			try {
				systemName = sslParser.verifySSL(request);
			} catch (Exception e) {
				LOG.error(Constants.EXCEPTION, e);
				ResponseData responseDataError = new ResponseData();
				statusObj.setErrorCode(Constants.API_NOAUTH);
				statusObj.setStatus(Constants.FAILED);
				statusObj.setReason(Constants.NOT_AUTHENTICATION_REASON);
				responseDataError.setStatus(statusObj);
				resultJsonString = Transformer.toJsonString(responseDataError);
				LOG.info(Constants.EXIT_TOKENISATION);
				return Response.status(403).entity(resultJsonString).build();
			}
		}
		try {

			requestData = Transformer.toJavaObject(jsonMessage);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			ResponseData responseDataError = new ResponseData();
			responseDataError = getJSONErrorResponseToReturn(statusObj);
			resultJsonString = Transformer.toJsonString(responseDataError);
			return Response.status(400).entity(resultJsonString).build();
		}

		/** Check for empty fields in Request JSON */
		if (!checkForEmptyFields(requestData)) {
			ResponseData responseDataError = new ResponseData();
			responseDataError = getEmptyResponseToReturn(statusObj);
			resultJsonString = Transformer.toJsonString(responseDataError);
			LOG.info(Constants.EXIT_TOKENISATION);
			return Response.status(422).entity(resultJsonString).build();
		}
		/** Validating and authorizing using stored procedure */

		try {
			if (authenticationReq.equalsIgnoreCase(Constants.YES)) {
				authorised = tokenisationService.isAuthorised(requestData, systemName);
			} else {
				systemName = requestData.getSourceSystemName();
				authorised = tokenisationService.isAuthorised(requestData, systemName);
			}
		} catch (TokenException e) {
			LOG.error(Constants.EXCEPTION, e);
			ResponseData responseDataError = new ResponseData();
			if (e.toString().contains(Constants.DB_UNAVAILABLE)) {
				statusObj.setErrorCode(Constants.DB_UNAVAILABLE);
				statusObj.setStatus(Constants.FAILED);
			} else {
				statusObj.setErrorCode(Constants.SYSTEM_ERROR);
				statusObj.setStatus(Constants.FAILED);
			}
			responseDataError.setStatus(statusObj);
			resultJsonString = Transformer.toJsonString(responseDataError);
			LOG.info(Constants.EXIT_TOKENISATION);
			return Response.status(500).entity(resultJsonString).build();
		}
		/** Check if data list is empty in Request JSON */
		if (requestData.getCsds() == null || requestData.getCsds().isEmpty()) {
			RetokeniseResponse resp = new RetokeniseResponse();
			Status status = new Status();
			status.setStatus(Constants.FAILED);
			status.setErrorCode(Constants.INVALID_REQUEST);
			status.setReason("CSD List is empty");
			resp.setStatus(status);
			resultJsonString = Transformer.toJSonStringRetokenise(resp);
			LOG.info(Constants.EXIT_TOKENISATION);
			return Response.status(422).entity(resultJsonString).build();
		}
		if (authorised) {
			try {
				resultJsonString = tokenisationController.processTokenistion(requestData);
			} catch (TokenException e) {
				LOG.error(Constants.EXCEPTION, e);
				ResponseData responseDataError = new ResponseData();
				statusObj = commonUtility.getStatus(statusObj, e.toString());
				responseDataError.setStatus(statusObj);
				resultJsonString = Transformer.toJsonString(responseDataError);
				return Response.status(500).entity(resultJsonString).build();
			}
			if (resultJsonString.contains(Constants.VALIDATION_FAILED)) {
				LOG.info(Constants.EXIT_TOKENISATION);
				return Response.status(422).entity(resultJsonString).build();
			} else {
				LOG.info(Constants.EXIT_TOKENISATION);
				return Response.status(200).entity(resultJsonString).build();
			}
		} else {
			ResponseData responseDataError = new ResponseData();
			statusObj.setErrorCode(Constants.API_NOAUTH);
			statusObj.setStatus(Constants.FAILED);
			statusObj.setReason(Constants.NOT_AUTH_REASON);
			responseDataError.setStatus(statusObj);
			resultJsonString = Transformer.toJsonString(responseDataError);
			LOG.info(Constants.EXIT_TOKENISATION);
			return Response.status(403).entity(resultJsonString).build();
		}

	}

	/**
	 * This method will check if fields in Request are empty or null and create string with all empty/null fields
	 * 
	 * @param requestData
	 *            : Request Data Object
	 * @return boolean: True:- If any null or empty field is found, False:- If no empty/null value found
	 *
	 */
	public boolean checkForEmptyFields(RequestData requestData) {
		LOG.debug("Invoking checkForEmptyFields");

		commonUtility.checkIfEmpty(requestData.getSourceSystemName(), "SourceSystemName", emptyMessage);
		commonUtility.checkIfEmpty(requestData.getDomain(), "Domain", emptyMessage);
		commonUtility.checkIfEmpty(requestData.getOwningBusinessEntity(), "OwningBusinessEntity", emptyMessage);
		if (emptyMessage.length() > 0) {
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
	public ResponseData getJSONErrorResponseToReturn(Status statusObj) {
		LOG.debug("Invoking getJSONErrorResponseToReturn");
		ResponseData responseDataError = new ResponseData();
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
	public ResponseData getEmptyResponseToReturn(Status statusObj) {
		LOG.debug("Invoking getEmptyResponseToReturn");
		ResponseData responseDataError = new ResponseData();
		statusObj.setErrorCode(Constants.VALIDATION_FAILED);
		statusObj.setStatus(Constants.FAILED);
		statusObj.setReason(Constants.MISSING_MANDATORY_FIELDS + emptyMessage.toString());
		responseDataError.setStatus(statusObj);
		return responseDataError;
	}

}
