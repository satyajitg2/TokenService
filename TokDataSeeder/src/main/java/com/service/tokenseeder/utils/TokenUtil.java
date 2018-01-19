package com.service.tokenseeder.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenUtil.class);

	private TokenUtil() {
	}

	public static String readFile(String fileName, String minimumNumToken) {
		Properties prop = new Properties();
		InputStream input = null;
		String mintoken = null;
		try {
			input = TokenUtil.class.getClassLoader().getResourceAsStream(fileName);
			prop.load(input);
			mintoken = prop.getProperty(minimumNumToken);
		} catch (IOException ex) {
			LOGGER.error("Reading File Exception", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error("File closing exception ", e);
				}
			}
		}
		return mintoken;

	}

}