package com.service.tokenseeder.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.service.tokenseeder.model.Configuration;
import com.service.tokenseeder.utils.HibernateUtil;

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
	 */
	public Configuration getConfigDetails() {
		Configuration config = null;
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query configQuery = session.getNamedQuery("config.buildConfigForBusinessEntity");
			configQuery.setParameter("OWNINGBUSINESSENTITY", owningBusinessEntity);
			if (configQuery.list() != null) {
				config = (Configuration) configQuery.list().get(0);
			}
			tx.commit();
		} catch (SQLGrammarException sqlExc) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception getConfigDetails method", sqlExc);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception getConfigDetails method", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return config;

	}

	/**
	 * <p>
	 * This method fetch complete details from configuration table
	 * <p>
	 * 
	 * @return List of of Configuration objects.
	 */
	public List<Configuration> getConfigurationDetails() {
		Transaction tx = null;
		Session session = null;
		List<Configuration> listConfiguration = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query configQuery = session.getNamedQuery("config.getAll");
			listConfiguration = configQuery.list();
			tx.commit();
		} catch (SQLGrammarException sqlExc) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception getConfigurationDetails method", sqlExc);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception getConfigurationDetails method", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listConfiguration;
	}

	/**
	 * method fetch configuration Identifier from configuration table based on region
	 * 
	 * @param region
	 * @return configuration Id
	 */
	public int getConfigurationDetailByRegion(String region) {
		Transaction tx = null;
		Session session = null;
		int configurationIdentifier = 0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			StringBuilder sbld = new StringBuilder();
			sbld.append("SELECT ConfigurationIdentifier");
			sbld.append(" FROM Configuration");
			sbld.append(" WHERE Configuration.businessEntityName=").append(region);
			Query namedQuery = session.createQuery(sbld.toString());
			if (namedQuery.uniqueResult() != null) {
				configurationIdentifier = (int) namedQuery.uniqueResult();
			}
			session.flush();
			tx.commit();
		} catch (SQLGrammarException sqlExc) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception getConfigurationDetailByRegion method", sqlExc);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernate Exception getConfigurationDetailByRegion method", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return configurationIdentifier;
	}
/**
 * This method fetch complete list of BusinessEntity/Region from configuration as a List
 * 
 * @return List of BusinessEntity/Region
 */
	public List<String> getRegions() {
		Session session1 = null;
		List<String> bEntity = null;
		try {
			session1 = HibernateUtil.getSessionFactory().openSession();
			Query businessEntityList = session1.createQuery("SELECT C.businessEntityName FROM Configuration C");
			bEntity = businessEntityList.list();
		} catch (SQLGrammarException e) {
			LOGGER.error("SQLGrammar Exception getRegions method", e);
		} catch (HibernateException e) {
			LOGGER.error("Hibernate Exception getRegions method", e);
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return bEntity;
	}
}// end of class
