package com.service.tokenisation.sequential;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.service.tokenisation.dao.ConfigDAO;
import com.service.tokenisation.dao.TokenDecisionDAO;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.Configuration;

/**
 * 
 * The IntegerToken class is responsible for managing the Integer Token
 * 
 * @author: Satyajit
 * @version: 1
 *
 */
public class IntegerToken {
	private static final Logger LOG =Logger.getLogger(IntegerToken.class);
	final Calendar cal = Calendar.getInstance();

	/**
	 * This method creates the Integer token based on the business entity.
	 * 
	 * @param businessEnitity
	 *            -Business entity, currently supported for Monaco, Geneva, Swiss token creation.
	 * @return a token
	 * @throws TokenException
	 */

	public long createIntegerToken(String businessEnitity) throws TokenException {
		LOG.debug("Invoking createIntegerToken");
		long intToken = 0;
		intToken = getIntegerToken(businessEnitity);
		return intToken;
	}

	private long getIntegerToken(String businessEnitity) throws TokenException {
		LOG.debug("Invoking getIntegerToken");
		ConfigDAO configDAO = null;
		Configuration config = null;
		TokenDecisionDAO tDecisionDAO = null;
		long token = 0;
		configDAO = new ConfigDAO(businessEnitity);
		config = configDAO.getConfigDetails();
		tDecisionDAO = new TokenDecisionDAO(config.getConfigurationIdentifier());
		token=tDecisionDAO.selectUpdateIntTokenDecision();
		return token;

	}

}
