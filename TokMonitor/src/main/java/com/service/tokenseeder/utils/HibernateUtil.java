package com.service.tokenseeder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.service.tokenseeder.constants.Constants;

/**
 * Hibernate utils to generate the session on Database.
 * 
 * @version 1.0
 *
 */
public class HibernateUtil {
	private HibernateUtil() {
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {

			String seederPath = System.getProperty(Constants.SEEDER_DB_PROPERTIES);

			if (seederPath == null || seederPath.length() == 0) {
				throw new IllegalArgumentException();
			}

			Properties dbSeederProperties = new Properties();
			FileInputStream inStream = new FileInputStream(new File(seederPath));

			dbSeederProperties.load(inStream);

			String connectionUrl = buildDBParams(dbSeederProperties);

			LOGGER.info("connectionUrl = " + connectionUrl);

			Configuration configuration = new Configuration();
			byte[] decodedPwdBytes = Base64.decodeBase64(dbSeederProperties.getProperty("hibernate.connection.pword").getBytes());
			configuration.setProperty("hibernate.connection.password", new String(decodedPwdBytes));
			configuration.setProperty("hibernate.connection.url", connectionUrl);
			configuration.configure("hibernate.cfg.xml").addProperties(dbSeederProperties);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();

			return configuration.buildSessionFactory(serviceRegistry);

		} catch (Exception ex) {
			LOGGER.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static void stop(){
		SESSION_FACTORY.close();
	}
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

	private static String buildDBParams(Properties dbSeederProperties) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.JDBC_SQL_SERVER);
		sb.append(dbSeederProperties.getProperty("hibernate.connection.ip"));
		sb.append(":");
		sb.append(dbSeederProperties.getProperty("hibernate.connection.port"));
		sb.append(";");
		sb.append("databaseName=");
		sb.append(dbSeederProperties.getProperty("hibernate.connection.databaseName"));
		/*
		 * StringBuilder sb = new StringBuilder();
		 * sb.append(dbSeederProperties.getProperty("hibernate.connection.driverurl")); sb.append("://");
		 * sb.append(dbSeederProperties.getProperty("hibernate.connection.ip")); sb.append("/");
		 * sb.append(dbSeederProperties.getProperty("hibernate.connection.databaseName")); sb.append(";");
		 * sb.append("instance="); sb.append(dbSeederProperties.getProperty("hibernate.connection.instance"));
		 * sb.append(";domain="); sb.append(dbSeederProperties.getProperty("hibernate.connection.domain"));
		 * sb.append(";useNTLMv2="); sb.append(dbSeederProperties.getProperty("hibernate.connection.useNTLMv2"));
		 * sb.append(";");
		 */
		// System.out.println(sb.toString());
		return sb.toString();
	}

	private static SessionFactory buildSessionFactory(String configFile) {
		try {
			return new Configuration().configure(configFile).buildSessionFactory();
		} catch (Exception ex) {
			LOGGER.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession(String generalConfigTest) {
		SessionFactory sf = buildSessionFactory(generalConfigTest);
		return sf.openSession();
	}

	public static Session openSession() {
		SessionFactory sf = buildSessionFactory();
		return sf.openSession();
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
