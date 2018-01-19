package com.service.tokenisation.sequential;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.service.tokenisation.dao.ConfigDAO;
import com.service.tokenisation.dao.TokenDecisionDAO;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.model.Configuration;

/**
 * 
 * The DateTimeToken class is responsible for managing the Date Time Token The driving factors are the upper and lower
 * year limits.
 * 
 * @author: Satyajit
 * @version: 1
 *
 */

public class DateTimeToken {
	private static final Logger LOG = Logger.getLogger(DateTimeToken.class);

	final Calendar cal = Calendar.getInstance();

	/**
	 * This method creates the Date Time token based on the business entity.
	 * <p>
	 * 
	 * @param businessEnitity
	 *            -
	 *            <p>
	 *            Business entity, currently supported for Monaco, Geneva, Swiss token creation.
	 *            </p>
	 * @return a token
	 * @throws TokenException
	 */

	public Timestamp createDateTimeToken(String businessEnitity) throws TokenException {
		LOG.debug("Invoking createDateTimeToken");
		Timestamp dateToken = null;
		dateToken = getDateTimeToken(businessEnitity);
		return dateToken;

	}

	private Timestamp getDateTimeToken(String businessEnitity) throws TokenException {
		LOG.debug("Invoking getDateTimeToken");
		ConfigDAO configDAO = null;
		Configuration config = null;
		TokenDecisionDAO tDecisionDAO = null;
		configDAO = new ConfigDAO(businessEnitity);
		config = configDAO.getConfigDetails();
		tDecisionDAO = new TokenDecisionDAO(config.getConfigurationIdentifier());
		Timestamp token = tDecisionDAO.selectUpdateDateTokenDecision(config.getDateTimeTokenYearEnd(),
				config.getDateTimeTokenYearStart());
		return token;
	}

}
