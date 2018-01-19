package com.service.tokenisation.controller.detokenisation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.Status;
import com.service.tokenisation.service.DetokenisationService;
import com.service.tokenisation.utility.CommonUtility;
import com.service.tokenisation.utility.Constants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * DetokenisationController class This class will perform operation on input request for Detokenisation
 * 
 * @author: Satyajit Singh
 * @Edited: Satyajit Modified for new Request-Response
 * @version: 2
 */
public class DetokenisationController {

	private static final Logger LOG = Logger.getLogger(DetokenisationController.class);

	private DetokenisationService detokenisationService = new DetokenisationService();
	private Map<String, String> tokenSet = new LinkedHashMap<String, String>();
	private CommonUtility commonUtility = new CommonUtility();
	private int check = 0;

	/**
	 * This method is responsible for processing detokenisation request
	 * 
	 * @param requestData
	 *            : Detoken Request Data Object
	 * @return String: de-okenisation response string
	 * @exception TokenException
	 */
	public String processDetokenisation(String jsonMessage, String domainName) throws TokenException {
		LOG.debug("Invoking processDetokenisation");

		boolean noAuthFlag = false;
		getTokenListInteger(jsonMessage);
		getTokenListString(jsonMessage);
		getTokenListDateTime(jsonMessage);

		List<Long> intTokenList = new ArrayList<Long>();
		List<String> strTokenList = new ArrayList<String>();
		List<String> dateTokenList = new ArrayList<String>();

		for (Map.Entry<String, String> entry : tokenSet.entrySet()) {
			switch (entry.getValue()) {
			case Constants.DATA_TYPE_INTEGER:
				intTokenList.add(Long.parseLong(entry.getKey()));
				break;
			case Constants.DATA_TYPE_STRING:
				strTokenList.add(entry.getKey());
				break;
			case Constants.DATA_TYPE_DATETIME:
				dateTokenList.add(entry.getKey());
				break;
			default:
				break;
			}

		}
		if (!intTokenList.isEmpty()) {
			noAuthFlag = detokenisationService.getCsdFromVault(tokenSet, Constants.TABLE_INTEGER, intTokenList,
					domainName);
			changeStatus(noAuthFlag);
			noAuthFlag = detokenisationService.getCsdFromMCOVault(tokenSet, Constants.TABLE_INTEGER, intTokenList,
					domainName);
			changeStatus(noAuthFlag);
		}
		if (!strTokenList.isEmpty()) {
			noAuthFlag = detokenisationService.getCsdFromVault(tokenSet, Constants.TABLE_STRING, strTokenList,
					domainName);
			changeStatus(noAuthFlag);
			noAuthFlag = detokenisationService.getCsdFromMCOVault(tokenSet, Constants.TABLE_STRING, strTokenList,
					domainName);
			changeStatus(noAuthFlag);
		}
		if (!dateTokenList.isEmpty()) {
			noAuthFlag = detokenisationService.getCsdFromVault(tokenSet, Constants.TABLE_DATETIME, dateTokenList,
					domainName);
			changeStatus(noAuthFlag);
			noAuthFlag = detokenisationService.getCsdFromMCOVault(tokenSet, Constants.TABLE_DATETIME, dateTokenList,
					domainName);
			changeStatus(noAuthFlag);
		}
		boolean notFoundFlag = changeRespone();
		int statusCheck = 0;
		if (notFoundFlag) {
			statusCheck = 1;
		}
		if (check > 0) {
			statusCheck = 2;
		}
		if (notFoundFlag == true && check > 0) {
			statusCheck = 4;
		}

		Status status = new Status();
		status = commonUtility.getStatusForResponse(status, statusCheck);
		String append = createResponseString(status);
		String message = repalceTokenWithCSD(jsonMessage, tokenSet);
		String finalResponse = fetchFromRequest(message);
		return append + finalResponse;
	}

	private void changeStatus(boolean noAuthFlag) {
		if (noAuthFlag) {
			check++;
		}
	}

