package com.service.tokenisation.utility;

import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.DetokeniseRequestData;
import com.service.tokenisation.model.DetokeniseResponse;
import com.service.tokenisation.model.RequestData;
import com.service.tokenisation.model.ResponseData;
import com.service.tokenisation.model.RetokeniseRequest;
import com.service.tokenisation.model.RetokeniseResponse;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Transformer class :This is common class for Transformations from JAVA to JSON and JSON to JAVA objects
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class Transformer {

	private static final Logger LOG = Logger.getLogger(Transformer.class);
	private Transformer() {
		super();
	}

	/** Convert Tokenisation ResponseData java object to JSON string*/
	public static String toJsonString(ResponseData responseData) throws TokenException {
		LOG.debug(" Invoking toJsonString");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(responseData);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return jsonInString;
	}

	/**
	 * This method will convert Tokenise Request string to object
	 * 
	 * @param jsonString
	 *            : Tokenise Request String
	 * @return RequestData
	 * @exception TokenException
	 */
	public static RequestData toJavaObject(String jsonString) throws TokenException {
		LOG.debug("Invoking toJavaObject");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.STRICT_DUPLICATE_DETECTION, true);
		RequestData requestData;
		try {
			requestData = mapper.readValue(jsonString, RequestData.class);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.INVALID_REQUEST);
		}
		return requestData;
	}
	/**
	 * This method will convert Detokenise Request string to object
	 * 
	 * @param jsonString
	 *            : Detokenise Request String
	 * @return DetokeniseRequestData
	 * @exception TokenException
	 */
	public static DetokeniseRequestData toJavaObjectForDetokenise(String jsonString) throws TokenException {
		LOG.info("Invoking toJavaObjectForDetokenise");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.STRICT_DUPLICATE_DETECTION, true);
		DetokeniseRequestData requestData;
		try {
			requestData = mapper.readValue(jsonString, DetokeniseRequestData.class);
			if(requestData.getEncryption() != null && requestData.getDomain() != null && requestData.getEncryption().getAlg() != null && requestData.getEncryption().getEncryption_key() != null){
				return requestData;
			}else{
				throw new TokenException(Constants.INVALID_REQUEST);
			}
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.INVALID_REQUEST);
		}
		

	}

	/**
	 * This method will convert DetokeniseeResponse object to String
	 * 
	 * @param DetokeniseeResponse
	 *            : Detokenisee Response object
	 * @return String: String formed from java Object
	 * @exception TokenException
	 */
	public static String toJasonStringDetokenise(DetokeniseResponse responseData) throws TokenException {
		LOG.debug("Invoking toJasonStringDetokenise");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(responseData);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return jsonInString;

	}

	/**
	 * This method will convert Retokenise Request string to RetokeniseRequest object
	 * 
	 * @param jsonString
	 *            : Retokenise Request String
	 * @return RetokeniseRequest: RetokeniseRequestObject
	 * @exception TokenException
	 */
	public static RetokeniseRequest toJavaObjectForRetokenise(String jsonString) throws TokenException {
		LOG.debug("Invoking toJSonStringRetokenise");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.STRICT_DUPLICATE_DETECTION, true);
		RetokeniseRequest requestData;
		try {
			requestData = mapper.readValue(jsonString, RetokeniseRequest.class);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.INVALID_REQUEST);
		}
		return requestData;
	}

	/**
	 * This method will convert RetokeniseResponse object to String
	 * 
	 * @param retokeniseResponse
	 *            : Retokenise Response object
	 * @return String: String formed from java Object
	 * @exception TokenException
	 */
	public static String toJSonStringRetokenise(RetokeniseResponse retokeniseResponse) throws TokenException {
		LOG.debug("Invoking toJSonStringRetokenise");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(retokeniseResponse);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return jsonInString;
	}
}
