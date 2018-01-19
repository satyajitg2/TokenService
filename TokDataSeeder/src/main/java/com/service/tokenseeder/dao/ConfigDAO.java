package com.service.tokenseeder.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.tokenseeder.exception.TokenException;
import com.service.tokenseeder.model.Configuration;

/**
 * 
 * The <b> ConfigDAO </b> is the Configuration Data Access Object responsible for INSERT/UPDATE/DELETE/SELECT operation
 * on the Configuration database Object.
 * 
 */

public class ConfigDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDAO.class);
	private String owningBusinessEntity = null;

	public ConfigDAO() {
	}

	public ConfigDAO(String owningBusinessEntity) {
		this.owningBusinessEntity = owningBusinessEntity;
	}

	/**
	 * This function fetch complete details from configuration table based on Region/BusinessEntity
	 * 
	 * @return the Configuration table representation
	 * @throws TokenException 
	 */
	public Configuration getConfigDetails(Session session) throws TokenException {
		Configuration config = null;
			try {
			Query configQuery = session.getNamedQuery("config.buildConfigForBusinessEntity");
			configQuery.setParameter("OWNINGBUSINESSENTITY",owningBusinessEntity.toUpperCase());
			if (configQuery.list() != null) {
				config = (Configuration) configQuery.list().get(0);
			}
			
		} catch (SQLGrammarException sqlExc) {
			LOGGER.error("SQLGrammar Exception getConfigDetails method", sqlExc);
			throw new TokenException("SQLGrammar Exception getConfigDetails method");
		} catch (HibernateException e) {
			LOGGER.error("Hibernate Exception getConfigDetails method", e);
			throw new TokenException("Hibernate Exception getConfigDetails method");
		} finally {
			session.flush();
			session.clear();
		}
		return config;

	}

	/**
	 * <p>
	 * This method fetch complete details from configuration table
	 * <p>
	 * 
	 * @return List of of Configuration objects.
	 * @throws TokenException 
	 */
	public List<Configuration> getConfigurationDetails(Session session) throws TokenException {
		List<Configuration> listConfiguration = null;
		try {
			Query configQuery = session.getNamedQuery("config.getAll");
			listConfiguration = configQuery.list();
			} catch (SQLGrammarException sqlExc) {
			session.flush();
			session.clear();
			LOGGER.error("SQLGrammar Exception getConfigurationDetails method", sqlExc);
			throw new TokenException("SQLGrammar Exception getConfigurationDetails method");
		} catch (HibernateException e) {
			session.flush();
			session.clear();
			LOGGER.error("Hibernate Exception getConfigurationDetails method", e);
			throw new TokenException("Hibernate Exception getConfigurationDetails method");
		} finally {
			session.flush();
			session.clear();
		}
		return listConfiguration;
	}

	/**
	 * method fetch configuration Identifier from configuration table based on region
	 * 
	 * @param region
	 * @return configuration Id
	 * @throws TokenException 
	 */
	public int getConfigurationDetailByRegion(Session session,String region) throws TokenException {
		int configurationIdentifier = 0;
		try {
			StringBuilder sbld = new StringBuilder();
			sbld.append("SELECT ConfigurationIdentifier");
			sbld.append(" FROM Configuration");
			sbld.append(" WHERE Configuration.businessEntityName=").append(region);
			Query namedQuery = session.createQuery(sbld.toString());
			if (namedQuery.uniqueResult() != null) {
				configurationIdentifier = (int) namedQuery.uniqueResult();
			}
			session.flush();
			
		} catch (SQLGrammarException sqlExc) {
			session.flush();
			session.clear();
			LOGGER.error("SQLGrammar Exception getConfigurationDetailByRegion method", sqlExc);
			throw new TokenException("SQLGrammar Exception getConfigurationDetailByRegion method");
		} catch (HibernateException e) {
			session.flush();
			session.clear();
			LOGGER.error("Hibernate Exception getConfigurationDetailByRegion method", e);
			throw new TokenException("Hibernate Exception getConfigurationDetailByRegion method");
		} finally {
			session.flush();
			session.clear();
		}
		return configurationIdentifier;
	}

	/**
	 * This method fetch complete list of BusinessEntity/Region from configuration as a List
	 * 
	 * @return List of BusinessEntity/Region
	 * @throws TokenException 
	 */
	public List<String> getRegions(Session session) throws TokenException {
		List<String> bEntity = null;
		try {
			
			Query businessEntityList = session.createQuery("SELECT C.businessEntityName FROM Configuration C");
			bEntity = businessEntityList.list();
		} catch (SQLGrammarException e) {
			LOGGER.error("SQLGrammar Exception getRegions method", e);
			throw new TokenException("SQLGrammar Exception getRegions method");
		} catch (HibernateException e) {
			LOGGER.error("Hibernate Exception getRegions method", e);
			throw new TokenException("Hibernate Exception getRegions method");
		} finally {
			session.flush();
			session.clear();
		}
		return bEntity;
	}
}// end of class
