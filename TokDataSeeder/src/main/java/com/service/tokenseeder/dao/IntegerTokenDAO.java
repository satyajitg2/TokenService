package com.service.tokenseeder.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.tokenseeder.exception.TokenException;
import com.service.tokenseeder.model.Configuration;
import com.service.tokenseeder.model.SeedInteger1;
import com.service.tokenseeder.model.SeedInteger2;
import com.service.tokenseeder.model.SeedInteger3;
import com.service.tokenseeder.model.TokenDecision;

public class IntegerTokenDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntegerTokenDAO.class);
	ConfigDAO configDAO = null;
	Configuration config = null;
	TokenDecisionDAO tDecisionDAO = null;
	TokenDecision tDecision = null;
	List<Long> listIntToken = null;
	final Calendar cal = Calendar.getInstance();

	public long insertTokenToBank(Session session,long intmaxvalue, String inttablename, long startvalue, long upperlimit,
			long lowerlimit, int configurationIdentifier) throws TokenException {
		cal.setTimeInMillis(System.currentTimeMillis());

		final String startTimeInt = new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());

		LOGGER.info("Integer: startTimeInt :" + startTimeInt);
	
		long data = startvalue;
		int i = 0, count = 1;
		try {

			while (i < intmaxvalue) {
				if (lowerlimit <= data && (data < upperlimit)) {

					if (("SeedInteger1").equals(inttablename)) {

						SeedInteger1 seedInteger = new SeedInteger1();
						seedInteger.setSeedValue(data);
						seedInteger.setSeedUsedFlag(false);
						seedInteger.setConfigurationIdentifier(configurationIdentifier);
						session.save(seedInteger);
						flushToken(session, i);
						i++;

					} else if (("SeedInteger2").equals(inttablename)) {

						SeedInteger2 seedInteger = new SeedInteger2();
						seedInteger.setSeedValue(data);
						seedInteger.setSeedUsedFlag(false);
						seedInteger.setConfigurationIdentifier(configurationIdentifier);
						session.save(seedInteger);
						flushToken(session, i);
						i++;

					} else {

						SeedInteger3 seedInteger = new SeedInteger3();
						seedInteger.setSeedValue(data);
						seedInteger.setSeedUsedFlag(false);
						seedInteger.setConfigurationIdentifier(configurationIdentifier);
						session.save(seedInteger);
						flushToken(session, i);
						i++;

					}

				}

				data = data + count;
				
			}
           
		} catch (SQLGrammarException e) {
			
			LOGGER.error("SQLGrammar Exception insertTokenToBank-IntegerTokenDAO", e);
			throw new TokenException("SQLGrammar Exception insertTokenToBank-IntegerTokenDAO");
		} catch (ConstraintViolationException e) {
			
			LOGGER.error("ConstraintViolation Exception insertTokenToBank-IntegerTokenDAO", e);
			throw new TokenException("ConstraintViolation Exception insertTokenToBank-IntegerTokenDAO");
		} catch (HibernateException e) {
			
			LOGGER.error("Hibernate Exception insertTokenToBank-IntegerTokenDAO", e);
			throw new TokenException("Hibernat Exception insertTokenToBank-IntegerTokenDAO");
		} finally {
			session.flush();
			session.clear();
			cal.setTimeInMillis(0);
			cal.setTimeInMillis(System.currentTimeMillis());

			final String endTimeInt = new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());

			LOGGER.info("Integer: endTimeInt :" + endTimeInt);

		}

		return data;
	}

	/**
	 * 
	 * @param session
	 * @param count
	 */

	private void flushToken(Session session, int count) {
		if (count % 1000 == 0) {
			session.flush();
			session.clear();
		}
	}
}