	/**
	 * This method will fetch Integer tokens from request and add to tokenmap
	 * 
	 * @param jsonString
	 *            : Detoken request
	 * @return void
	 * @throws TokenException
	 *
	 */
	private void getTokenListInteger(String jsonString) throws TokenException {
		LOG.debug("Invoking getTokenListInteger");
		try {
			Set<String> tokens = new HashSet<String>();
			int tokenLength = 19;
			String regExp = Constants.INT_TKN_REG_EXP;
			Pattern p = Pattern.compile(regExp);
			Matcher matcher = p.matcher(jsonString);
			while (matcher.find()) {
				parseToken(tokens, jsonString, matcher.start(), matcher.start() + tokenLength);
			}
			for (String string : tokens) {
				tokenSet.put(string, Constants.DATA_TYPE_INTEGER);
			}
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

	/**
	 * This method will fetch String tokens from request and add to tokenmap
	 * 
	 * @param jsonString
	 *            : Detoken request
	 * @return void
	 * @throws TokenException
	 *
	 */
	private void getTokenListString(String jsonString) throws TokenException {
		LOG.debug("Invoking getTokenListString");
		try {
			Set<String> tokens = new HashSet<String>();
			int tokenLength = 10;
			String regExp = Constants.STR_TKN_REG_EXP;
			Pattern p = Pattern.compile(regExp);
			Matcher matcher = p.matcher(jsonString);
			while (matcher.find()) {
				parseToken(tokens, jsonString, matcher.start(), matcher.start() + tokenLength);
			}
			for (String string : tokens) {
				tokenSet.put(string, Constants.DATA_TYPE_STRING);
			}
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

	/**
	 * This method will fetch DateTime tokens from request and add to tokenmap
	 * 
	 * @param jsonString
	 *            : Detoken request
	 * @return void
	 * @throws TokenException
	 *
	 */
	private void getTokenListDateTime(String jsonString) throws TokenException {
		LOG.debug("Invoking getTokenListDateTime");
		try {
			Set<String> tokens = new HashSet<String>();
			int tokenLength = 23;
			String regExp = Constants.DATETIME_TKN_REG_EXP;
			Pattern p = Pattern.compile(regExp);
			Matcher matcher = p.matcher(jsonString); // get a matcher object
			while (matcher.find()) {
				parseToken(tokens, jsonString, matcher.start(), matcher.start() + tokenLength);
			}
			for (String string : tokens) {
				tokenSet.put(string, Constants.DATA_TYPE_DATETIME);
			}
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

	private Set<String> parseToken(Set<String> tokens, String jsonString, int startIndex, int endIndex) {
		LOG.debug("Invoking parseToken");
		if ((jsonString.charAt(startIndex - 1) == '"') && (jsonString.charAt(endIndex) == '"')) {
			tokens.add(jsonString.substring(startIndex, endIndex));
		}
		return tokens;
	}

	private String repalceTokenWithCSD(String jsonString, Map<String, String> tokenset) {
		LOG.debug("Invoking repalceTokenWithCSD");
		for (Map.Entry<String, String> entrySet : tokenset.entrySet()) {
			jsonString = jsonString.replace(entrySet.getKey(), entrySet.getValue());
		}
		return jsonString;
	}

	/**
	 * This method will place non tokens as it is for response
	 * 
	 * @return void
	 */
	private boolean changeRespone() {
		LOG.debug("Invoking changeRespone");
		boolean notFound = false;
		for (Map.Entry<String, String> entry : tokenSet.entrySet()) {
			switch (entry.getValue()) {
			case Constants.DATA_TYPE_INTEGER:
				tokenSet.put(entry.getKey(), entry.getKey());
				notFound = true;
				break;
			case Constants.DATA_TYPE_STRING:
				tokenSet.put(entry.getKey(), entry.getKey());
				notFound = true;
				break;
			case Constants.DATA_TYPE_DATETIME:
				tokenSet.put(entry.getKey(), entry.getKey());
				notFound = true;
				break;
			default:
				break;
			}
		}
		return notFound;
	}

	/**
	 * This method will create response message for detokenisation
	 * 
	 * @param status
	 *            : Status object to be return
	 * @return String: Response string
	 * @throws TokenException
	 *
	 */
	public static String createResponseString(Status status) throws TokenException {
		LOG.debug("Invoking createResponseString");
		ObjectMapper mapper = new ObjectMapper();
		String responseStr = "";
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			responseStr = mapper.writeValueAsString(status);
			String startString = "{" + "\"status\"" + ":";
			StringBuilder sb = new StringBuilder();
			sb.append(startString);
			sb.append(responseStr);
			return sb.toString();
		} catch (JsonProcessingException e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}

	}

	/**
	 * This method will fetch detoken object from detokenisation request
	 * 
	 * @param String
	 *            :request string
	 * @return String: Response string
	 * @throws TokenException
	 *
	 */
	private String fetchFromRequest(String req) throws TokenException {
		LOG.debug("Invoking fetchFromRequest");
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder sb = null;
		try {
			sb = new StringBuilder();
			sb.append(",\"detokenObject\"");
			JsonNode root = mapper.readTree(req);
			root.elements();
			JsonNode dObject = root.path("detokenObject");
			sb.append(":" + dObject.toString());
			sb.append("}");
			return sb.toString();
		} catch (IOException e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}

	}

}
