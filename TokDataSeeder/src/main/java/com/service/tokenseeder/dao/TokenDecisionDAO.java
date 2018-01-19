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

import com.service.tokenseeder.exception.TokenException;
import com.service.tokenseeder.model.TokenDecision;


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
	 * @throws TokenException 
	 */
	public TokenDecision getTokenDecisionDetails(Session session) throws TokenException {
		TokenDecision tokenDecision = null;
		List<TokenDecision> listTokenDecisions = null;
		try {
			Query namedQuery = session.getNamedQuery("getTokenDecisionDetails");
			namedQuery.setParameter("CONFIGIDENTIFIER", configurationIdentifier);

			listTokenDecisions = namedQuery.list();

			if (!listTokenDecisions.isEmpty()) {
				tokenDecision = listTokenDecisions.get(0);
			}
		} catch (SQLGrammarException e) {
			
			LOGGER.error("SQLGrammar Exception getTokenDecisionDetails - TokenDecisionDAO", e);
          throw new TokenException("SQLGrammar Exception getTokenDecisionDetails - TokenDecisionDAO");
		} catch (HibernateException e) {
			
			LOGGER.error("Hibernate Exception getTokenDecisionDetails - TokenDecisionDAO", e);
			throw new TokenException("Hibernate Exception getTokenDecisionDetails - TokenDecisionDAO");
		} finally {
			session.flush();
			session.clear();
		}

		return tokenDecision;
	}

	
	/**
	 * <p>
	 * This method updates TokenDecision table based on Integer,String and DateTime Token To generate next time
	 * <p>
	 * 
	 * @param intToken
	 * @param strToken
	 * @param dateToken
	 * @throws TokenException 
	 */
	public int updateTokenDecision(Session session,long intToken, String strToken, Timestamp dateToken) throws TokenException {
		int result=0;
		try {
			String hql = "update TokenDecision set tokenToStartGenerateInteger =:intToken ,tokenToStartGenerateString =:strToken,tokenToStartGenerateDateTime=:dateToken,nextRotationTime=:nexttime where configurationIdentifier =:configurationIdentifier";
			Query q = session.createQuery(hql);
			q.setParameter("intToken", intToken);
			q.setParameter("strToken", strToken);
			q.setParameter("dateToken", dateToken);
			q.setParameter("nexttime", new Timestamp(System.currentTimeMillis()));
			q.setParameter("configurationIdentifier", configurationIdentifier);
			result=q.executeUpdate();
					} catch (SQLGrammarException e) {
			
			LOGGER.error("SQLGrammar Exception updateTokenDecision - TokenDecisionDAO", e);
			throw new TokenException("SQLGrammar Exception updateTokenDecision - TokenDecisionDAO");
		} catch (HibernateException e) {
			
			LOGGER.error("Hibernate Exception updateTokenDecision - TokenDecisionDAO", e);
			throw new TokenException("Hibernate Exception updateTokenDecision - TokenDecisionDAO");
		} finally {
			session.flush();
			session.clear();
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
	 * @throws TokenException 
	 */
	public int updateTableSet(Session session,int tokenEngine, int tokenGenerator) throws TokenException {
		
		int result=0;
		try {
			String hql = "update TokenDecision set tableSetToUseByTokenEngine =:tokenEngine , tableSetToUseByTokenSeeder=:tokenGenerator";
			Query q = session.createQuery(hql);
			q.setParameter("tokenEngine", tokenEngine);
			q.setParameter("tokenGenerator", tokenGenerator);
			result=q.executeUpdate();

		} catch (SQLGrammarException e) {
			
			LOGGER.error("SQLGrammar Exception updateTableSet - TokenDecisionDAO", e);
			throw new TokenException("SQLGrammar Exception updateTableSet - TokenDecisionDAO");
		} catch (HibernateException e) {
			
			LOGGER.error("Hibernate Exception updateTableSet - TokenDecisionDAO", e);
			throw new TokenException("Hibernate Exception updateTableSet - TokenDecisionDAO");
		} finally {
			session.flush();
			session.clear();
		}
		return result;

	}// end of method

}// end of class