package com.service.tokenisation.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.JDBCConnectionException;

import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.model.DetokeniseRequestData;
import com.service.tokenisation.model.TokenDateTime;
import com.service.tokenisation.model.TokenInteger;
import com.service.tokenisation.model.TokenString;

/**
 * DetokenisationDAO class This class is responsible for Database operations for Detokenisation Service
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class DetokenisationDAO {

	private static final Logger LOG = Logger.getLogger(DetokenisationDAO.class);

	/*
	 * * This method will getCsdFromVault
	 * 
	 * @param map :token map
	 * 
	 * @param tableName :vault table name
	 * 
	 * @param tokenList :token list
	 * 
	 * @return HashMap<String,String>: True:- If Authorized, False:- If Unauthorized
	 * 
	 * @exception TokenException
	 */
	public boolean getCsdFromVault(Map<String, String> map, String tableName, List tokenList, String domainName)
			throws TokenException {
		LOG.debug("Invoking getCsdFromVault");
		Session session = null;
		try {
			session = HibernateUtil.getVaultSession();
			Query query = null;
			if (tableName.equalsIgnoreCase(Constants.TABLE_STRING)) {
				query = session.createQuery("FROM TokenString tv WHERE tv.token in :tokenValue and upper(domainName) = :domainName");
				query.setParameterList("tokenValue", tokenList);
				query.setParameter("domainName", domainName.toUpperCase());
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_INTEGER)) {
				query = session.createQuery("FROM TokenInteger tv WHERE tv.token in :tokenValue and upper(domainName) = :domainName");
				query.setParameterList("tokenValue", tokenList);
				query.setParameter("domainName", domainName.toUpperCase());
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_DATETIME)) {
				query = session.createQuery("FROM TokenDateTime tv WHERE tv.token in :tokenValue and upper(domainName) = :domainName");
				List dateToken = new ArrayList<>();
				for (int i = 0; i < tokenList.size(); i++) {
					SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
					Date date = formatter1.parse((String) tokenList.get(i));
					java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
					dateToken.add(timeStampDate);
				}
				query.setParameterList("tokenValue", dateToken);
				query.setParameter("domainName", domainName.toUpperCase());
			}
			List csdList = query.list();
			if (!csdList.isEmpty()) {
				return addCsdToMap(map, csdList, tableName, domainName);
			}
		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (session != null) {
				session.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (session != null) {
				session.close();
			}
			throw new TokenException(Constants.VAULT_ERROR);
		} catch (ParseException pe) {
			LOG.error(Constants.EXCEPTION, pe);
			if (session != null) {
				session.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return false;
	}

	/**
	 * This method will getCsdFromMCOVault
	 * 
	 * @param map
	 *            :token map
	 * @param tableName
	 *            :vault table name
	 * @param tokenList
	 *            :token list
	 * @return HashMap<String,String>: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean getCsdFromMCOVault(Map<String, String> map, String tableName, List tokenList, String domainName)
			throws TokenException {
		LOG.debug("Invoking getCsdFromMCOVault");
		Session session = null;
		try {
			session = HibernateUtil.getMCOVaultSession();
			Query query = null;
			if (tableName.equalsIgnoreCase(Constants.TABLE_STRING)) {
				query = session.createQuery("FROM TokenString tv WHERE tv.token in :tokenValue and upper(domainName) = :domainName");
				query.setParameterList("tokenValue", tokenList);
				query.setParameter("domainName", domainName.toUpperCase());
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_INTEGER)) {
				query = session.createQuery("FROM TokenInteger tv WHERE tv.token in :tokenValue and upper(domainName) = :domainName");
				query.setParameterList("tokenValue", tokenList);
				query.setParameter("domainName", domainName.toUpperCase());
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_DATETIME)) {
				query = session.createQuery("FROM TokenDateTime tv WHERE tv.token in :tokenValue and upper(domainName) = :domainName");
				List dateToken = new ArrayList<>();
				for (int i = 0; i < tokenList.size(); i++) {
					SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
					Date date = formatter1.parse((String) tokenList.get(i));
					java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
					dateToken.add(timeStampDate);
				}
				query.setParameterList("tokenValue", dateToken);
				query.setParameter("domainName", domainName.toUpperCase());
			}

			List csdList = query.list();
			if (!csdList.isEmpty()) {
				return addCsdToMap(map, csdList, tableName, domainName);
			}
		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (session != null) {
				session.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error("Exception: ", e);
			if (session != null) {
				session.close();
			}
			throw new TokenException(Constants.VAULT_ERROR);
		} catch (ParseException pe) {
			LOG.error(Constants.EXCEPTION, pe);
			if (session != null) {
				session.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return false;
	}

	/**
	 * This method will add csd to map
	 * 
	 * @param map
	 *            :token map
	 * @param csdList
	 *            :csd received from vault
	 * @param tableName
	 *            :vault table name
	 * @return
	 * @exception TokenException
	 */
	private boolean addCsdToMap(Map<String, String> map, List csdList, String tableName, String domainName)
			throws TokenException {
		LOG.debug("Invoking addCsdToMap");
		boolean noAuthFlag = false;
		List<String> businessEntity = getDataFromApiAuth(domainName);

		for (Map.Entry<String, String> entry : map.entrySet()) {
			for (int j = 0; j < csdList.size(); j++) {
				if (tableName.equals(Constants.TABLE_INTEGER)) {
					TokenInteger tokenInteger = (TokenInteger) csdList.get(j);
					if (entry.getKey().equals(String.valueOf(tokenInteger.getToken()))) {
						if (containsIgnoreCase(businessEntity, tokenInteger.getBusinessEntityName())) {
							map.put(entry.getKey(), tokenInteger.getcSDValue());
							break;
						} else {
							map.put(entry.getKey(), entry.getKey());
							noAuthFlag = true;
							break;
						}

					}
				} else if (tableName.equals(Constants.TABLE_STRING)) {
					TokenString tokenString = (TokenString) csdList.get(j);
					if (entry.getKey().equals(String.valueOf(tokenString.getToken()))) {
						if (containsIgnoreCase(businessEntity, tokenString.getBusinessEntityName())) {
							map.put(entry.getKey(), tokenString.getcSDValue());
							break;
						} else {
							map.put(entry.getKey(), entry.getKey());
							noAuthFlag = true;
							break;
						}
					}
				} else if (tableName.equals(Constants.TABLE_DATETIME)) {
					TokenDateTime tokenDateTime = (TokenDateTime) csdList.get(j);
					if (entry.getValue().equals(Constants.DATA_TYPE_DATETIME)) {
						java.sql.Timestamp timeStampDate;
						try {
							SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
							Date date = formatter1.parse(entry.getKey());
							timeStampDate = new Timestamp(date.getTime());
						} catch (ParseException e) {
							throw new TokenException(Constants.SYSTEM_ERROR);
						}
						long tokenInVault = tokenDateTime.getToken().getTime();
						long tokenReceived = timeStampDate.getTime();
						if (tokenInVault == tokenReceived) {
							if (containsIgnoreCase(businessEntity, tokenDateTime.getBusinessEntityName())) {
								map.put(entry.getKey(), tokenDateTime.getcSDValue());
								break;
							} else {
								map.put(entry.getKey(), entry.getKey());
								noAuthFlag = true;
								break;
							}
						}

					}
				}
			}

		}
		return noAuthFlag;
	}

	public boolean containsIgnoreCase(List<String> businessEntityList, String businessEntity) {
		Iterator<String> it = businessEntityList.iterator();
		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(businessEntity))
				return true;
		}
		return false;
	}

	/**
	 * This method will fetch list of authorized business entities from APIAuth table
	 * 
	 * @return List<String: List of Authorized Business Entities
	 * @exception TokenException
	 */
	public List<String> getDataFromApiAuth(String domainName) throws TokenException {
		LOG.debug(" Invoking getDataFromApiAuth");
		Session commonSession = HibernateUtil.getSession();

		try {
			Query query = commonSession.getNamedQuery("getAuthorizedBusinessEntityList");
			query.setParameter("DomainName", domainName.toUpperCase());
			query.setParameter("APIName", Constants.API_DETOKENISATION_NAME.toUpperCase());
			List<String> businessEntityList = query.list();
			if (!businessEntityList.isEmpty()) {
				return businessEntityList;
			} else {
				throw new TokenException(Constants.API_NOAUTH);
			}
		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}

	}

	/**
	 * This method will check the Authorization for DeTokenisation Request
	 * 
	 * @param requestData
	 *            :Detoken Request Data Object
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorised(DetokeniseRequestData detokeniseRequestData) throws TokenException {
		LOG.debug(" Invoking isAuthorised");
		Session commonSession = HibernateUtil.getSession();
		try {
			Query query = commonSession.getNamedQuery("authorisation.checkAuthorisation");
			query.setParameter("DOMAINNAME", detokeniseRequestData.getDomain().toUpperCase());
			query.setParameter("APINAME", Constants.API_DETOKENISATION_NAME.toUpperCase());
			List list = query.list();
			if (!list.isEmpty()) {
				return true;
			}
			return false;
		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

	/**
	 * This method will check the Authorization for DeTokenisation Request
	 * 
	 * @param requestData
	 *            :Detoken Request Data Object
	 * @param requestData
	 *            :callingSystemName
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorisedCallingSystem(DetokeniseRequestData detokeniseRequestData, String callingSystemName)
			throws TokenException {
		LOG.debug(" Invoking isAuthorisedCallingSystem");
		Session commonSession = HibernateUtil.getSession();
		try {
			Query query = commonSession.getNamedQuery("authorisation.checkAuthorisationUsingCert");
			query.setParameter("DOMAINNAME", detokeniseRequestData.getDomain().toUpperCase());
			query.setParameter("APINAME", Constants.API_DETOKENISATION_NAME.toUpperCase());
			query.setParameter("CALLINGSYSTENMANE", callingSystemName.toUpperCase());
			List list = query.list();
			if (!list.isEmpty()) {
				return true;
			}
			return false;
		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

}
