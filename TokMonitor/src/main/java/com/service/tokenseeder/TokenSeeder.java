package com.service.tokenseeder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.service.tokenseeder.constants.Constants;
import com.service.tokenseeder.monitor.JobMonitor;
import com.service.tokenseeder.tokenizer.GenerateToken;

/**
 * 
 * <p>
 * The <b> TokenSeeder </b> class will be invoked for seeding the token in the Token Bank
 * <p>
 * 
 * @version 1.0
 * 
 */

public class TokenSeeder{
	private final static Logger LOGGER = Logger.getLogger(TokenSeeder.class);
	/*static {
        String dbPropertiesFile = "D:\\Users\\santoshkumar-t\\Desktop\\log4j.properties";//path of property file
        PropertyConfigurator.configure(dbPropertiesFile);
}*/

	public static void main(String[] args){

		if (args == null || args.length == 0) {
			LOGGER.error("Usage : java TokenSeeder <DB_PROPERTIES_FILE_PATH> ");
			LOGGER.error("Illegal arguments, please provide Token Seeder database details in a properties file.");
			throw new IllegalArgumentException();
		}

		String dbPropertiesFile = args[0];

		System.setProperty(Constants.SEEDER_DB_PROPERTIES, dbPropertiesFile);

		try {
			LOGGER.info("TokenSeeder Start time: " + logTime(System.currentTimeMillis()));
			LOGGER.info("TokenSeeder Start time: " + logTime(System.currentTimeMillis()));
			JobMonitor job=new JobMonitor();
			job.doJob();			
			LOGGER.info("TokenSeeder End time: " + logTime(System.currentTimeMillis()));
			} catch (Exception ex) {
			LOGGER.error(" The Token Seeder appplication failed to complete successfully. ", ex);
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
}