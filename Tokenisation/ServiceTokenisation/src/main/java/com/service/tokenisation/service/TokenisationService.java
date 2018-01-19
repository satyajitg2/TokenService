package com.service.tokenisation.service;

import org.apache.log4j.Logger;

import com.service.tokenisation.dao.TokenisationDAO;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.CsdRequest;
import com.service.tokenisation.model.RequestData;

/**
 * TokenisationService class This is service layer class for Tokenisation
 * 
 * @author: Satyajit Singh
 * @version: 1
 */

public class TokenisationService {
	private static final Logger LOG = Logger.getLogger(TokenisationService.class);
	private TokenisationDAO tokenisationDAO = new TokenisationDAO();

	public void setTokenisationDAO(TokenisationDAO tokenisationDAO) {
		LOG.debug("setTokenisationDAO Method is called ");
		this.tokenisationDAO = tokenisationDAO;
	}

	/**
	 * This method will call respective dao method for getting Repeatable Token from the Vault
	 * 
	 * @param csdReqObj
	 *            : CSD object
	 * @param reqFieldValue
	 *            : CSD for which token is required
	 * @param reqTokenTyp
	 *            : Token Type (String/Integer/DateTime)
	 * @param requestData
	 *            : Request Data Object
	 * @return String: Repetable token fetched from the Vault
	 * @exception TokenException
	 */
	public String getRepetableTokenFromVault(String reqFieldValue, String reqTokenType, CsdRequest csdReqObj,
			RequestData requestData) throws TokenException {
		LOG.debug("Invoking getRepetableTokenFromVault");
		return tokenisationDAO.getRepetableTokenFromVault(reqFieldValue, reqTokenType, csdReqObj, requestData);
	}

	/**
	 * This method will call respective dao method for getting Unique Token from the Token Bank
	 * 
	 * @param csdReqObj
	 *            : CSD object
	 * @param reqFieldValue
	 *            : CSD for which token is required
	 * @param reqTokenTyp
	 *            : Token Type (String/Integer/DateTime)
	 * @param requestData
	 *            : Request Data Object
	 * @return String: Unique token fetched from the Token Seed Table
	 * @exception TokenException
	 */
	public String getUniqueTokenFromTokenBank(String reqFieldValue, String reqTokenType, CsdRequest csdReqObj,
			RequestData requestData) throws TokenException {
		LOG.debug("Invoking getUniqueTokenFromTokenBank");

		return tokenisationDAO.getUniqueTokenFromTokenBank(reqFieldValue, reqTokenType, csdReqObj, requestData);
	}

	/**
	 * This method will call respective dao method to check the Authorization for Tokenisation Request
	 * 
	 * @param requestData
	 *            : Request Data Object
	 * @param CallingSystemName
	 *            : Read from Certificate
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorised(RequestData requestData, String callingSystemName) throws TokenException {
		LOG.debug("Invoking isAuthorised");
		return tokenisationDAO.isAuthorised(requestData,callingSystemName);
	}
	/**
	 * This method will fetch Configuration for particular Business Entity
	 * 
	 * @param owningBusinessEntity
	 *            : Business Entity Coming in request
	 * @return integer: Configuration ID
	 * @exception TokenException
	 */
	public int getConfigurationIdForBusinessEntity(String owningBusinessEntity) throws TokenException {
		LOG.debug("Invoking getConfigurationIdForBusinessEntity");
		return tokenisationDAO.getConfigurationIdForBusinessEntity(owningBusinessEntity);
		
	}

	/**
	 * This method will fetch Token Set to be used by service
	 * 
	 * @param configurationId
	 *            : Configuration id for requested Business Entity
	 * @param owningBusinessEntity
	 *            : Business Entity Coming in request
	 * @return integer: Token Set to use
	 * @exception TokenException
	 */
	public int getTokenSetToUse(int configurationId, String owningBusinessEntity) throws TokenException {
		LOG.debug("Invoking getTokenSetToUse");
		return tokenisationDAO.getTokenSetToUse(configurationId,owningBusinessEntity);
	}
}
