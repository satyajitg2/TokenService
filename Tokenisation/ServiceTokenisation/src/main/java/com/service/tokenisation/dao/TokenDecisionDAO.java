package com.service.tokenisation.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.model.TokenDecision;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.utility.TokenUtil;

/**
 * <p>
 * The <b> TokenDecisionDAO <b> class performs operation on TokenDecision table.
 * <p>
 * 
 * @version: 1.0
 */
public class TokenDecisionDAO {
	private static final Logger LOG = Logger.getLogger(TokenDecisionDAO.class);
	private int configurationIdentifier = 0;

	public TokenDecisionDAO() {
	}

	public TokenDecisionDAO(int configurationIdentifier) {
		super();
		this.configurationIdentifier = configurationIdentifier;
	}

	/**
	 * <p>
	 * This function fetch complete details from TokenDecision table based on Region/BusinessEntity
	 * </p>
	 * 
	 * @return TokenDecision data object
	 * @throws TokenException
	 */

	public long selectUpdateIntTokenDecision() throws TokenException {
		TokenDecision tokenDecision = null;
		Transaction tx = null;
		Session session = null;
		List<TokenDecision> listTokenDecisions = null;
		long tokenToInsert = 0l;
		int updateCount = 0;
		try {
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			session.clear();
			Query namedQuery = session.getNamedQuery("getTokenDecisionDetails");
			namedQuery.setParameter("CONFIGIDENTIFIER", configurationIdentifier);
			listTokenDecisions = namedQuery.list();

			if (!listTokenDecisions.isEmpty()) {
				tokenDecision = listTokenDecisions.get(0);
			}
			session.flush();

			tokenToInsert = TokenUtil.generateRealTimeInt(tokenDecision.getTokenToStartGenerateInteger());
			long nextValue = tokenDecision.getUpdateCount() + 1;
			String hql = "UPDATE TokenDecision SET tokenToStartGenerateInteger =:TOKEN,UpdateCount =:updateCount  WHERE ConfigurationIdentifier =:CONFIG_IDENTIFIER and UpdateCount =:CURRENT_UpdateCount";
			Query query = session.createQuery(hql);
			query.setParameter("TOKEN", tokenToInsert);
			query.setParameter("updateCount", nextValue);
			query.setParameter("CONFIG_IDENTIFIER", configurationIdentifier);
			query.setParameter("CURRENT_UpdateCount", tokenDecision.getUpdateCount());

			updateCount = query.executeUpdate();
			session.flush();
			tx.commit();

			if (updateCount > 0) {
				return tokenDecision.getTokenToStartGenerateInteger();
			} else {
				return selectUpdateIntTokenDecision();
			}

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session != null) {
				session.close();
			}
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}

	}

	public String selectUpdateStrTokenDecision(String vaultIdentifier) throws TokenException {
		TokenDecision tokenDecision = null;
		Transaction tx = null;
		Session session = null;
		List<TokenDecision> listTokenDecisions = null;
		String tokenToInsert = null;
		int updateCount = 0;
		try {
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			session.clear();
			Query namedQuery = session.getNamedQuery("getTokenDecisionDetails");
			namedQuery.setParameter("CONFIGIDENTIFIER", configurationIdentifier);
			listTokenDecisions = namedQuery.list();

			if (!listTokenDecisions.isEmpty()) {
				tokenDecision = listTokenDecisions.get(0);
			}
			session.flush();
			String token = TokenUtil.appendIdentifier(vaultIdentifier, tokenDecision.getTokenToStartGenerateString());
			tokenToInsert = TokenUtil.generateRealTimeString(vaultIdentifier,
					tokenDecision.getTokenToStartGenerateString());
			tokenToInsert = tokenToInsert.substring(3);
			long nextValue = tokenDecision.getUpdateCount() + 1;
			String hql = "UPDATE TokenDecision SET TokenToStartGenerateString =:TOKEN,UpdateCount =:updateCount  WHERE ConfigurationIdentifier =:CONFIG_IDENTIFIER and UpdateCount =:CURRENT_UpdateCount";
			Query query = session.createQuery(hql);
			query.setParameter("TOKEN", tokenToInsert);
			query.setParameter("updateCount", nextValue);
			query.setParameter("CONFIG_IDENTIFIER", configurationIdentifier);
			query.setParameter("CURRENT_UpdateCount", tokenDecision.getUpdateCount());
			updateCount = query.executeUpdate();
			session.flush();
			tx.commit();
			if (updateCount > 0) {
				return token;
			} else {
				return selectUpdateStrTokenDecision(vaultIdentifier);
			}

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session != null) {
				session.close();
			}
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}

	}

	public Timestamp selectUpdateDateTokenDecision(int endDate, int startDate) throws TokenException {
		TokenDecision tokenDecision = null;
		Transaction tx = null;
		Session session = null;
		List<TokenDecision> listTokenDecisions = null;
		Timestamp tokenToInsert = null;
		int updateCount = 0;
		try {
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			session.clear();
			Query namedQuery = session.getNamedQuery("getTokenDecisionDetails");
			namedQuery.setParameter("CONFIGIDENTIFIER", configurationIdentifier);
			listTokenDecisions = namedQuery.list();

			if (!listTokenDecisions.isEmpty()) {
				tokenDecision = listTokenDecisions.get(0);
			}
			session.flush();
			SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
			String valueToStartDate = dateFormat.format(tokenDecision.getTokenToStartGenerateDateTime());
			long nextValue = tokenDecision.getUpdateCount() + 1;
			tokenToInsert = TokenUtil.generateRealTimeDate(endDate, startDate, valueToStartDate);
			String hql = "UPDATE TokenDecision SET tokenToStartGenerateDateTime =:TOKEN,UpdateCount =:updateCount  WHERE ConfigurationIdentifier =:CONFIG_IDENTIFIER and UpdateCount =:CURRENT_UpdateCount";
			Query query = session.createQuery(hql);
			query.setParameter("TOKEN", tokenToInsert);
			query.setParameter("updateCount", nextValue);
			query.setParameter("CONFIG_IDENTIFIER", configurationIdentifier);
			query.setParameter("CURRENT_UpdateCount", tokenDecision.getUpdateCount());
			updateCount = query.executeUpdate();
			session.flush();
			tx.commit();
			if (updateCount > 0) {
				return tokenDecision.getTokenToStartGenerateDateTime();
			} else {
				return selectUpdateDateTokenDecision(endDate, startDate);
			}

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session != null) {
				session.close();
			}
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}

	}

}