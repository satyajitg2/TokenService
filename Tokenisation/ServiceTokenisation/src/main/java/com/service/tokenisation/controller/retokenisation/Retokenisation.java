package com.service.tokenisation.controller.retokenisation;

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
import com.service.tokenisation.service.RetokenisationService;
import com.service.tokenisation.utility.CommonUtility;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.utility.SSLParser;
import com.service.tokenisation.utility.Transformer;
import com.service.tokenisation.model.ResponseData;
import com.service.tokenisation.model.RetokeniseRequest;
import com.service.tokenisation.model.RetokeniseResponse;
import com.service.tokenisation.model.Status;

/**
 * Retokenisation class This class is entry point for Re- Tokenisation web service call
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
@Path("/tokenservice")
public class Retokenisation {

	private static final Logger LOG = Logger.getLogger(Retokenisation.class);
	private RetokenisationController retokenisationController = new RetokenisationController();
	private RetokenisationService retokenisationService = new RetokenisationService();
	private StringBuilder emptyMessage = new StringBuilder();
	private CommonUtility commonUtility = new CommonUtility();
	
	@Context      
    private HttpServletRequest request;
    
    @Context
    public void setHttpServletRequest(HttpServletRequest request ) {
        this.request = request;
    }

	/**
	 * This method is entry point for Re-Tokenisation Service request
	 * 
	 * @param jsonMessage
	 *            :Re Tokenisation Request JSON message
	 * @return javax.ws.rs.core.Response:Re-Tokenisation Service JSON Response with status
	 */
	@POST
	@Path("/retokenise")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retokenMain(String jsonMessage) throws TokenException {

		LOG.info(Constants.START_RETOKENISATION);
		LOG.debug("Invoking retokenMain");
		String resultJsonString = null;
		boolean authorised = true;
		RetokeniseRequest retokeniseRequest = new RetokeniseRequest();
		Status statusObj = new Status();
		String systemName = "";

		/** Check for Authentication */
		String authenticationReq = HibernateUtil.getAuthenticationReq();
		if (authenticationReq.equalsIgnoreCase(Constants.YES)) {
			SSLParser sslParser = new SSLParser();
			try{
			systemName = sslParser.verifySSL(request);
			} catch (Exception e) {
				LOG.error(Constants.EXCEPTION, e);
				RetokeniseResponse resp = new RetokeniseResponse();
				Status status = new Status();
				statusObj.setErrorCode(Constants.API_NOAUTH);
				statusObj.setStatus(Constants.FAILED);
				statusObj.setReason(Constants.NOT_AUTHENTICATION_REASON);
				resp.setStatus(status);
				resultJsonString = Transformer.toJSonStringRetokenise(resp);
				LOG.info(Constants.EXIT_RETOKENISATION);
				return Response.status(403).entity(resultJsonString).build();
			}
		}

			/** Check for Valid JSON Format */
			try {
				retokeniseRequest = Transformer.toJavaObjectForRetokenise(jsonMessage);
			} catch (Exception e) {
				LOG.error("Exception: ", e);
				RetokeniseResponse responseDataError = new RetokeniseResponse();
				responseDataError = getJSONErrorResponseToReturn(statusObj);
				resultJsonString = Transformer.toJSonStringRetokenise(responseDataError);
				LOG.info(Constants.EXIT_RETOKENISATION);
				return Response.status(400).entity(resultJsonString).build();
			}

			/** Check for empty fields in Request JSON */
			if (!checkForEmptyFields(retokeniseRequest)) {
				RetokeniseResponse responseDataError = new RetokeniseResponse();
				responseDataError = getEmptyResponseToReturn(statusObj);
				resultJsonString = Transformer.toJSonStringRetokenise(responseDataError);
				LOG.info(Constants.EXIT_RETOKENISATION);
				return Response.status(422).entity(resultJsonString).build();
			}
			/** Check if Source and Target domains are same in Request JSON */
			if (retokeniseRequest.getSourceDomain().equalsIgnoreCase(retokeniseRequest.getTargetDomain())) {
				RetokeniseResponse resp = new RetokeniseResponse();
				Status status = new Status();
				status.setStatus(Constants.FAILED);
				status.setErrorCode(Constants.VALIDATION_FAILED);
				status.setReason("Source and Target Domain is same");
				resp.setStatus(status);
				resultJsonString = Transformer.toJSonStringRetokenise(resp);
				LOG.info(Constants.EXIT_RETOKENISATION);
				return Response.status(400).entity(resultJsonString).build();
			}
			/** Check if data list is empty in Request JSON */
			if (retokeniseRequest.getData() == null || retokeniseRequest.getData().isEmpty()) {
				RetokeniseResponse resp = new RetokeniseResponse();
				Status status = new Status();
				status.setStatus(Constants.FAILED);
				status.setErrorCode(Constants.VALIDATION_FAILED);
				status.setReason("Data list is empty");
				resp.setStatus(status);
				resultJsonString = Transformer.toJSonStringRetokenise(resp);
				LOG.info(Constants.EXIT_RETOKENISATION);
				return Response.status(400).entity(resultJsonString).build();
			}

			/** Check for authorisation */
			try {
				if (authenticationReq.equalsIgnoreCase(Constants.YES)) {
					authorised = retokenisationService.isAuthorisedCallingSystem(retokeniseRequest, systemName);
				}else{
					authorised = retokenisationService.isAuthorised(retokeniseRequest);
					}
			}catch (TokenException e) {
				LOG.error(Constants.EXCEPTION, e);
				RetokeniseResponse resp = new RetokeniseResponse();
				Status statusRes = new Status();
				if (e.toString().contains(Constants.DB_UNAVAILABLE)) {
					statusRes.setErrorCode(Constants.DB_UNAVAILABLE);
					statusRes.setStatus(Constants.FAILED);
				} else {
					statusRes.setErrorCode(Constants.SYSTEM_ERROR);
					statusRes.setStatus(Constants.FAILED);
				}
				resp.setStatus(statusRes);
				resultJsonString = Transformer.toJSonStringRetokenise(resp);
				LOG.info(Constants.EXIT_RETOKENISATION);
				return Response.status(500).entity(resultJsonString).build();
			}
			
			if (authorised) {
				try {
					resultJsonString = retokenisationController.processReTokenisation(retokeniseRequest);
				} catch (TokenException e) {
					LOG.error("Exception: ", e);
					ResponseData responseDataError = new ResponseData();
					if (e.toString().contains(Constants.VAULT_ERROR)) {
						statusObj.setErrorCode(Constants.VAULT_ERROR);
						statusObj.setStatus(Constants.FAILED);
					} else if (e.toString().contains(Constants.DB_UNAVAILABLE)) {
						statusObj.setErrorCode(Constants.DB_UNAVAILABLE);
						statusObj.setStatus(Constants.FAILED);
					} else if (e.toString().contains(Constants.API_NOAUTH)) {
						statusObj.setErrorCode(Constants.API_NOAUTH);
						statusObj.setStatus(Constants.FAILED);
						responseDataError.setStatus(statusObj);
						resultJsonString = Transformer.toJsonString(responseDataError);
						LOG.info(Constants.EXIT_RETOKENISATION);
						return Response.status(403).entity(resultJsonString).build();
					} else {
						statusObj.setErrorCode(Constants.SYSTEM_ERROR);
						statusObj.setStatus(Constants.FAILED);
					}
					responseDataError.setStatus(statusObj);
					resultJsonString = Transformer.toJsonString(responseDataError);
					LOG.info(Constants.EXIT_RETOKENISATION);
					return Response.status(500).entity(resultJsonString).build();
				}
				if (resultJsonString.contains(Constants.VALIDATION_FAILED)) {
					LOG.info(Constants.EXIT_RETOKENISATION);
					return Response.status(422).entity(resultJsonString).build();
				} else {
					LOG.info(Constants.EXIT_RETOKENISATION);
					return Response.status(200).entity(resultJsonString).build();
				}
			} else {
				RetokeniseResponse resp = new RetokeniseResponse();
				Status statusRes = new Status();
				statusRes.setStatus(Constants.FAILED);
				statusRes.setErrorCode(Constants.API_NOAUTH);
				statusRes.setReason(Constants.NOT_AUTH_REASON);
				resp.setStatus(statusRes);
				resultJsonString = Transformer.toJSonStringRetokenise(resp);
				LOG.info(Constants.EXIT_RETOKENISATION);
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
	public boolean checkForEmptyFields(RetokeniseRequest requestData) {
		LOG.debug("Invoking checkForEmptyFields");
		commonUtility.checkIfEmpty(requestData.getSourceSystemName(), "SourceSystemName", emptyMessage);
		commonUtility.checkIfEmpty(requestData.getSourceDomain(), "SourceDomain", emptyMessage);
		commonUtility.checkIfEmpty(requestData.getTargetDomain(), "TargetDomain", emptyMessage);
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
	public RetokeniseResponse getJSONErrorResponseToReturn(Status statusObj) {
		LOG.debug("Invoking getJSONErrorResponseToReturn");
		RetokeniseResponse responseDataError = new RetokeniseResponse();
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
	public RetokeniseResponse getEmptyResponseToReturn(Status statusObj) {
		LOG.debug(" Invoking getEmptyResponseToReturn");
		RetokeniseResponse responseDataError = new RetokeniseResponse();
		statusObj.setErrorCode(Constants.VALIDATION_FAILED);
		statusObj.setStatus(Constants.FAILED);
		statusObj.setReason(Constants.MISSING_MANDATORY_FIELDS + emptyMessage.toString());
		responseDataError.setStatus(statusObj);
		return responseDataError;
	}

}
