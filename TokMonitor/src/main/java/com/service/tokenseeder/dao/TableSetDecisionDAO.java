package com.service.tokenseeder.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.service.tokenseeder.constants.Constants;
import com.service.tokenseeder.model.TokenSetDecision;
import com.service.tokenseeder.utils.HibernateUtil;

/**
 * <p>
 * The <b> TableSetDecisionDAO </b> is TableSetDecision Data Access Object responsible for INSERT/UPDATE/DELETE/SELECT
 * operation on the TableSetDecision database Object.
 * </p>
 */

public class TableSetDecisionDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(TableSetDecisionDAO.class);

	private int tableInUse = 0;

	public TableSetDecisionDAO() {
	}

	public TableSetDecisionDAO(int tableInUse) {
		super();
		this.tableInUse = tableInUse;
	}

	/**
	 * <p>
	 * This method returns the Decision table details.
	 * </p>
	 * 
	 * @return - list of TokenSetDecision
	 */
	public List<TokenSetDecision> getSetDecisionDetails() {
		Transaction tx = null;
		Session session = null;
		List<TokenSetDecision> listTokenDecisions = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			Query setQuery = session.getNamedQuery("decision.getSet");
			setQuery.setParameter("TABLEINUSE", tableInUse);

			listTokenDecisions = setQuery.list();
			tx.commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception getSetDecisionDetails - TableSetDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception getSetDecisionDetails - TableSetDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}

		return listTokenDecisions;
	}

	/**
	 * This method returns the number of tokens in the table
	 * 
	 * @param tableName
	 *            - name of the table (SeedInteger<n>, SeedDateTime<n>, SeedString<n>).
	 * @param configurationIdentifier
	 *            - configuration identifier.
	 * @return - The number of tokens in the table.
	 */
	public long getNumberofTokenInTable(String tableName, int configurationIdentifier) {
		Transaction tx = null;
		Session session = null;
		long minvalue = 0L;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			StringBuilder sbld = new StringBuilder("SELECT count(*) FROM ");
			sbld.append(tableName);
			sbld.append(" WHERE Vault =").append(configurationIdentifier);
			sbld.append(" AND SeedUsedFlag =").append(Constants.SEED_NOTUSED);

			Query query = session.createQuery(sbld.toString());
			minvalue = (long) query.uniqueResult();

			tx.commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception getNumberofTokenInTable - TableSetDecisionDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception getNumberofTokenInTable - TableSetDecisionDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return minvalue;
	}

}// end of class
