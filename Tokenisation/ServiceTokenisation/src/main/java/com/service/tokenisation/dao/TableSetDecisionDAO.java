package com.service.tokenisation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.model.TokenSetDecision;
import com.service.tokenisation.utility.Constants;

/**
 * <p>
 * The <b> TableSetDecisionDAO </b> is TableSetDecision Data Access Object responsible for INSERT/UPDATE/DELETE/SELECT
 * operation on the TableSetDecision database Object.
 * </p>
 */

public class TableSetDecisionDAO {
	private static final Logger LOG = Logger.getLogger(TableSetDecisionDAO.class);
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
	 * @throws TokenException
	 */
	public List<TokenSetDecision> getSetDecisionDetails() throws TokenException {
		Transaction tx = null;
		Session session = null;
		List<TokenSetDecision> listTokenDecisions = null;
		try {
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();
			Query setQuery = session.getNamedQuery("decision.getSet");
			setQuery.setParameter("TABLEINUSE", tableInUse);

			listTokenDecisions = setQuery.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session != null) {
				session.close();
			}
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
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
	 * @throws TokenException
	 */
	public long getNumberofTokenInTable(String tableName, int configurationIdentifier) throws TokenException {
		Transaction tx = null;
		Session session = null;
		long minvalue = 0L;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			StringBuilder sbld = new StringBuilder("SELECT count(*) FROM ");
			sbld.append(tableName);
			sbld.append(" WHERE Vault =").append(configurationIdentifier);
			sbld.append(" AND SeedUsedFlag =").append(Constants.SEED_NOTUSED);

			Query query = session.createQuery(sbld.toString());
			minvalue = (long) query.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session != null) {
				session.close();
			}
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return minvalue;
	}

}
