package com.service.tokenseeder.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.tokenseeder.model.TokenDecision;
import com.service.tokenseeder.utils.HibernateUtil;

/**
 * <p>
 * The <b> TokenDecisionDAO <b> class performs operation on TokenDecision table.
 * <p>
 * 
 * @version: 1.0
 */
public class TokenDecisionDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenDecisionDAO.class);
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
	 */
	public TokenDecision getTokenDecisionDetails() {
		TokenDecision tokenDecision = null;
		Transaction tx = null;
		Session session = null;
		List<TokenDecision> listTokenDecisions = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();

			Query namedQuery = session.getNamedQuery("getTokenDecisionDetails");
			namedQuery.setParameter("CONFIGIDENTIFIER", configurationIdentifier);

			listTokenDecisions = namedQuery.list();

			if (!listTokenDecisions.isEmpty()) {
				tokenDecision = listTokenDecisions.get(0);
			}

			tx.commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception getTokenDecisionDetails - TokenDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception getTokenDecisionDetails - TokenDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}

		return tokenDecision;
	}

	/**
	 * This function updates TokenDecision table based on Integer Token To generate next time
	 * 
	 * @param token
	 */
	public void updateIntegerTokenDecision(long token) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query namedUpdateQuery = session.getNamedQuery("update.TokenDecision.TokenToStartGenerateInteger");
			namedUpdateQuery.setParameter("TOKEN", token);
			namedUpdateQuery.setParameter("CONFIG_IDENTIFIER", this.configurationIdentifier);
			namedUpdateQuery.executeUpdate();
			tx.commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception updateIntegerTokenDecision - TokenDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception updateIntegerTokenDecision - TokenDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	/**
	 * <p>
	 * This function updates TokenDecision table based on String Token To generate next time
	 * <p>
	 * 
	 * @param token
	 *            - updates the TokenDecision table
	 */
	public void updateStringTokenDecision(String token) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query namedUpdateQuery = session.getNamedQuery("update.TokenDecision.TokenToStartGenerateString");
			namedUpdateQuery.setParameter("TOKEN", token);
			namedUpdateQuery.setParameter("CONFIG_IDENTIFIER", this.configurationIdentifier);
			namedUpdateQuery.executeUpdate();
			tx.commit();
		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception updateStringTokenDecision - TokenDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception updateStringTokenDecision - TokenDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	/**
	 * <p>
	 * This function update TokenDecision table based on DateTime Token To generate next time
	 * <p>
	 * 
	 * @param token
	 */
	public void updateDateTimeTokenDecision(Timestamp token) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String hql = "update TokenDecision set tokenToStartGenerateDateTime =:token where configurationIdentifier =:configurationIdentifier";
			Query q = session.createQuery(hql);
			q.setParameter("token", token);
			q.setParameter("configurationIdentifier", configurationIdentifier);
			q.executeUpdate();
			tx.commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception updateDateTimeTokenDecision - TokenDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception updateDateTimeTokenDecision - TokenDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}
	}

	/**
	 * <p>
	 * This method updates TokenDecision table based on Integer,String and DateTime Token To generate next time
	 * <p>
	 * 
	 * @param intToken
	 * @param strToken
	 * @param dateToken
	 */
	public int updateTokenDecision(long intToken, String strToken, Timestamp dateToken) {
		Transaction tx = null;
		Session session = null;
		int result=0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String hql = "update TokenDecision set tokenToStartGenerateInteger =:intToken ,tokenToStartGenerateString =:strToken,tokenToStartGenerateDateTime=:dateToken where configurationIdentifier =:configurationIdentifier";
			Query q = session.createQuery(hql);
			q.setParameter("intToken", intToken);
			q.setParameter("strToken", strToken);
			q.setParameter("dateToken", dateToken);
			q.setParameter("configurationIdentifier", configurationIdentifier);
			result=q.executeUpdate();
			tx.commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception updateTokenDecision - TokenDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception updateTokenDecision - TokenDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return result;
	}

	/**
	 * <p>
	 * This function updates the TokenDecision table based on Table Set to use next time.
	 * <p>
	 * 
	 * @param tokenEngine
	 * @param tokenGenerator
	 */
	public int updateTableSet(int tokenEngine, int tokenGenerator) {
		Transaction tx = null;
		Session session = null;
		int result=0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();

			String hql = "update TokenDecision set tableSetToUseByTokenEngine =:tokenEngine , tableSetToUseByTokenSeeder=:tokenGenerator";
			Query q = session.createQuery(hql);
			q.setParameter("tokenEngine", tokenEngine);
			q.setParameter("tokenGenerator", tokenGenerator);
			result=q.executeUpdate();

			tx.commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception updateTableSet - TokenDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception updateTableSet - TokenDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return result;

	}// end of method

}// end of class