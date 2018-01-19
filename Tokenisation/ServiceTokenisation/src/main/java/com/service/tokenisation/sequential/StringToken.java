package com.service.tokenisation.sequential;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.service.tokenisation.dao.ConfigDAO;
import com.service.tokenisation.dao.TokenDecisionDAO;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.Configuration;

/**
 * 
 * The StringToken class is responsible for managing the tokens of type String.
 * 
 * @author: Satyajit
 * @version: 1
 * 
 */
public class StringToken {
	private static final Logger LOG = Logger.getLogger(StringToken.class);
	final Calendar cal = Calendar.getInstance();

	/**
	 * 
	 * This method creates the String token based on the business entity.
	 * 
	 * @param businessEnitity
	 *            Business entity, currently supported for Monaco, Geneva, Swiss token creation.
	 * @return a token
	 * @throws TokenException
	 */

	public String createStringToken(String businessEnitity) throws TokenException {
		LOG.debug("Invoking createStringToken");
		String strToken = null;
		strToken = getStringToken(businessEnitity);
		return strToken;

	}

	private String getStringToken(String businessEnitity) throws TokenException {
		LOG.debug("Invoking getStringToken");
		ConfigDAO configDAO = null;
		Configuration config = null;
		TokenDecisionDAO tDecisionDAO = null;
		configDAO = new ConfigDAO(businessEnitity);
		config = configDAO.getConfigDetails();
		tDecisionDAO = new TokenDecisionDAO(config.getConfigurationIdentifier());
		String token = tDecisionDAO.selectUpdateStrTokenDecision(config.getStringVaultIdentifer());
		return token;
	}

}
