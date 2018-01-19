package com.service.tokenisation.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.JDBCConnectionException;

import com.service.tokenisation.controller.retokenisation.TokenisationRetokenisation;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.utility.CommonUtility;
import com.service.tokenisation.utility.Constants;
import com.service.tokenisation.model.CsdRequest;
import com.service.tokenisation.model.CsdResponse;
import com.service.tokenisation.model.RequestData;
import com.service.tokenisation.model.ResponseData;
import com.service.tokenisation.model.RetokenisationHandler;
import com.service.tokenisation.model.RetokeniseRequest;
import com.service.tokenisation.model.TokenDateTime;
import com.service.tokenisation.model.TokenInteger;
import com.service.tokenisation.model.TokenString;

/**
 * RetokenisationDAO class This class is responsible for Database operations for Retokenisation
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class RetokenisationDAO {
	private static final Logger LOG = Logger.getLogger(RetokenisationDAO.class);
	CommonUtility commonUtility = new CommonUtility();

	/**
	 * This method will fetch Authorized Business Entity list from APIAuth table for retokenisation
	 * 
	 * @param sourceDomainName
	 *            : Source domain coming in request
	 * @param targetDomainName
	 *            : Target domain coming in request
	 * @return List: Authorized Business entity name list
	 * @exception TokenException
	 */
	public List<String> getAuthorisedBusinessEntities(String sourceDomainName, String targetDomainName)
			throws TokenException {
		LOG.info("Invoking getAuthorisedBusinessEntities");
		Session commonSession = HibernateUtil.getSession();
		try {
			List<String> beList1;
			List<String> beList2;
			Query queryObj1 = commonSession.getNamedQuery("getAuthorizedBusinessEntityList");
			queryObj1.setParameter("DomainName", sourceDomainName.toUpperCase());
			queryObj1.setParameter("APIName", Constants.API_RETOKENISATION_NAME.toUpperCase());
			beList1 = queryObj1.list();
			Query queryObj2 = commonSession.getNamedQuery("getAuthorizedBusinessEntityList");
			queryObj2.setParameter("DomainName", targetDomainName.toUpperCase());
			queryObj2.setParameter("APIName", Constants.API_RETOKENISATION_NAME.toUpperCase());
			beList2 = queryObj2.list();
			if (!beList1.isEmpty() && !beList2.isEmpty()) {
				List<String> beList3 = new ArrayList<String>(beList2);
				beList3.retainAll(beList1);
				return beList3;
			} else {
				throw new TokenException(Constants.API_NOAUTH);
			}
		} catch(JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

	/**
	 * This method will check the Authorization of ReTokenisation Request using source and target domain
	 * 
	 * @param retokeniseRequest
	 *            : Retoken Request object
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorised(RetokeniseRequest retokeniseRequest) throws TokenException {
		LOG.debug("Invoking isAuthorised");
		Session commonSession = HibernateUtil.getSession();
		long resultForSourceDomain = 0;
		long resultForTargetDomain = 0;
		try {
			Query query1 = commonSession.getNamedQuery("authorisation.checkAuthorisation");
			query1.setParameter("DOMAINNAME", retokeniseRequest.getSourceDomain().toUpperCase());
			query1.setParameter("APINAME", Constants.API_RETOKENISATION_NAME.toUpperCase());
			resultForSourceDomain = (long) query1.uniqueResult();
			Query query2 = commonSession.getNamedQuery("authorisation.checkAuthorisation");
			query2.setParameter("DOMAINNAME", retokeniseRequest.getTargetDomain().toUpperCase());
			query2.setParameter("APINAME", Constants.API_RETOKENISATION_NAME.toUpperCase());
			resultForTargetDomain = (long) query2.uniqueResult();
		} catch(JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		if (resultForTargetDomain > 0 && resultForSourceDomain > 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method will fetch csd from the vault and update the map
	 * 
	 * @param tableName
	 *            : Token Vault table name from which token needs to be fetched
	 * @param tokenList
	 *            : Token list according to data type
	 * @param retokeniseRequest
	 * @param tokenMap
	 * @param authorisedBusinessEntities
	 * @return HashMap<String, RetokenisationHandler>
	 * @exception TokenException
	 */
	public Map<String, RetokenisationHandler> getRetokeniseData(String tableName, List tokenList,
			RetokeniseRequest retokeniseRequest, Map<String, RetokenisationHandler> tokenMap,
			List<String> authorisedBusinessEntities) throws TokenException {
		LOG.info("Invoking getRetokeniseData");
		Session sessionVault = HibernateUtil.getVaultSession();
		Map<String, RetokenisationHandler> tokenMapRetoken = tokenMap;
		try {
			Query queryList1 = null;
			if (tableName.equalsIgnoreCase(Constants.TABLE_STRING)) {
				queryList1 = sessionVault.createQuery("FROM TokenString tv WHERE tv.token in :tokenValue ");
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_INTEGER)) {
				queryList1 = sessionVault.createQuery("FROM TokenInteger tv WHERE tv.token in :tokenValue ");
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_DATETIME)) {
				queryList1 = sessionVault.createQuery("FROM TokenDateTime tv WHERE tv.token in :tokenValue");
			}
			queryList1.setParameterList("tokenValue", tokenList);
			List csdList = queryList1.list();
			if (!csdList.isEmpty()) {
				tokenMapRetoken = createTokenisationRequest(csdList, tableName, retokeniseRequest, tokenMapRetoken,
						authorisedBusinessEntities);
			}

		} catch(JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (sessionVault != null) {
				sessionVault.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (sessionVault != null) {
				sessionVault.close();
			}
			throw new TokenException(Constants.VAULT_ERROR);
		}
		/** For MCO Vault */
		try {
			Session sessionMCOVault = HibernateUtil.getMCOVaultSession();
			Query queryList2 = null;
			if (tableName.equalsIgnoreCase(Constants.TABLE_STRING)) {
				queryList2 = sessionMCOVault.createQuery("FROM TokenString tv WHERE tv.token in :tokenValue ");
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_INTEGER)) {
				queryList2 = sessionMCOVault.createQuery("FROM TokenInteger tv WHERE tv.token in :tokenValue ");
			} else if (tableName.equalsIgnoreCase(Constants.TABLE_DATETIME)) {
				queryList2 = sessionMCOVault.createQuery("FROM TokenDateTime tv WHERE tv.token in :tokenValue");
			}
			queryList2.setParameterList("tokenValue", tokenList);
			List csdList2 = queryList2.list();
			if (!csdList2.isEmpty()) {
				tokenMapRetoken = createTokenisationRequest(csdList2, tableName, retokeniseRequest, tokenMapRetoken,
						authorisedBusinessEntities);
			}

		} catch(JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (sessionVault != null) {
				sessionVault.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (sessionVault != null) {
				sessionVault.close();
			}
			throw new TokenException(Constants.VAULT_ERROR);
		}
		return tokenMap;
	}

	/**
	 * This method will replace DataType value from map with CSD
	 * 
	 * @param csdList
	 *            : CSD's fetched from Vault
	 * @param tableName
	 *            : token vault name
	 * @param retokeniseRequest
	 * @param tokenMap
	 * @param authorisedBusinessEntities
	 * @return HashMap<String, RetokenisationHandler>
	 * @exception TokenException
	 */
	private Map<String, RetokenisationHandler> createTokenisationRequest(List csdList, String tableName,
			RetokeniseRequest retokeniseRequest, Map<String, RetokenisationHandler> tokenMapRetoken,
			List<String> authorisedBusinessEntities) throws TokenException {
		LOG.debug("Invoking createTokenisationRequest");
		List<String> authorisedBusinessEntitiesList = authorisedBusinessEntities;
		List<RequestData> listTokenRequestData = new ArrayList<>();
		Map<String, RetokenisationHandler> tokenMapRetoken1 = tokenMapRetoken;

		for (int i = 0; i < authorisedBusinessEntitiesList.size(); i++) {

			/** Create new Request Object to be send to Tokenisation Service */
			RequestData tokRequestData = new RequestData();
			tokRequestData.setOwningBusinessEntity(authorisedBusinessEntitiesList.get(i));
			tokRequestData.setDomain(retokeniseRequest.getTargetDomain());
			tokRequestData.setSourceSystemName(retokeniseRequest.getSourceSystemName());
			/** Create new CSD List for Request */
			List<CsdRequest> csdDataList = new ArrayList<CsdRequest>();
			for (Entry<String, RetokenisationHandler> entry : tokenMapRetoken1.entrySet()) {

				for (int k = 0; k < csdList.size(); k++) {
					if (tableName.equals(Constants.TABLE_INTEGER)) {
						TokenInteger tokenInteger = (TokenInteger) csdList.get(k);
						if (entry.getValue().getOldToken().equals(String.valueOf(tokenInteger.getToken()))) {
							if (tokenInteger.getBusinessEntityName().equalsIgnoreCase(
									authorisedBusinessEntitiesList.get(i))) {
								CsdRequest csdFields = new CsdRequest();
								csdFields.setId(entry.getKey());
								csdFields.setFieldValue(tokenInteger.getcSDValue());

								if (tokenInteger.isRepeatableFlag()) {
									csdFields.setIsRepeatable(Constants.REPETABLE_TRUE);
								} else {
									csdFields.setIsRepeatable(Constants.REPETABLE_FALSE);
								}
								if (tokenInteger.getTokenMetadata() != null
										&& !tokenInteger.getTokenMetadata().isEmpty()) {
									csdFields.setMetadata(tokenInteger.getTokenMetadata());
								}
								csdFields.setSourceFieldName(tokenInteger.getSourceFieldName());
								csdFields.setTargetFieldName(entry.getValue().getTargetFieldName());
								csdFields.setTokenType(Constants.DATA_TYPE_INTEGER);
								csdDataList.add(csdFields);
								break;
							} else {
								entry.getValue().setNewToken(Constants.NOAUTH);
								break;
							}
						}
					} else if (tableName.equals(Constants.TABLE_STRING)) {
						TokenString tokenString = (TokenString) csdList.get(k);
						if (entry.getValue().getOldToken().equals(tokenString.getToken())) {
							if (tokenString.getBusinessEntityName().equalsIgnoreCase(
									authorisedBusinessEntitiesList.get(i))) {
								CsdRequest csdFields = new CsdRequest();
								csdFields.setId(entry.getKey());
								csdFields.setFieldValue(tokenString.getcSDValue());
								if (tokenString.isRepeatableFlag()) {
									csdFields.setIsRepeatable(Constants.REPETABLE_TRUE);
								} else {
									csdFields.setIsRepeatable(Constants.REPETABLE_FALSE);
								}
								if (tokenString.getTokenMetadata() != null && !tokenString.getTokenMetadata().isEmpty()) {
									csdFields.setMetadata(tokenString.getTokenMetadata());
								}
								csdFields.setSourceFieldName(tokenString.getSourceFieldName());
								csdFields.setTargetFieldName(entry.getValue().getTargetFieldName());
								csdFields.setTokenType(Constants.DATA_TYPE_STRING);
								csdDataList.add(csdFields);
								break;
							} else {
								entry.getValue().setNewToken(Constants.NOAUTH);
								break;
							}
						}
					} else if (tableName.equals(Constants.TABLE_DATETIME)) {
						TokenDateTime tokenDateTime = (TokenDateTime) csdList.get(k);
						if (entry.getValue().getDataType().equals(Constants.DATA_TYPE_DATETIME)) {
							java.sql.Timestamp timeStampDate;
							try {
								SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
								Date date = formatter1.parse(entry.getValue().getOldToken());
								timeStampDate = new Timestamp(date.getTime());
							} catch (ParseException e) {
								throw new TokenException(Constants.SYSTEM_ERROR);
							}
							long tokenInVault = tokenDateTime.getToken().getTime();
							long tokenReceived = timeStampDate.getTime();

							if (tokenReceived == tokenInVault) {
								if (tokenDateTime.getBusinessEntityName().equalsIgnoreCase(
										authorisedBusinessEntitiesList.get(i))) {
									CsdRequest csdFields = new CsdRequest();

									csdFields.setId(entry.getKey());
									csdFields.setFieldValue(tokenDateTime.getcSDValue());
									if (tokenDateTime.isRepeatableFlag()) {
										csdFields.setIsRepeatable(Constants.REPETABLE_TRUE);
									} else {
										csdFields.setIsRepeatable(Constants.REPETABLE_FALSE);
									}
									if (tokenDateTime.getTokenMetadata() != null
											&& !tokenDateTime.getTokenMetadata().isEmpty()) {
										csdFields.setMetadata(tokenDateTime.getTokenMetadata());
									}
									csdFields.setSourceFieldName(tokenDateTime.getSourceFieldName());
									csdFields.setTargetFieldName(entry.getValue().getTargetFieldName());
									csdFields.setTokenType(Constants.DATA_TYPE_DATETIME);
									csdDataList.add(csdFields);
									break;
								} else {
									entry.getValue().setNewToken(Constants.NOAUTH);
									break;
								}

							}

						}
					}
				}
			}
			if (!csdDataList.isEmpty()) {
				tokRequestData.setCsds(csdDataList);
				listTokenRequestData.add(tokRequestData);
			}
		}
		tokenMapRetoken1 = tokeniseData(listTokenRequestData, tokenMapRetoken1);
		return tokenMapRetoken1;
	}

	/**
	 * This method will call tokenisation service method to tokenise data
	 * 
	 * @param listTokenRequestData
	 *            : List of Token Request Data
	 * @param tokenMap
	 * @return HashMap<String, RetokenisationHandler>
	 * @exception TokenException
	 */
	Map<String, RetokenisationHandler> tokeniseData(List<RequestData> listTokenRequestData,
			Map<String, RetokenisationHandler> tokenMap) throws TokenException {
		LOG.debug("Invoking tokeniseData");
		TokenisationRetokenisation tokenisationRetokenisation = new TokenisationRetokenisation();
		try {
			List<ResponseData> responseDataList = tokenisationRetokenisation.getTokeniseData(listTokenRequestData);
			if (!responseDataList.isEmpty()) {
				for (ResponseData responseDataObj : responseDataList) {
					List<CsdResponse> csds = responseDataObj.getCsds();
					for (int i = 0; i < csds.size(); i++) {
						for (Entry<String, RetokenisationHandler> entry : tokenMap.entrySet()) {
							if (entry.getKey().equals(csds.get(i).getId())) {
								entry.getValue().setNewToken(csds.get(i).getToken());
							}
						}
					}
				}
			}
			return tokenMap;
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (e.toString().contains(Constants.DB_UNAVAILABLE)) {
				throw new TokenException(Constants.DB_UNAVAILABLE);
			} else if (e.toString().contains(Constants.VAULT_ERROR)){
				throw new TokenException(Constants.VAULT_ERROR);
			}else{
			throw new TokenException(Constants.SYSTEM_ERROR);
			}
		}
	}
	/**
	 * This method will check the Authorization of ReTokenisation Request using source and target domain
	 * 
	 * @param retokeniseRequest
	 *            : Retoken Request object
	 *	@param requestData
	 *            :callingSystemName
	 * @return boolean: True:- If Authorized, False:- If Unauthorized
	 * @exception TokenException
	 */
	public boolean isAuthorisedCallingSystem(RetokeniseRequest retokeniseRequest, String callingSystemName) throws TokenException {
		LOG.debug("Invoking isAuthorisedCallingSystem");
		Session commonSession = HibernateUtil.getSession();
		long resultForSourceDomain = 0;
		long resultForTargetDomain = 0;
		try {
			Query query1 = commonSession.getNamedQuery("authorisation.checkAuthorisationUsingCert");
			query1.setParameter("DOMAINNAME", retokeniseRequest.getSourceDomain().toUpperCase());
			query1.setParameter("APINAME", Constants.API_RETOKENISATION_NAME.toUpperCase());
			query1.setParameter("CALLINGSYSTENMANE", callingSystemName.toUpperCase());
			resultForSourceDomain = (long) query1.uniqueResult();
			Query query2 = commonSession.getNamedQuery("authorisation.checkAuthorisationUsingCert");
			query2.setParameter("DOMAINNAME", retokeniseRequest.getTargetDomain().toUpperCase());
			query2.setParameter("APINAME", Constants.API_RETOKENISATION_NAME.toUpperCase());
			query2.setParameter("CALLINGSYSTENMANE", callingSystemName.toUpperCase());
			resultForTargetDomain = (long) query2.uniqueResult();
		} catch(JDBCConnectionException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}catch (HibernateException e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			if (commonSession != null) {
				commonSession.close();
			}
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		if (resultForTargetDomain > 0 && resultForSourceDomain > 0) {
			return true;
		}
		return false;
	}

}
