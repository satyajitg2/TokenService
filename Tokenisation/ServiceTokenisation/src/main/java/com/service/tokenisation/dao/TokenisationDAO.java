package com.service.tokenisation.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;

import com.service.tokenisation.controller.tokenisation.TokenisationController;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.tokengenerator.TokenDateImpl;
import com.service.tokenisation.tokengenerator.TokenIntImpl;
import com.service.tokenisation.tokengenerator.TokenStringImpl;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.model.CsdRequest;
import com.service.tokenisation.model.RequestData;
import com.service.tokenisation.model.TokenDateTime;
import com.service.tokenisation.model.TokenInteger;
import com.service.tokenisation.model.TokenString;

/**
 * TokenisationDAO class This class is responsible for Database operations for Tokenisation
 * 
 * @author: Satyajit Singh
 * @version: 2 Modified For Performance Improvement 20 April 2016
 */
public class TokenisationDAO {

	private static final Logger LOG = Logger.getLogger(TokenisationDAO.class);
	public static final int MYSQL_DUPLICATE_PK = 2627;

	/**
	 * This method is responsible for getting Repeatable Token from the Vault
	 * 
	 * @param csdReqObj
	 *            : CSD object
	 * @param reqFieldValue
	 *            : CSD for which token is required
	 * @param reqTokenTyp
	 *            : Token Type (String/Integer/DateTime)
	 * @param requestData
	 *            : Request Data Object
	 * @return String: Repeatable token fetched from the Vault
	 * @exception Exception
	 *                : Hibernate Exception, Parse Exception
	 */

	public String getRepetableTokenFromVault(String reqFieldValue, String reqTokenTyp, CsdRequest csdReqObj,
			RequestData requestData) throws TokenException {

		LOG.debug("Invoking getRepetableTokenFromVault");

		String tokenType = reqTokenTyp;
		String fieldValue = reqFieldValue;
		String repetableToken = null;
		Session vaultSession;
		Transaction tx;
		if (requestData.getOwningBusinessEntity().equalsIgnoreCase(Constants.MCO)) {
			vaultSession = HibernateUtil.getMCOVaultSession();
			tx = vaultSession.beginTransaction();
		} else {
			vaultSession = HibernateUtil.getVaultSession();
			tx = vaultSession.beginTransaction();
		}

		try {
			Query query = null;
			if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {
				query = vaultSession.createQuery("FROM TokenString tv WHERE tv.cSDValue = :fieldValue and tv.repeatableFlag = True and upper(tv.domainName) = :domainName and upper(tv.businessEntityName) = :businessEntityName ");
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
				query = vaultSession.createQuery("FROM TokenInteger tv WHERE tv.cSDValue = :fieldValue and tv.repeatableFlag = True and upper(tv.domainName) = :domainName and upper(tv.businessEntityName) = :businessEntityName ");
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
				query = vaultSession.createQuery("FROM TokenDateTime tv WHERE tv.cSDValue = :fieldValue and tv.repeatableFlag = True and upper(tv.domainName) = :domainName and upper(tv.businessEntityName) = :businessEntityName ");
			}
			query.setParameter("fieldValue", fieldValue);
			query.setParameter("domainName", requestData.getDomain().toUpperCase());
			query.setParameter("businessEntityName", requestData.getOwningBusinessEntity().toUpperCase());
			List list = query.list();
			if (!list.isEmpty()) {
				Iterator itr = list.iterator();
				if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {

					List<TokenString> outputList = new ArrayList<TokenString>();
					while (itr.hasNext()) {
						TokenString tokenStringObj = (TokenString) itr.next();
						outputList.add(tokenStringObj);
					}
					if (!outputList.isEmpty()) {
						repetableToken = outputList.get(0).getToken();
						return repetableToken;
					}
				} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {

					List<TokenInteger> outputList = new ArrayList<TokenInteger>();
					while (itr.hasNext()) {
						TokenInteger tokenIntegerObj = (TokenInteger) itr.next();
						outputList.add(tokenIntegerObj);
					}
					if (!outputList.isEmpty()) {
						repetableToken = String.valueOf(outputList.get(0).getToken());
						return repetableToken;
					}
				} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {

					List<TokenDateTime> outputList = new ArrayList<TokenDateTime>();
					while (itr.hasNext()) {
						TokenDateTime tokenDateTimeObj = (TokenDateTime) itr.next();
						outputList.add(tokenDateTimeObj);
					}
					if (!outputList.isEmpty()) {

						DateFormat newDf = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
						repetableToken = newDf.format(outputList.get(0).getToken());
						return repetableToken;
					}
				}
			} else {
				String generatedToken = null;
				generatedToken = getUniqueTokenFromTokenBank(reqFieldValue, reqTokenTyp, csdReqObj, requestData);
				return generatedToken;
			}
		} catch (RuntimeException e) {
			LOG.error(Constants.EXCEPTION, e);
			try {
				if (tx != null) {
					tx.rollback();
				}
				if (vaultSession != null) {
					vaultSession.close();
				}
				throw new TokenException(Constants.VAULT_ERROR);
			} catch (RuntimeException rbe) {
				LOG.error("Couldn not roll back transaction", rbe);
			}
		}
		return null;
	}

