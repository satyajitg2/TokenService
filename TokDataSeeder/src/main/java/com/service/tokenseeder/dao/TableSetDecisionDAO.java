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
import com.service.tokenseeder.exception.TokenException;
import com.service.tokenseeder.model.TokenSetDecision;


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
	 * @throws TokenException 
	 */
	public List<TokenSetDecision> getSetDecisionDetails(Session session) throws TokenException {
		List<TokenSetDecision> listTokenDecisions = null;
		try {
			Query setQuery = session.getNamedQuery("decision.getSet");
			setQuery.setParameter("TABLEINUSE", tableInUse);
			listTokenDecisions = setQuery.list();
			} catch (SQLGrammarException e) {
			
			LOGGER.error("SQLGrammar Exception getSetDecisionDetails - TableSetDecisionDAO", e);
			throw new TokenException("SQLGrammar Exception getSetDecisionDetails - TableSetDecisionDAO");
		} catch (HibernateException e) {
			
			LOGGER.error("Hibernate Exception getSetDecisionDetails - TableSetDecisionDAO", e);
			throw new TokenException("Hibernate Exception getSetDecisionDetails - TableSetDecisionDAO");
		} finally {
			session.flush();
			session.clear();
			
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
	public long getNumberofTokenInTable(Session session,String tableName, int configurationIdentifier) throws TokenException {
		long minvalue = 0L;
		try {
			
			StringBuilder sbld = new StringBuilder("SELECT count(*) FROM ");
			sbld.append(tableName);
			sbld.append(" WHERE Vault =").append(configurationIdentifier);
			sbld.append(" AND SeedUsedFlag =").append(Constants.SEED_NOTUSED);

			Query query = session.createQuery(sbld.toString());
			minvalue = (long) query.uniqueResult();
		} catch (SQLGrammarException e) {
			
			LOGGER.error("SQLGrammar Exception getNumberofTokenInTable - TableSetDecisionDAO", e);
			throw new TokenException("SQLGrammar Exception getNumberofTokenInTable - TableSetDecisionDAO");
		} catch (HibernateException e) {
			
			LOGGER.error("Hibernate Exception getNumberofTokenInTable - TableSetDecisionDAO", e);
			throw new TokenException("Hibernate Exception getNumberofTokenInTable - TableSetDecisionDAO");
		} finally {
			session.flush();
			session.clear();

		}
		return minvalue;
	}

}// end of class
