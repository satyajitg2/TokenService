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
import com.service.tokenseeder.constants.Constants;
import com.service.tokenseeder.model.Configuration;
import com.service.tokenseeder.model.SeedString1;
import com.service.tokenseeder.model.SeedString2;
import com.service.tokenseeder.model.SeedString3;
import com.service.tokenseeder.model.TokenDecision;
import com.service.tokenseeder.utils.HibernateUtil;

/**
 * <p>
 * The <b> StringToken <b> class is responsible for managing the tokens of type String.
 * <p>
 */
public class StringTokenDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(StringTokenDAO.class);
	ConfigDAO configDAO = null;
	Configuration config = null;
	TokenDecisionDAO tDecisionDAO = null;
	TokenDecision tDecision = null;

	List<String> listStrToken = null;
	final Calendar cal = Calendar.getInstance();

	/**
	 * <p>
	 * The method insertTokenToBank is responsible for inserting the token of type String.
	 * 
	 * @param stringmaxvalue
	 * @param strtablename
	 * @param startvalue
	 * @param pattern
	 * @param configurationIdentifier
	 * @return the token
	 */
	public String insertTokenToBank(long stringmaxvalue, String strtablename, String startvalue, String pattern,
			int configurationIdentifier) {

		cal.setTimeInMillis(System.currentTimeMillis());

		final String startTimeString = new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());

		LOGGER.info("String: startTimeString :" + startTimeString);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		String sToken = startvalue;
		// This variable is used to track where we are in the token generation.
		char[] tokenIndex = { '0', '0', '0', '0', '0', '0', '0' };

		String append = Constants.STRING_TOKEN_PATTERN + pattern;

		tokenIndex = setStartingIndex(sToken, tokenIndex, Constants.getStringToken());
		int j = 0;
		try {
			for (j = 0; j < stringmaxvalue; ++j) {

				sToken = append + sToken;
				if (("SeedString1").equals(strtablename)) {
					SeedString1 seedString = new SeedString1();
					seedString.setSeedValue(sToken);
					seedString.setSeedUsedFlag(false);
					seedString.setConfigurationIdentifier(configurationIdentifier);
					session.save(seedString);
					flushToken(session, j);
				} else if (("SeedString2").equals(strtablename)) {
					SeedString2 seedString = new SeedString2();
					seedString.setSeedValue(sToken);
					seedString.setSeedUsedFlag(false);
					seedString.setConfigurationIdentifier(configurationIdentifier);
					session.save(seedString);
					flushToken(session, j);
				} else {
					SeedString3 seedString = new SeedString3();
					seedString.setSeedValue(sToken);
					seedString.setSeedUsedFlag(false);
					seedString.setConfigurationIdentifier(configurationIdentifier);
					session.save(seedString);
					flushToken(session, j);
				}
				sToken = incrementTokenIndex(tokenIndex, Constants.getStringToken());
			}

			session.getTransaction().commit();

			if (sToken.length() == 10) {
				sToken = sToken.substring(3);
			}

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception insertTokenToBank-StringTokenDAO", e);
		} catch (ConstraintViolationException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("ConstraintViolation Exception insertTokenToBank-StringTokenDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception insertTokenToBank-StringTokenDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}
			cal.setTimeInMillis(0);
			cal.setTimeInMillis(System.currentTimeMillis());

			final String endTimeString = new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());

			LOGGER.info("String: endTimeString :" + endTimeString);

		}

		return sToken;
	}

	// ***** Private method start here ******//

	// Add one to the index count. Making sure to move along the indices if we
	// have gotten to the limit of one index dimension.
	private String incrementTokenIndex(char[] tokenIndex, char[] stringTokens) {
		boolean stopAdding = false;
		for (int i = 0; (i < tokenIndex.length) && (!stopAdding); i++) {
			tokenIndex[i] = (char) (tokenIndex[i] + 1);
			if ((tokenIndex[i]) < stringTokens.length) {
				stopAdding = true;
			} else {
				tokenIndex[i] = 0;
			}
		}

		return nextToken(tokenIndex, stringTokens);

	}

	// This function takes an initial input string and aligns the index so we
	// can start counting from there
	private char[] setStartingIndex(String startToken, char[] tokenIndex, char[] stringTokens) {

		for (int i = startToken.length() - 1, j = 0; i >= 0; i--, j++) {
			tokenIndex[j] = (char) indexInTokens(startToken.charAt(i), stringTokens);
		}
		return tokenIndex;
	}

	// This function turns the index into a token
	private String nextToken(char[] tokenIndex, char[] stringTokens) {
		String newToken = "";
		for (int i = 0; i < tokenIndex.length; i++) {
			int j = tokenIndex[i];
			newToken = stringTokens[j] + newToken;
		}
		return newToken;
	}

	// This function finds where in the Token Character Universe this Character
	// occurs
	private int indexInTokens(char tokenChar, char[] stringTokens) {
		int idxInTokens = -1, i = 0;
		while (idxInTokens < 0 && i < stringTokens.length) {

			if (stringTokens[i] == tokenChar) {
				idxInTokens = i;
			}
			i++;
		}
		return idxInTokens;
	}

	private void flushToken(Session session, int count) {
		if (count % 1000 == 0) {
			session.flush();
			session.clear();
		}
	}

}