	/**
	 * This method is responsible for getting Unique Token from the Token Bank
	 * 
	 * @param csdReqObj
	 *            : CSD object
	 * @param reqFieldValue
	 *            : CSD for which token is required
	 * @param reqTokenTyp
	 *            : Token Type (String/Integer/DateTime)
	 * @param requestData
	 *            : Request Data Object
	 * @return String: Unique token fetched from the Token Seed Table
	 * @exception TokenException
	 * @throws
	 */
	public String getUniqueTokenFromTokenBank(String reqFieldValue, String reqTokenTyp, CsdRequest csdReqObj,
			RequestData requestData) throws TokenException {

		LOG.debug("Invoking getUniqueTokenFromTokenBank");
		String uniqueToken = null;
		int seedTokenId = 0;
		boolean isFlagUpdated = false;
		boolean isMappingDone = false;

		String tokenType = reqTokenTyp;
		Session commonSession = HibernateUtil.getSession();
		Transaction tx = null;

		int configId;
		String businessEntityName = requestData.getOwningBusinessEntity();
		String tokenBankToUse = getTokenSeedTableForBusinessEntity(tokenType);

		try {
			tx = commonSession.beginTransaction();
			configId = TokenisationController.getConfigurationId();
			LOG.debug("getUniqueTokenFromTokenBank >>>>>>>> Start time: " + logTime(System.currentTimeMillis()));

			StringBuffer queryStr = new StringBuffer();
			
			queryStr.append("Select Top (1) Seed, SeedValue FROM [TECORE].");
			queryStr.append(tokenBankToUse);
			queryStr.append(" tb where tb.SeedUsedFlag = 0  and tb.Vault = "); 
			queryStr.append(configId );
			queryStr.append(" ORDER BY NEWID()");

			Query query = commonSession.createSQLQuery(queryStr.toString());
			List<Object[]> seedData = (List<Object[]>) query.list();

			if (seedData.isEmpty()) {
				/** Call the Token Generator */
				uniqueToken = getTokenFromTokenGeneratorService(tokenType, businessEntityName);
				/** Save Mapping to token vault */
				if (!uniqueToken.isEmpty() && !(uniqueToken == null)) {
					isMappingDone = saveMappingToTokenVault(uniqueToken, csdReqObj, requestData);
					if (isMappingDone) {
						return uniqueToken;
					}
				}
			} else {
				for (Object[] seedTable : seedData) {
					if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {
						seedTokenId = (Integer) seedTable[0];
						uniqueToken = (String) seedTable[1];
					} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
						seedTokenId = (Integer) seedTable[0];
						uniqueToken = String.valueOf(seedTable[1]);
					} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
						seedTokenId = (Integer) seedTable[0];
						DateFormat newDf = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
						String uniqueDateToken = newDf.format(seedTable[1]);
						uniqueToken = uniqueDateToken;
					}
				}
				LOG.debug("getUniqueTokenFromTokenBank >>>>>>>>> End time: " + logTime(System.currentTimeMillis()));
				/** Update the flag in Token Bank */
				isFlagUpdated = changeFlagToUsed(tokenBankToUse, seedTokenId, tokenType, commonSession);

				/** Save Mapping to token vault */
				if (isFlagUpdated) {
					isMappingDone = saveMappingToTokenVault(uniqueToken, csdReqObj, requestData);
					if (isMappingDone) {
						return uniqueToken;
					}
				}
			}

		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			if (e.toString().contains(Constants.DB_UNAVAILABLE)) {
				throw new TokenException(Constants.DB_UNAVAILABLE);
			} else if (e.toString().contains(Constants.SYSTEM_ERROR)) {
				throw new TokenException(Constants.SYSTEM_ERROR);
			} else if (e.toString().contains(Constants.VAULT_ERROR)) {
				throw new TokenException(Constants.VAULT_ERROR);
			} else {
				throw new TokenException(Constants.SYSTEM_ERROR);
			}
		}
		return null;
	}

	/**
	 * This method is responsible for creating Token on the fly when token bank runs out of token
	 * 
	 * @param tokenTyp
	 *            : Token Type (String/Integer/DateTime)
	 * @param businessEntityId
	 *            : Business Entity for which token is required
	 * @return String: Unique token created on the fly when token bank runs out of token
	 * @exception TokenException
	 */
	public String getTokenFromTokenGeneratorService(String tokenType, String businessEntityName) throws TokenException {
		LOG.debug(" Invoking getTokenFromTokenGeneratorService");
		String responseReceived = null;
		Timestamp dateToken = null;
		try {

			if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
				TokenDateImpl token = new TokenDateImpl();
				dateToken = token.generateOnDemandToken(businessEntityName);
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {
				TokenStringImpl token = new TokenStringImpl();
				responseReceived = token.generateOnDemandToken(businessEntityName);
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
				TokenIntImpl token = new TokenIntImpl();
				responseReceived = token.generateOnDemandToken(businessEntityName);
			}
			if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
				DateFormat newDf2 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
				String uniqueDateToken2 = newDf2.format(dateToken);
				responseReceived = uniqueDateToken2;
			}
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}
		return responseReceived;
	}

	/**
	 * This method will give Seed Table name from which token needs to be fetched
	 * 
	 * @param tokenTyp
	 *            : Token Type (String/Integer/DateTime)
	 * @return String: Seed Table name from which token needs to be fetched
	 * @exception TokenException
	 */
	public String getTokenSeedTableForBusinessEntity(String tokenType) throws TokenException {
		LOG.debug("Invoking getTokenSeedTableForBusinessEntity");
		int tokenSet;
		String tokenBankTableName = null;
		try {
			tokenSet = TokenisationController.getTokenSetToUse();
			if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {
				tokenBankTableName = Constants.TOKEN_BANK_STRING;
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
				tokenBankTableName = Constants.TOKEN_BANK_INTEGER;
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
				tokenBankTableName = Constants.TOKEN_BANK_DATE_TIME;
			}
			if (tokenSet == Constants.ONE) {
				return tokenBankTableName + Constants.ONE;
			} else if (tokenSet == Constants.TWO) {
				return tokenBankTableName + Constants.TWO;
			} else if (tokenSet == Constants.THREE) {
				return tokenBankTableName + Constants.THREE;
			}
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		return null;
	}

	/**
	 * This method will update the flag to used in Token Bank
	 * 
	 * @param tokenTyp
	 *            : Token Type (String/Integer/DateTime)
	 * @param tokenBankUsed
	 *            : Token Bank whose flag needs to be updated
	 * @param uniqueToken
	 *            : Unique token against which flag needs to be update
	 * @param commonSession2
	 * @return boolean: True:- If flag is successfully updated, False:- If update is fail
	 * @exception TokenException
	 */
	private boolean changeFlagToUsed(String tokenBankUsed, int seedTokenId, String tokenType, Session commonSession2)
			throws TokenException {
		LOG.debug("Invoking changeFlagToUsed");
		int result = 0;
		try {
			LOG.debug("changeFlagToUsed  >>>>>>>>Start time: " + logTime(System.currentTimeMillis()));
			String hql = "UPDATE " + tokenBankUsed + " set seedUsedFlag = True WHERE seed = :seedTokenId";
			Query query = commonSession2.createQuery(hql);
			query.setParameter("seedTokenId", seedTokenId);
			result = query.executeUpdate();
			commonSession2.flush();
			commonSession2.getTransaction().commit();
			LOG.debug("changeFlagToUsed >>>>>>>>> END time: " + logTime(System.currentTimeMillis()));
			if (result > 0) {
				return true;
			} else {
				throw new TokenException(Constants.DB_UNAVAILABLE);
			}

		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession2 != null) {
				commonSession2.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession2.getTransaction() != null) {
				commonSession2.getTransaction().rollback();
			}
			if (commonSession2 != null) {
				commonSession2.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession2.getTransaction() != null) {
				commonSession2.getTransaction().rollback();
			}
			if (commonSession2 != null) {
				commonSession2.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

	/**
	 * This method will save the mapping to Token Vault
	 * 
	 * @param uniqueToken
	 *            : Unique token against which flag needs to be update
	 * @param csdReqObj
	 *            : CSD object
	 * @param requestData
	 *            : Request Data Object
	 * @return boolean: True:- If mapping is saved successfully , False:- If saving fails
	 * @exception Exception
	 *                : Hibernate Exception, Parse Exception
	 */
	public boolean saveMappingToTokenVault(String uniqueToken, CsdRequest csdReqObj, RequestData requestData)
			throws TokenException {
		LOG.debug("Invoking saveMappingToTokenVault");
		String tokenType = csdReqObj.getTokenType();
		String domainName = requestData.getDomain();
		String systemName = requestData.getSourceSystemName();
		String businessEntityName = requestData.getOwningBusinessEntity();
		Session vaultSession;
		if (businessEntityName.equalsIgnoreCase(Constants.MCO)) {
			vaultSession = HibernateUtil.getMCOVaultSession();
		} else {
			vaultSession = HibernateUtil.getVaultSession();
		}
		Transaction tx = null;
		try {
			tx = vaultSession.beginTransaction();
			if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {

				TokenString objString = new TokenString();
				if (csdReqObj.getIsRepeatable().equalsIgnoreCase(Constants.REPETABLE_TRUE)) {
					objString.setRepeatableFlag(Constants.TRUE);
				} else {
					objString.setRepeatableFlag(Constants.FALSE);
				}

				if (!uniqueToken.isEmpty()) {
					objString.setToken(uniqueToken);
				}
				Calendar calendar = Calendar.getInstance();
				Timestamp timeStamp = new Timestamp(calendar.getTimeInMillis());
				objString.setTokenCreationDatetime(timeStamp);
				objString.setDomainName(domainName);
				objString.setSystemName(systemName);
				objString.setBusinessEntityName(businessEntityName);
				if (!csdReqObj.getFieldValue().isEmpty() && csdReqObj.getFieldValue() != null) {
					objString.setcSDValue(csdReqObj.getFieldValue());
				}
				if (!csdReqObj.getTargetFieldName().isEmpty() && csdReqObj.getTargetFieldName() != null) {
					objString.setTargetFieldName(csdReqObj.getTargetFieldName());
				}
				if (!csdReqObj.getSourceFieldName().isEmpty() && csdReqObj.getSourceFieldName() != null) {
					objString.setSourceFieldName(csdReqObj.getSourceFieldName());
				}
				if (csdReqObj.getMetadata() != null) {
					objString.setTokenMetadata(csdReqObj.getMetadata());
				}
				vaultSession.save(objString);
				vaultSession.flush();
				tx.commit();
				return true;
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
				TokenInteger objInteger = new TokenInteger();
				if (csdReqObj.getIsRepeatable().equalsIgnoreCase(Constants.REPETABLE_TRUE)) {
					objInteger.setRepeatableFlag(Constants.TRUE);
				} else {
					objInteger.setRepeatableFlag(Constants.FALSE);
				}
				objInteger.setDomainName(domainName);
				objInteger.setSystemName(systemName);
				objInteger.setBusinessEntityName(businessEntityName);
				if (!uniqueToken.isEmpty()) {
					objInteger.setToken(Long.parseLong(uniqueToken));
				}
				Calendar calendar = Calendar.getInstance();
				Timestamp timeStamp = new Timestamp(calendar.getTimeInMillis());
				objInteger.setTokenCreationDatetime(timeStamp);
				if (!csdReqObj.getFieldValue().isEmpty() && csdReqObj.getFieldValue() != null) {
					objInteger.setcSDValue(csdReqObj.getFieldValue());
				}
				if (!csdReqObj.getTargetFieldName().isEmpty() && csdReqObj.getTargetFieldName() != null) {
					objInteger.setTargetFieldName(csdReqObj.getTargetFieldName());
				}
				if (!csdReqObj.getSourceFieldName().isEmpty() && csdReqObj.getSourceFieldName() != null) {
					objInteger.setSourceFieldName(csdReqObj.getSourceFieldName());
				}
				if (csdReqObj.getMetadata() != null) {
					objInteger.setTokenMetadata(csdReqObj.getMetadata());
				}
				vaultSession.save(objInteger);
				vaultSession.flush();
				tx.commit();
				return true;
			} else if (tokenType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
				TokenDateTime objDateTime = new TokenDateTime();
				if (csdReqObj.getIsRepeatable().equalsIgnoreCase(Constants.REPETABLE_TRUE)) {
					objDateTime.setRepeatableFlag(Constants.TRUE);
				} else {
					objDateTime.setRepeatableFlag(Constants.FALSE);
				}
				objDateTime.setDomainName(domainName);
				objDateTime.setSystemName(systemName);
				objDateTime.setBusinessEntityName(businessEntityName);
				SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
				Date date = formatter1.parse(uniqueToken);
				java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
				objDateTime.setToken(timeStampDate);
				Calendar calendar = Calendar.getInstance();
				Timestamp timeStamp = new Timestamp(calendar.getTimeInMillis());
				objDateTime.setTokenCreationDatetime(timeStamp);
				if (!csdReqObj.getFieldValue().isEmpty() && csdReqObj.getFieldValue() != null) {
					objDateTime.setcSDValue(csdReqObj.getFieldValue());
				}
				if (!csdReqObj.getTargetFieldName().isEmpty() && csdReqObj.getTargetFieldName() != null) {
					objDateTime.setTargetFieldName(csdReqObj.getTargetFieldName());
				}
				if (!csdReqObj.getSourceFieldName().isEmpty() && csdReqObj.getSourceFieldName() != null) {
					objDateTime.setSourceFieldName(csdReqObj.getSourceFieldName());
				}
				if (csdReqObj.getMetadata() != null) {
					objDateTime.setTokenMetadata(csdReqObj.getMetadata());
				}
				vaultSession.save(objDateTime);
				tx.commit();
				vaultSession.flush();
				return true;
			}

		} catch (RuntimeException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (vaultSession.getTransaction() != null) {
				vaultSession.getTransaction().rollback();
			}
			if (vaultSession != null) {
				vaultSession.close();
			}
			throw new TokenException(Constants.VAULT_ERROR);
		} catch (ParseException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (vaultSession.getTransaction() != null) {
				vaultSession.getTransaction().rollback();
			}
			if (vaultSession != null) {
				vaultSession.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (vaultSession.getTransaction() != null) {
				vaultSession.getTransaction().rollback();
			}
			if (vaultSession != null) {
				vaultSession.close();
			}
			throw new TokenException(Constants.VAULT_ERROR);
		}
		return false;

	}

	/**
	 * This method will check the Authorization for Tokenisation Request
	 * 
	 * @param requestData
	 *            : Request Data Object
	 * @param CallingSystemName
	 *            : Read from Certificate
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @throws
	 * @exception Exception
	 *                : Hibernate Exception
	 */
	public boolean isAuthorised(RequestData requestData, String callingSystemName) throws TokenException {
		LOG.debug("Invoking isAuthorised");
		Session commonSession = HibernateUtil.getSession();
		Connection connection = null;

		try {
			String authorised;
			commonSession.beginTransaction();
			connection = commonSession.connection();
			CallableStatement callable = null;
			callable = connection.prepareCall("EXEC [TECORE].SP_CHECKAUTHORISATION ?,?,?,?,?");
			callable.setString(1, callingSystemName);
			callable.setString(2, requestData.getOwningBusinessEntity());
			callable.setString(3, requestData.getDomain());
			callable.setString(4, Constants.API_TOKENISATION_NAME);
			callable.registerOutParameter(5, Types.VARCHAR);
			callable.execute();
			authorised = callable.getString(5);
			connection.close();
			if (authorised.equalsIgnoreCase(Constants.AUTHORISED)) {
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
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					LOG.error(Constants.EXCEPTION, e);
				}
			}
		}
	}

	private static String logTime(long time) {

		final Calendar cal = Calendar.getInstance();
		String returnTime = "";
		cal.setTimeInMillis(0);
		cal.setTimeInMillis(time);

		returnTime = new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());

		return returnTime;

	}

	public int getConfigurationIdForBusinessEntity(String owningBusinessEntity) throws TokenException {
		LOG.debug(" Invoking getConfigurationIdForBE");
		int configId;
		Session commonSession = HibernateUtil.getSession();
		Transaction tx = commonSession.beginTransaction();
		try {
			LOG.debug("getConfigurationIdForBE Start time: " + logTime(System.currentTimeMillis()));
			Query query1 = commonSession.getNamedQuery("tokenisation.getConfigurationForBusinessEntity");
			query1.setString("BUSINESSENTITYNAME", owningBusinessEntity.toUpperCase());
			configId = (int) query1.uniqueResult();
			LOG.debug("getConfigurationIdForBE END time: " + logTime(System.currentTimeMillis()));
			return configId;
		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (tx != null) {
				tx.rollback();
			}
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}

	}

	/**
	 * This method will fetch Token Set to be used by service
	 * 
	 * @param configurationId
	 *            : Configuration id for requested Business Entity
	 * @param owningBusinessEntity
	 *            : Business Entity Coming in request
	 * @return integer: Token Set to use
	 * @exception TokenException
	 */
	public int getTokenSetToUse(int configurationId, String owningBusinessEntity) throws TokenException {
		LOG.debug("Invoking getTokenSetToUse");
		int result;
		Session commonSession = HibernateUtil.getSession();
		Transaction tx = commonSession.beginTransaction();
		try {
			LOG.debug("getTokenSetToUse >>>>>>>Start time: " + logTime(System.currentTimeMillis()));
			Query query = commonSession.getNamedQuery("tokenisation.getTokenSeedTableSetForBusinessEntity");
			query.setInteger("CONFIGURATIONID", configurationId);
			result = (int) query.uniqueResult();
			LOG.debug("getTokenSetToUse >>>>>>END time: " + logTime(System.currentTimeMillis()));
			return result;
		} catch (JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		} catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (tx != null) {
				tx.rollback();
			}
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}
	}

}
