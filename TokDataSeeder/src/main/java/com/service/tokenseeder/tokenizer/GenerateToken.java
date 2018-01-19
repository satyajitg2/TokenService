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
import com.service.tokenseeder.exception.TokenException;
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
	public String rotation(String flagAuto) {
		String integerTableToUse = null, stringTableToUse = null, datetimeTableToUse = null;
		IntegerTokenDAO it = new IntegerTokenDAO();
		StringTokenDAO st = new StringTokenDAO();
		DateTimeTokenDAO dtt = new DateTimeTokenDAO();
		String valueToStartDate;
		int tableSetUseByTokenEngine = 0;
		int tableSetUseByTokenSeeder = 0;
		StringBuilder sbReturn = new StringBuilder();
		int flagToSwitch=0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			  tx=session.beginTransaction();
			configDAO = new ConfigDAO();

			List<Configuration> list = configDAO.getConfigurationDetails(session);
			
			for (Configuration configuration : list) {

				tokenDecisionDAO = new TokenDecisionDAO(configuration.getConfigurationIdentifier());

				TokenDecision tokenDecision = tokenDecisionDAO.getTokenDecisionDetails(session);
				tableSetUseByTokenEngine = tokenDecision.getTableSetToUseByTokenEngine();
				tableSetUseByTokenSeeder = tokenDecision.getTableSetToUseByTokenSeeder();

				tokenSetDecisionDAO = new TableSetDecisionDAO(tokenDecision.getTableSetToUseByTokenSeeder());

				List<TokenSetDecision> tokenSetDecision = tokenSetDecisionDAO.getSetDecisionDetails(session);

				for (TokenSetDecision tSetDecision : tokenSetDecision) {
					integerTableToUse = tSetDecision.getTokenIntegerTable();
					stringTableToUse = tSetDecision.getTokenStringTable();
					datetimeTableToUse = tSetDecision.getTokenDateTimeTable();
				}

				SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
				valueToStartDate = dateFormat.format(tokenDecision.getTokenToStartGenerateDateTime());
				long maxIntegerValue = tokenDecision.getMaxTokenToGenerateInteger();
				maxIntegerValue = getMaxValue(session,maxIntegerValue, integerTableToUse,
						configuration.getConfigurationIdentifier());

				long maxStringValue = tokenDecision.getMaxTokenToGenerateString();
				maxStringValue = getMaxValue(session,maxStringValue, stringTableToUse,
						configuration.getConfigurationIdentifier());

				long maxDateTimeValue = tokenDecision.getMaxTokenToGenerateDateTime();
				maxDateTimeValue = getMaxValue(session,maxDateTimeValue, datetimeTableToUse,
						configuration.getConfigurationIdentifier());
				long intToken =tokenDecision.getTokenToStartGenerateInteger();
				String strToken=tokenDecision.getTokenToStartGenerateString();
				Timestamp dtToken = tokenDecision.getTokenToStartGenerateDateTime();
                if(maxIntegerValue>0){
                	flagToSwitch++;
				intToken = it.insertTokenToBank(session,maxIntegerValue, integerTableToUse,intToken, configuration.getIntegerTokenEndValue(),
						configuration.getIntegerTokenStartValue(), configuration.getConfigurationIdentifier());
                }
                if(maxStringValue>0){
                	flagToSwitch++;
				strToken = st.insertTokenToBank(session,maxStringValue, stringTableToUse,strToken
						, configuration.getStringVaultIdentifer(),
						configuration.getConfigurationIdentifier());
                }
                if(maxDateTimeValue>0){
                	flagToSwitch++;
				dtToken = dtt.insertTokenToBank(session,maxDateTimeValue, datetimeTableToUse, valueToStartDate,
						configuration.getDateTimeTokenYearEnd(), configuration.getDateTimeTokenYearStart(),
						configuration.getConfigurationIdentifier());
                }

				tokenDecisionDAO.updateTokenDecision(session,intToken, strToken, dtToken);
				
			}
			if(!flagAuto.equals(Constants.AUTO_SWITCH_FLAG)){
            if(flagToSwitch>0){
			int result=checkTableSet(session,tableSetUseByTokenEngine, tableSetUseByTokenSeeder);
			if(result>0){
				sbReturn.append("Tokens successfully inserted in set-");
				sbReturn.append(tableSetUseByTokenSeeder);
				sbReturn.append("!!!!!!!!!!");
            }
			}else{
				sbReturn.append("TokenBank is not been update, there are sufficient token in bank.");
				sbReturn.append("!!!!!!!!!!");
			}}else{
				sbReturn.append("Tokens successfully inserted");
			}
            tx.commit();
		} catch (Exception e) {
			LOGGER.error("Rotation: ", e);
			sbReturn.append("TokenBank is not been update, Please Check log file for further details.");
		}finally{
			if(session!=null){
				session.close();
			}
			HibernateUtil.stop();
		}
		
		return sbReturn.toString();	
	}// end of method

	// **** Private method go here *****//

	// Give Max token to be generated for particular table
	private long getMaxValue(Session session,long maxIntegerValue, String tableName, int configurationIdentifier) throws TokenException {

		long minValue = tokenSetDecisionDAO.getNumberofTokenInTable(session,tableName, configurationIdentifier);

		return maxIntegerValue - minValue;

	}// end of method

	// Select next set of tables to be used by TokenEngine and TokenGenerator
	private int checkTableSet(Session session,int tableSeeder, int tableEngine) throws TokenException {
		int tableSetSeeder = tableSeeder;
		int tableSetEngine = tableEngine;
		tableSetSeeder = tableSetSeeder + 1;
		tableSetEngine = tableSetEngine + 1;
		if (tableSetSeeder > 3) {
			tableSetSeeder = 1;
		}
		if (tableSetEngine > 3) {
			tableSetEngine = 1;
		}

		return tokenDecisionDAO.updateTableSet(session,tableSetSeeder, tableSetEngine);
	 

	}// end of method

	
} // end of class
