package com.service.tokenisation.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.service.tokenisation.dao.DetokenisationDAO;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.DetokeniseRequestData;

/**
 * DetokenisationService class This is service layer class for Detokenisation
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class DetokenisationService {
	private static final Logger LOG = Logger.getLogger(DetokenisationService.class);
	private DetokenisationDAO detokenisationDAO = new DetokenisationDAO();

	/**
	 * This method will call respective dao method to getCsdFromVault
	 * 
	 * @param map
	 *            :token map
	 * @param tableName
	 *            :vault table name
	 * @param tokenList
	 *            :token list
	 * @return boolean
	 * @exception TokenException
	 */
	public boolean getCsdFromVault(Map<String, String> map, String tableName, List tokenList, String domainName)
			throws TokenException {
		LOG.info("Invoking getCsdFromVault");
		return detokenisationDAO.getCsdFromVault(map, tableName, tokenList, domainName);
	}

	/**
	 * This method will call respective dao method getCsdFromMCOVault
	 * 
	 * @param map
	 *            :token map
	 * @param tableName
	 *            :vault table name
	 * @param tokenList
	 *            :token list
	 * @return boolean
	 * @exception TokenException
	 */
	public boolean getCsdFromMCOVault(Map<String, String> map, String tableName, List tokenList, String domainName)
			throws TokenException {
		LOG.info("Invoking getCsdFromVault");
		return detokenisationDAO.getCsdFromMCOVault(map, tableName, tokenList, domainName);
	}

	/**
	 * This method will call respective dao method to check the Authorization for DeTokenisation Request
	 * 
	 * @param requestData
	 *            :Detoken Request Data Object
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorised(DetokeniseRequestData detokeniseRequestData) throws TokenException {
		LOG.debug("Invoking isAuthorised");
		return detokenisationDAO.isAuthorised(detokeniseRequestData);
	}

	
	/**
	 * This method will call respective dao method to check the Authorization for DeTokenisation Request
	 * 
	 * @param requestData
	 *            :Detoken Request Data Object
	 *	@param requestData
	 *            :callingSystemName
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorisedCallingSystem(DetokeniseRequestData detokeniseRequestData, String callingSystemName) throws TokenException {
		LOG.debug("Invoking isAuthorisedCallingSystem");
		return detokenisationDAO.isAuthorisedCallingSystem(detokeniseRequestData, callingSystemName);
	}

}
