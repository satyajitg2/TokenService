package com.service.tokenisation.hibernate;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.utility.Constants;
import org.apache.commons.codec.binary.Base64;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static SessionFactory vaultSessionFactory;
	private static SessionFactory mcoVaultSessionFactory;
	private static final ThreadLocal THREADSESSION = new ThreadLocal();
	private static final ThreadLocal THREADVAULTSESSION = new ThreadLocal();
	private static final ThreadLocal THREADMCOVAULTSESSION = new ThreadLocal();
	private static final Logger LOG = Logger.getLogger(HibernateUtil.class);
	private static String location;
	private static String encryptionURL;
	private static String authenticationReq;

	public static String getLocation() {
		return location;
	}

	public static String getEncryptionURL() {
		return encryptionURL;
	}

	public static String getAuthenticationReq() {
		return authenticationReq;
	}

	public HibernateUtil() {
		super();
	}

	static {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		System.setProperty("log4j.date", df.format(cal.getTime()));
		System.setProperty("log4j.date.folder", df.format(cal.getTime()));

	}

	/** Create the initial SessionFactory from the default configuration files */
	public static void configure() {
		try {
			configureTokenisation();
			configureVault();
			configureMcoVault();
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void configureVault() {
		try {
			vaultSessionFactory = buildSessionFactory(Constants.DB_VAULT);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void configureTokenisation() {
		try {
			sessionFactory = buildSessionFactory(Constants.DB_TE);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static void configureMcoVault() {
		try {
			mcoVaultSessionFactory = buildSessionFactory(Constants.DB_MCO);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	/*private static SessionFactory buildSessionFactory(String connectionnDB) throws TokenException {
		try {

			Context env = (Context) new InitialContext().lookup("java:/comp/env");
			location = (String) env.lookup("tokenisationProps");
			Properties dbConnectionProperties = new Properties();
			dbConnectionProperties.load(new FileInputStream(location + Constants.DB_PROPERTIES_FILE));
			return loadProperties(connectionnDB, dbConnectionProperties);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			return buildSessionFactoryFromLocal(connectionnDB);
		}
	}*/
	private static SessionFactory buildSessionFactory(String connectionnDB)
            throws TokenException {
        try {
            String tomcatBase = System.getProperty("catalina.home");
            if (tomcatBase == null || tomcatBase.equalsIgnoreCase("")){
            	 throw new Exception("catalina.base not Set");
            }
            location = tomcatBase + "/tokenisationdata/conf/";
            Properties dbConnectionProperties = new Properties();
            dbConnectionProperties.load(new FileInputStream(location + Constants.DB_PROPERTIES_FILE));
            return loadProperties(connectionnDB, dbConnectionProperties);
        } catch (Exception ex) {
            LOG.error(Constants.EXCEPTION, ex);
            return buildSessionFactoryFromLocal(connectionnDB);
        }

    }

	

	private static SessionFactory buildSessionFactoryFromLocal(String connectionnDB) throws TokenException {
		try {
			Properties dbConnectionProperties = new Properties();
			dbConnectionProperties.load(HibernateUtil.class.getClassLoader().getResourceAsStream(
					Constants.DB_PROPERTIES_FILE));
			return loadProperties(connectionnDB, dbConnectionProperties);

		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}
	}
	
	/**
	 * This method will load properties from external location
	 * 
	 * @param connectionnDB
	 *            : DB field name for respective DB
	 * @param dbConnectionProperties
	 *            : DB Properties file
	 * @return SessionFactory
	 * @throws TokenException
	 *
	 */
	private static SessionFactory loadProperties(String connectionnDB, Properties dbConnectionProperties) {
		byte[] decodedPwdBytes = Base64.decodeBase64(dbConnectionProperties.getProperty(
				"hibernate.connection.password." + connectionnDB).getBytes());

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.connection.url",
				dbConnectionProperties.getProperty("hibernate.connection.url." + connectionnDB));
		connectionProperties.setProperty("hibernate.connection.username",
				dbConnectionProperties.getProperty("hibernate.connection.username." + connectionnDB));
		connectionProperties.setProperty("hibernate.connection.password", new String(decodedPwdBytes));
		connectionProperties.setProperty("hibernate.connection.driver_class",
				dbConnectionProperties.getProperty("hibernate.connection.driver_class." + connectionnDB));

		if (connectionnDB.equalsIgnoreCase(Constants.DB_TE)) {
			return new Configuration().mergeProperties(connectionProperties).configure(
					"/configuration/tokenisation.cfg.xml").buildSessionFactory();
		}
		if (connectionnDB.equalsIgnoreCase(Constants.DB_MCO)) {
			return new Configuration().mergeProperties(connectionProperties).configure(
					"/configuration/mcoVault.cfg.xml").buildSessionFactory();
		}
		if (connectionnDB.equalsIgnoreCase(Constants.DB_VAULT)) {
			return new Configuration().mergeProperties(connectionProperties).configure("/configuration/vault.cfg.xml").buildSessionFactory();
		}
		return null;
	}

	public static SessionFactory getSessionFactory() throws TokenException {
		try {
			if (sessionFactory == null) {
				configureTokenisation();
			}
			return sessionFactory;
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}
	}

	public static SessionFactory getVaultSessionFactory() throws TokenException {
		try {
			if (vaultSessionFactory == null) {
				configureVault();
			}
			return vaultSessionFactory;
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}
	}

	private static SessionFactory getMCOVaultSessionFactory() throws TokenException {
		try {
			if (mcoVaultSessionFactory == null) {
				configureMcoVault();
			}
			return mcoVaultSessionFactory;
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.DB_UNAVAILABLE);
		}
	}

	public static Session getSession() throws TokenException {
		Session s = (Session) THREADSESSION.get();
		if (s == null || !s.isOpen()) {
			s = getSessionFactory().openSession();
			THREADSESSION.set(s);
		}
		return s;
	}

	public static Session getVaultSession() throws TokenException {
		Session s = (Session) THREADVAULTSESSION.get();
		if (s == null || !s.isOpen()) {
			s = getVaultSessionFactory().openSession();
			THREADVAULTSESSION.set(s);
		}
		return s;
	}

	public static Session getMCOVaultSession() throws TokenException {
		Session s = (Session) THREADMCOVAULTSESSION.get();
		if (s == null || !s.isOpen()) {
			s = getMCOVaultSessionFactory().openSession();
			THREADMCOVAULTSESSION.set(s);
		}
		return s;
	}

	public static void initLogPro() throws TokenException {
		try {
			Properties loggingFile = new Properties();
			loggingFile.load(new FileInputStream(location + "log4j.properties"));
			PropertyConfigurator.configure(loggingFile);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			initLogProLocal();
		}
	}

	public static void initLogProLocal() throws TokenException {
		try {
			Properties loggingFile = new Properties();
			loggingFile.load(HibernateStartUpServlet.class.getClassLoader().getResourceAsStream("log4j.properties"));
			PropertyConfigurator.configure(loggingFile);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}

	public static void loadAllProperties() throws TokenException {
		try {
			Properties propertyFile = new Properties();
			propertyFile.load(new FileInputStream(location + "serviceCommon.properties"));
			authenticationReq = propertyFile.getProperty(Constants.IS_CERT_AUTH_REQ);
			encryptionURL = propertyFile.getProperty(Constants.ENCRYPTION_SERVICE_URL);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			loadAllPropertiesLocal();
		}

	}

	public static void loadAllPropertiesLocal() throws TokenException {
		try {
			Properties propertyFile = new Properties();
			propertyFile.load(HibernateStartUpServlet.class.getClassLoader().getResourceAsStream(
					"serviceCommon.properties"));
			authenticationReq = propertyFile.getProperty(Constants.IS_CERT_AUTH_REQ);
			encryptionURL = propertyFile.getProperty(Constants.ENCRYPTION_SERVICE_URL);
		} catch (Exception ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}

	}
}