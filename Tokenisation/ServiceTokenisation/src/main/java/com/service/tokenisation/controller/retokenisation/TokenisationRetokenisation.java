package com.service.tokenisation.controller.retokenisation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.service.tokenisation.controller.tokenisation.TokenisationController;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.RequestData;
import com.service.tokenisation.model.ResponseData;
import com.service.tokenisation.utility.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

/** TokenisationRetokenisation class
 * This class is used by retokenisation to invoke tokenisation service
 * @author: Satyajit Singh
 * @version: 1 
 */

/** WILL REMOVE ONCE JAR FILE IS ADDED */
public class TokenisationRetokenisation {
	private static final Logger LOG = Logger.getLogger(TokenisationRetokenisation.class);

	TokenisationController tokenisationController = new TokenisationController();

	public List<ResponseData> getTokeniseData(List<RequestData> listTokenRequestData) throws TokenException {
		LOG.info("Invoking getTokeniseData");
		List<ResponseData> responseDataList = new ArrayList<>();
		for (RequestData requestDataObj : listTokenRequestData) {

			String outputStr = tokenisationController.processTokenistion(requestDataObj);
			ObjectMapper mapper = new ObjectMapper();
			ResponseData responseData;
			try {
				responseData = mapper.readValue(outputStr, ResponseData.class);
			} catch (Exception e) {
				LOG.error(Constants.EXCEPTION, e);
				throw new TokenException(Constants.SYSTEM_ERROR);
			}

			responseDataList.add(responseData);
		}
		return responseDataList;
	}

}
