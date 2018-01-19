package com.service.tokenisation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.service.tokenisation.dao.RetokenisationDAO;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.RetokenisationHandler;
import com.service.tokenisation.model.RetokeniseRequest;

/**
 * RetokenisationService class This is service layer class for Retokenisation
 * 
 * @author: Satyajit Singh
 * @version: 1
 */

public class RetokenisationService {
	private static final Logger LOG = Logger.getLogger(RetokenisationService.class);

	private RetokenisationDAO retokenisationDAO = new RetokenisationDAO();

	public void setRetokenisationDAO(RetokenisationDAO retokenisationDAO) {
		this.retokenisationDAO = retokenisationDAO;
	}

	/**
	 * This method will call respective dao method to fetch Authorized Business Entity list from APIAuth table for
	 * retokenisation
	 * 
	 * @param sourceDomainName
	 *            : Source domain coming in request
	 * @param targetDomainName
	 *            : Target domain coming in request
	 * @return List: Authorized Business entity name list
	 * @exception TokenException
	 */
	public List<String> getAuthorisedBusinessEntities(String sourceDomainName, String targetDomainName)
			throws TokenException {
		LOG.info("Invoking getAuthorisedBusinessEntities");
		return retokenisationDAO.getAuthorisedBusinessEntities(sourceDomainName, targetDomainName);
	}

	/**
	 * This method will call respective dao method to fetch csd from the vault and update the map
	 * 
	 * @param tableName
	 *            : Token Vault table name from which token needs to be fetched
	 * @param tokenList
	 *            : Token list according to data type
	 * @param retokeniseRequest
	 * @param tokenMap
	 * @param authorisedBusinessEntities
	 * @return none
	 * @exception TokenException
	 */
	public Map<String, RetokenisationHandler> getRetokeniseData(String tableName, List tokenList,
			RetokeniseRequest retokeniseRequest, Map<String, RetokenisationHandler> tokenMap,
			List<String> authorisedBusinessEntities) throws TokenException {
		LOG.info("Invoking getRetokeniseData");
		return retokenisationDAO.getRetokeniseData(tableName, tokenList, retokeniseRequest, tokenMap,
				authorisedBusinessEntities);
	}

	/**
	 * This method will will call respective dao method to check the Authorization of ReTokenisation Request using
	 * source and target domain
	 * 
	 * @param retokeniseRequest
	 *            : Retoken Request object
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorised(RetokeniseRequest retokeniseRequest) throws TokenException {
		LOG.info("Invoking isAuthorised");
		return retokenisationDAO.isAuthorised(retokeniseRequest);
	}

	/**
	 * This method will will call respective dao method to check the Authorization of ReTokenisation Request using
	 * source and target domain
	 * 
	 * @param retokeniseRequest
	 *            : Retoken Request object
	 * @param callingSystemName
	 *            : Calling System
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorisedCallingSystem(RetokeniseRequest retokeniseRequest, String callingSystemName) throws TokenException {
		LOG.info("Invoking isAuthorisedCallingSystem");
		return retokenisationDAO.isAuthorisedCallingSystem(retokeniseRequest,callingSystemName);
	}

}
