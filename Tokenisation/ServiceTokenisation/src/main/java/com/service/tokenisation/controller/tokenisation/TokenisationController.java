package com.service.tokenisation.controller.tokenisation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.service.TokenisationService;
import com.service.tokenisation.utility.CommonUtility;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.utility.Transformer;
import com.service.tokenisation.model.CsdRequest;
import com.service.tokenisation.model.CsdResponse;
import com.service.tokenisation.model.RequestData;
import com.service.tokenisation.model.ResponseData;
import com.service.tokenisation.model.Status;

/**
 * TokenisationController class This class will perform operation on Tokenisation input request
 * 
 * @author: Satyajit Singh
 * @version: 2 Modified For Performance Improvement 20 April 2016
 */

public class TokenisationController {

	private static final Logger LOG = Logger.getLogger(TokenisationController.class);
	private TokenisationService tokenisationService = new TokenisationService();
	private StringBuilder emptyFieldMessage = new StringBuilder();
	private CommonUtility commonUtility = new CommonUtility();
	private static int configurationId;
	private static int tokenSetToUse;
	
	public static int getConfigurationId() {
		return configurationId;
	}
	public static int getTokenSetToUse() {
		return tokenSetToUse;
	}

	/**
	 * This method is responsible for processing tokenisation request according to data type, repeatable flag
	 * 
	 * @param requestData
	 *            : Request Data Object
	 * @return String: Tokenisation response string
	 * @exception TokenException
	 */
	public String processTokenistion(RequestData requestData) throws TokenException {
		LOG.debug("Invoking processTokenistion");
		String resultJsonString = null;
		Status statusObj = new Status();
		ResponseData responseData = new ResponseData();
		List<CsdResponse> listCsdResp = new ArrayList<CsdResponse>();
		List<CsdRequest> csdList = requestData.getCsds();
		
		configurationId = tokenisationService.getConfigurationIdForBusinessEntity(requestData.getOwningBusinessEntity());
		tokenSetToUse = tokenisationService.getTokenSetToUse(configurationId,requestData.getOwningBusinessEntity());
		
		if (!csdList.isEmpty()) {
			for (int i = 0; i < requestData.getCsds().size(); i++) {
				CsdRequest csdReqObj = requestData.getCsds().get(i);
				CsdResponse csdResObj = new CsdResponse();
				String generatedToken = null;
				boolean result = isNullOrEmpty(csdReqObj);
				if (!result) {
					statusObj.setErrorCode(Constants.VALIDATION_FAILED);
					statusObj.setReason(Constants.MISSING_MANDATORY_FIELDS  + emptyFieldMessage.toString());
					statusObj.setStatus(Constants.FAILED);
					responseData.setStatus(statusObj);
					resultJsonString = Transformer.toJsonString(responseData);
					return resultJsonString;
				}
				int check = 0;
				if (csdReqObj.getIsRepeatable().equalsIgnoreCase(Constants.REPETABLE_TRUE)) {
					check = 1;
				} else if (csdReqObj.getIsRepeatable().equalsIgnoreCase(Constants.REPETABLE_FALSE)) {
					check = 2;
				}
				if (check == 0) {
					statusObj.setErrorCode(Constants.VALIDATION_FAILED);
					statusObj.setReason("Is Repeatable field is not valid");
					statusObj.setStatus(Constants.FAILED);
					responseData.setStatus(statusObj);
					resultJsonString = Transformer.toJsonString(responseData);
					return resultJsonString;
				}

				if (csdReqObj.getTokenType().equalsIgnoreCase(Constants.DATA_TYPE_STRING)
						|| csdReqObj.getTokenType().equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)
						|| csdReqObj.getTokenType().equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
					String reqFieldValue = csdReqObj.getFieldValue();
					String reqTokenType = csdReqObj.getTokenType();
					if (csdReqObj.getIsRepeatable().equalsIgnoreCase(Constants.REPETABLE_TRUE)) {
						try {
							generatedToken = tokenisationService.getRepetableTokenFromVault(reqFieldValue,
									reqTokenType, csdReqObj, requestData);
						} catch (Exception e) {
							LOG.error(Constants.EXCEPTION, e);
							throw new TokenException(e.toString());
						}
						if (generatedToken != null) {
							csdResObj.setToken(generatedToken);
							csdResObj.setId(csdReqObj.getId());
						}
					} else if (csdReqObj.getIsRepeatable().equalsIgnoreCase(Constants.REPETABLE_FALSE)) {
						try {
							generatedToken = tokenisationService.getUniqueTokenFromTokenBank(reqFieldValue,
									reqTokenType, csdReqObj, requestData);
						} catch (TokenException e) {
							LOG.error(Constants.EXCEPTION, e);
							throw new TokenException(e.toString());
						}
						if (generatedToken != null) {
							csdResObj.setToken(generatedToken);
							csdResObj.setId(csdReqObj.getId());
						}
					}
					listCsdResp.add(csdResObj);
				} else {
					statusObj.setErrorCode(Constants.VALIDATION_FAILED);
					statusObj.setStatus(Constants.FAILED);
					statusObj.setReason("Invalid Data Type in request");
					responseData.setStatus(statusObj);
					resultJsonString = Transformer.toJsonString(responseData);
					return resultJsonString;
				}
			}
		}
		
		statusObj.setStatus(Constants.SUCCESS);
		responseData.setStatus(statusObj);
		responseData.setCsds(listCsdResp);
		resultJsonString = Transformer.toJsonString(responseData);
		return resultJsonString;
	}

	/**
	 * This method will check if fields in Request are empty or null and create string with all empty/null fields
	 * 
	 * @param requestData
	 *            : Request Data Object
	 * @return boolean: True:- If any null or empty field is found, False:- If no empty/null value found
	 *
	 */
	public boolean isNullOrEmpty(CsdRequest csd) {
		LOG.debug("Invoking isNullOrEmpty");

		commonUtility.checkIfEmpty(csd.getId(), "Id",emptyFieldMessage);
		commonUtility.checkIfEmpty(csd.getSourceFieldName(), "SourceFieldName",emptyFieldMessage);
		commonUtility.checkIfEmpty(csd.getFieldValue(), "FieldValue",emptyFieldMessage);
		commonUtility.checkIfEmpty(csd.getTokenType(), "TokenType",emptyFieldMessage);
		commonUtility.checkIfEmpty(csd.getTargetFieldName(), "TargetFieldName",emptyFieldMessage);
		commonUtility.checkIfEmpty(csd.getIsRepeatable(), "IsRepeatable",emptyFieldMessage);
		if (emptyFieldMessage.length() > 0) {
			return false;
		} 
		return true;
		
	}

}
