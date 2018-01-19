package com.service.tokenseeder.tokenizer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.tokenseeder.constants.Constants;
import com.service.tokenseeder.dao.ConfigDAO;
import com.service.tokenseeder.dao.DateTimeTokenDAO;
import com.service.tokenseeder.dao.IntegerTokenDAO;
import com.service.tokenseeder.dao.StringTokenDAO;
import com.service.tokenseeder.dao.TableSetDecisionDAO;
import com.service.tokenseeder.dao.TokenDecisionDAO;
import com.service.tokenseeder.model.Configuration;
import com.service.tokenseeder.model.TokenDecision;
import com.service.tokenseeder.model.TokenSetDecision;
import com.service.tokenseeder.utils.HibernateUtil;

/**
 * <p>
 * The class <b> GenerateToken <b> is the primary class responsible for generating tokens It implements the rotation
 * logic as per the business requirements.
 * <p>
 * 
 * @version 1.0
 */

public class GenerateToken {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateToken.class);
	Transaction tx = null;
	Session session = null;
	Query query = null;
	ConfigDAO configDAO = null;
	TokenDecisionDAO tokenDecisionDAO = null;
	TableSetDecisionDAO tokenSetDecisionDAO = null;

	/**
	 * <p>
	 * This table is responsible for updating the corresponding Set of tables for Integer, DateTime and String are
	 * updated
	 * <p>
	 * 
	 * @return - User friendly message that gets logged in the logger.
	 * @throws TokenException
	 */
	
	// Give Max token to be generated for particular table
	private long getMaxValue(long maxIntegerValue, String tableName, int configurationIdentifier) {

		long minValue = tokenSetDecisionDAO.getNumberofTokenInTable(tableName, configurationIdentifier);

		return maxIntegerValue - minValue;

	}// end of method

	
	public void generateTokenInSet(int tableSet, String owningBusinessEntity) {
		LOGGER.info("TokenRotation1>>>>>>>>>>>>>>>>>>>>>>>>> In generateTokenInSet()");
		String integerTableToUse = null, stringTableToUse = null, datetimeTableToUse = null;
		IntegerTokenDAO it = new IntegerTokenDAO();
		StringTokenDAO st = new StringTokenDAO();
		DateTimeTokenDAO dtt = new DateTimeTokenDAO();
		String valueToStartDate;

		configDAO = new ConfigDAO(owningBusinessEntity);
		Configuration configuration = configDAO.getConfigDetails();

		tokenDecisionDAO = new TokenDecisionDAO(configuration.getConfigurationIdentifier());
		TokenDecision tokenDecision = tokenDecisionDAO.getTokenDecisionDetails();
		tokenSetDecisionDAO = new TableSetDecisionDAO(tableSet);

		List<TokenSetDecision> tokenSetDecision = tokenSetDecisionDAO.getSetDecisionDetails();

		for (TokenSetDecision tSetDecision : tokenSetDecision) {
			integerTableToUse = tSetDecision.getTokenIntegerTable();
			stringTableToUse = tSetDecision.getTokenStringTable();
			datetimeTableToUse = tSetDecision.getTokenDateTimeTable();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
		valueToStartDate = dateFormat.format(tokenDecision.getTokenToStartGenerateDateTime());

		int maxIntegerValue = tokenDecision.getMaxTokenToGenerateInteger();
		maxIntegerValue = (int) getMaxValue(maxIntegerValue, integerTableToUse,
				configuration.getConfigurationIdentifier());

		int maxStringValue = tokenDecision.getMaxTokenToGenerateString();
		maxStringValue = (int) getMaxValue(maxStringValue, stringTableToUse, configuration.getConfigurationIdentifier());

		int maxDateTimeValue = tokenDecision.getMaxTokenToGenerateDateTime();
		maxDateTimeValue = (int) getMaxValue(maxDateTimeValue, datetimeTableToUse,
				configuration.getConfigurationIdentifier());

		long intToken = it.insertTokenToBank(maxIntegerValue, integerTableToUse,
				tokenDecision.getTokenToStartGenerateInteger(), configuration.getIntegerTokenEndValue(),
				configuration.getIntegerTokenStartValue(), configuration.getConfigurationIdentifier());
		String strToken = st.insertTokenToBank(maxStringValue, stringTableToUse,
				tokenDecision.getTokenToStartGenerateString(), configuration.getStringVaultIdentifer(),
				configuration.getConfigurationIdentifier());
		Timestamp dtToken = dtt.insertTokenToBank(maxDateTimeValue, datetimeTableToUse, valueToStartDate,
				configuration.getDateTimeTokenYearEnd(), configuration.getDateTimeTokenYearStart(),
				configuration.getConfigurationIdentifier());

		tokenDecisionDAO.updateTokenDecision(intToken, strToken, dtToken);

	}

} // end of class
