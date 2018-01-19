package com.service.tokenisation.controller.retokenisation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.service.tokenisation.service.RetokenisationService;
import com.service.tokenisation.utility.CommonUtility;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.utility.Transformer;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.RetokenReqData;
import com.service.tokenisation.model.RetokenRespData;
import com.service.tokenisation.model.RetokenisationHandler;
import com.service.tokenisation.model.RetokeniseRequest;
import com.service.tokenisation.model.RetokeniseResponse;
import com.service.tokenisation.model.Status;

/**
 * RetokenisationController class This class will perform operation on input request for Retokenisation
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class RetokenisationController {

	private static final Logger LOG = Logger.getLogger(RetokenisationController.class);

	private RetokenisationService retokenisationService = new RetokenisationService();
	private Map<String, RetokenisationHandler> tokenMap = new LinkedHashMap<String, RetokenisationHandler>();
	private boolean isNotToken = false;
	private StringBuilder emptyFieldMessage = new StringBuilder();
	private CommonUtility commonUtility = new CommonUtility();

	public String processReTokenisation(RetokeniseRequest retokeniseRequest) throws TokenException {

		LOG.info("Invoking processTokenistion");
		String resultJsonString = null;
		List<RetokenReqData> listDataReq = retokeniseRequest.getData();
		List<Long> intTokenList = new ArrayList<Long>();
		List<String> strTokenList = new ArrayList<String>();
		List<Timestamp> dateTokenList = new ArrayList<Timestamp>();
		boolean result = false;

		/** Authorized Business Entity List */
		List<String> authorisedBusinessEntities = retokenisationService.getAuthorisedBusinessEntities(
				retokeniseRequest.getSourceDomain(), retokeniseRequest.getTargetDomain());

		for (int i = 0; i < listDataReq.size(); i++) {
			result = isNullOrEmpty(listDataReq.get(i));
			if (result) {
				RetokeniseResponse retokenResponse = new RetokeniseResponse();
				Status status = new Status();
				status.setErrorCode(Constants.VALIDATION_FAILED);
				status.setReason(Constants.MISSING_MANDATORY_FIELDS + emptyFieldMessage.toString());
				status.setStatus(Constants.FAILED);
				retokenResponse.setStatus(status);
				resultJsonString = Transformer.toJSonStringRetokenise(retokenResponse);
				return resultJsonString;
			}
			getTokenList(listDataReq.get(i).getId(), listDataReq.get(i).getToken(),
					listDataReq.get(i).getTargetFieldName());
		}

		if (isNotToken) {
			RetokeniseResponse retokenResponse = new RetokeniseResponse();
			Status status = new Status();
			status.setErrorCode(Constants.VALIDATION_FAILED);
			status.setStatus(Constants.FAILED);
			status.setReason("Tokens in request are not valid");
			retokenResponse.setStatus(status);
			resultJsonString = Transformer.toJSonStringRetokenise(retokenResponse);
			return resultJsonString;
		} else {
			/** Creating list for each data type */
			for (Entry<String, RetokenisationHandler> entry : tokenMap.entrySet()) {
				if (entry.getValue().getDataType().equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
					intTokenList.add(Long.parseLong(entry.getValue().getOldToken()));
				}
				if (entry.getValue().getDataType().equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {
					strTokenList.add(entry.getValue().getOldToken());
				}
				if (entry.getValue().getDataType().equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
					try {
						SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
						Date date = formatter1.parse(entry.getValue().getOldToken());
						java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
						dateTokenList.add(timeStampDate);
					} catch (ParseException pe) {
						LOG.error(Constants.EXCEPTION, pe);
						throw new TokenException(Constants.SYSTEM_ERROR);
					}
				}
			}

			if (!intTokenList.isEmpty()) {
				tokenMap = retokenisationService.getRetokeniseData(Constants.TABLE_INTEGER, intTokenList,
						retokeniseRequest, tokenMap, authorisedBusinessEntities);
			}
			if (!strTokenList.isEmpty()) {
				tokenMap = retokenisationService.getRetokeniseData(Constants.TABLE_STRING, strTokenList,
						retokeniseRequest, tokenMap, authorisedBusinessEntities);
			}
			if (!dateTokenList.isEmpty()) {
				tokenMap = retokenisationService.getRetokeniseData(Constants.TABLE_DATETIME, dateTokenList,
						retokeniseRequest, tokenMap, authorisedBusinessEntities);
			}

			RetokeniseResponse retokenResponse = new RetokeniseResponse();
			List<RetokenRespData> retokenRespDataList = new ArrayList<RetokenRespData>();
			boolean isNotAuth = false;
			boolean isNotFound = false;
			for (Entry<String, RetokenisationHandler> entry : tokenMap.entrySet()) {
				RetokenRespData retokenRespData = new RetokenRespData();
				if (entry.getValue().getNewToken() == null || entry.getValue().getNewToken().isEmpty()) {
					retokenRespData.setId(entry.getKey());
					retokenRespData.setNewToken(Constants.NOT_FOUND);
					isNotFound = true;
				} else if (entry.getValue().getNewToken().equalsIgnoreCase(Constants.NOAUTH)) {
					retokenRespData.setId(entry.getKey());
					retokenRespData.setNewToken(Constants.NOAUTH);
					isNotAuth = true;
				} else if (entry.getValue().getNewToken() != null && !entry.getValue().getNewToken().isEmpty()
						&& !entry.getValue().getNewToken().equalsIgnoreCase(Constants.NOAUTH)) {
					retokenRespData.setId(entry.getKey());
					retokenRespData.setNewToken(entry.getValue().getNewToken());
				}
				retokenRespDataList.add(retokenRespData);
			}
			int statusCheck = 0;
			if (isNotAuth) {
				statusCheck = 2;
			}
			if (isNotFound) {
				statusCheck = 1;
			}
			if (isNotFound && isNotAuth) {
				statusCheck = 3;
			}
			Status retokenStatus = new Status();
			retokenStatus = commonUtility.getStatusForResponse(retokenStatus, statusCheck);
			retokenResponse.setData(retokenRespDataList);
			retokenResponse.setStatus(retokenStatus);
			resultJsonString = Transformer.toJSonStringRetokenise(retokenResponse);
			return resultJsonString;
		}

	}

	/**
	 * This method will check if fields in Request are empty or null and create string with all empty/null fields
	 * 
	 * @param retokenReqData
	 *            :Retoken Request Data Object
	 * @return boolean: True:- If any null or empty field is found, False:- If no empty/null value found
	 *
	 */
	public boolean isNullOrEmpty(RetokenReqData retokenReqData) {
		LOG.debug("Invoking isNullOrEmpty");
		if (retokenReqData.getId() == null || retokenReqData.getId().isEmpty()) {
			emptyFieldMessage.append("Id");
		}
		if (retokenReqData.getTargetFieldName() == null || retokenReqData.getTargetFieldName().isEmpty()) {
			if (emptyFieldMessage.length() > 0) {
				emptyFieldMessage.append(", TargetFieldName");
			} else {
				emptyFieldMessage.append(" TargetFieldName");
			}
		}
		if (retokenReqData.getToken() == null || retokenReqData.getToken().isEmpty()) {
			if (emptyFieldMessage.length() > 0) {
				emptyFieldMessage.append(", Token");
			} else {
				emptyFieldMessage.append(" Token");
			}
		}
		if (emptyFieldMessage.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method will create token map with respective Data Type
	 * 
	 * @param id
	 *            : id coming in request
	 * @param receivedToken
	 *            : token coming in request
	 * @param targetFieldName
	 *            : target field name coming in request
	 */

	private void getTokenList(String id, String receivedToken, String targetFieldName) {

		RetokenisationHandler retokenisationHandler = new RetokenisationHandler();
		retokenisationHandler.setTargetFieldName(targetFieldName);
		retokenisationHandler.setOldToken(receivedToken);

		String regExpInt = Constants.INT_TKN_REG_EXP;
		String regExpStr = Constants.STR_TKN_REG_EXP;
		String regExpDateTime = Constants.DATETIME_TKN_REG_EXP;

		Pattern patternInt = Pattern.compile(regExpInt);
		Pattern patternStr = Pattern.compile(regExpStr);
		Pattern patternDateTime = Pattern.compile(regExpDateTime);

		Matcher matcher1 = patternInt.matcher(receivedToken);
		Matcher matcher2 = patternStr.matcher(receivedToken);
		Matcher matcher3 = patternDateTime.matcher(receivedToken);
		if (matcher1.find()) {
			retokenisationHandler.setDataType(Constants.DATA_TYPE_INTEGER);
			tokenMap.put(id, retokenisationHandler);
		} else if (matcher2.find()) {
			retokenisationHandler.setDataType(Constants.DATA_TYPE_STRING);
			tokenMap.put(id, retokenisationHandler);
		} else if (matcher3.find()) {
			retokenisationHandler.setDataType(Constants.DATA_TYPE_DATETIME);
			tokenMap.put(id, retokenisationHandler);
		} else {
			retokenisationHandler.setDataType(Constants.NOT_TOKEN);
			tokenMap.put(id, retokenisationHandler);
			isNotToken = true;
		}
	}

}
