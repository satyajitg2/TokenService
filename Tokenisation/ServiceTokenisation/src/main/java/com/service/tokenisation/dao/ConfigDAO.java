package com.service.tokenisation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.model.Configuration;
import com.service.tokenisation.utility.Constants;

/**
 * 
 * The ConfigDAO is the Configuration Data Access Object responsible for INSERT/UPDATE/DELETE/SELECT operation on the
 * Configuration database Object.
 * 
 * @author: Satyajit
 * @version: 1
 */

public class ConfigDAO {
	private static final Logger LOG = Logger.getLogger(ConfigDAO.class);
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
	public Configuration getConfigDetails() throws TokenException {

		Configuration config = null;
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.clear();
			Query configQuery = session.getNamedQuery("config.buildConfigForBusinessEntity");
			configQuery.setParameter("OWNINGBUSINESSENTITY", owningBusinessEntity.toUpperCase());
			if (configQuery.list() != null) {
				config = (Configuration) configQuery.list().get(0);
			}
			tx.commit();
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
		return config;
	}

	/**
	 * This method fetch complete details from configuration table
	 * 
	 * @return List of of Configuration objects.
	 * @throws TokenException
	 */
	public List<Configuration> getConfigurationDetails() throws TokenException {

		Transaction tx = null;
		Session session = null;
		List<Configuration> listConfiguration = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query configQuery = session.getNamedQuery("config.getAll");
			listConfiguration = configQuery.list();
			tx.commit();

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
		return listConfiguration;

	}

	/**
	 * method fetch configuration Identifier from configuration table based on region
	 * 
	 * @param region
	 * @return
	 * @throws TokenException
	 */
	public int getConfigurationDetailByRegion(String region) throws TokenException {

		Transaction tx = null;
		Session session = null;
		int configurationIdentifier = 0;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			StringBuilder sbld = new StringBuilder();
			sbld.append("SELECT ConfigurationIdentifier");
			sbld.append(" FROM Configuration,BusinessEntity");
			sbld.append(" WHERE Configuration.businessEntityIdentifier=BusinessEntity.BusinessEntityIdentifier ");
			sbld.append(" AND BusinessEntity.BusinessEntityName =").append(region);
			Query namedQuery = session.createQuery(sbld.toString());

			if (namedQuery.uniqueResult() != null) {
				configurationIdentifier = (int) namedQuery.uniqueResult();
			}
			session.flush();
			tx.commit();
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
		return configurationIdentifier;

	}

}
