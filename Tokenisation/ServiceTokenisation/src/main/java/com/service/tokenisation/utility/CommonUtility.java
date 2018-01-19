package com.service.tokenisation.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.Status;

/**
 * CommonUtility class This is common Utility class
 * 
 * @author: Satyajit Singh
 * @version: 1
 */

public class CommonUtility {
	private static final Logger LOG = Logger.getLogger(CommonUtility.class);

	/**
	 * Method is use to read properties file
	 * 
	 * @throws TokenException
	 * */
	public Properties readProperteyFile(String fileName) throws TokenException {
		LOG.debug("Invoking readProperteyFile");
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = getClass().getClassLoader().getResourceAsStream(fileName);
			prop.load(input);
		} catch (IOException ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return prop;
	}
	
	public Status getStatus(Status statusObj,String messageStr) throws TokenException {
		LOG.debug("Invoking getStatus");
		if (messageStr.contains(Constants.VAULT_ERROR)) {
			statusObj.setErrorCode(Constants.VAULT_ERROR);
			statusObj.setStatus(Constants.FAILED);
		} else if (messageStr.contains(Constants.DB_UNAVAILABLE)) {
			statusObj.setErrorCode(Constants.DB_UNAVAILABLE);
			statusObj.setStatus(Constants.FAILED);
		} else {
			statusObj.setErrorCode(Constants.SYSTEM_ERROR);
			statusObj.setStatus(Constants.FAILED);
		}
		return statusObj;
	}
	
	public Status getStatusForResponse(Status status,int statusVar) throws TokenException {
		LOG.debug("Invoking getStatusForResponse");
		switch (statusVar) {
		case 0:
			status.setStatus(Constants.SUCCESS);
			break;
		case 1:
			status.setStatus(Constants.PARTIAL_SUCCESS);
			status.setErrorCode(Constants.NOT_FOUND);
			status.setReason(Constants.NOT_FOUND_REASON);
			break;
		case 2:
			status.setStatus(Constants.PARTIAL_SUCCESS);
			status.setErrorCode(Constants.BU_NOAUTH);
			status.setReason(Constants.NOT_AUTH_REASON);
			break;
		case 3:
			status.setStatus(Constants.PARTIAL_SUCCESS);
			status.setErrorCode(Constants.MULTI_ERRORS);
			status.setReason(Constants.MULTI_ERR_REASON);
			break;
		case 4:
			status.setStatus(Constants.PARTIAL_SUCCESS);
			status.setErrorCode(Constants.MULTI_ERRORS);
			status.setReason(Constants.MULTI_ERR_REASON_DETOKEN);
			break;
		default:
			break;
		}
		return status;
	}
	
	public boolean checkIfEmpty(String fieldToCheck, String toAppend, StringBuilder emptyMessage) {
		LOG.debug("Invoking checkIfEmpty");
		if (fieldToCheck == null || fieldToCheck.isEmpty()) {
			if (emptyMessage.length() > 0) {
				emptyMessage.append(", ");
				emptyMessage.append(toAppend);
			} else {
				emptyMessage.append(toAppend);
			}

			return true;
		} else {
			return false;
		}
	}

}
