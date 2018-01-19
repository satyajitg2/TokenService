package com.service.tokenseeder.constants;

/**
 * 
 * <p>
 * The <b> Constants <b> being used in the Token Seeder library.
 * <p>
 *
 */

public class Constants {
	private Constants() {
	}

	public static final String SEEDER_DB_PROPERTIES = "SEEDER_DB_PROPERTIES";
	public static final String REQUEST_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String STRING_TOKEN_PATTERN = "^^";
	private static final char[] STRING_TOKEN = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9' };
	public static final int SEED_NOTUSED = 0;
	public static final String FILE_NAME = "config_bench.properties";
	public static final String TOKEN_BENCHMARK = "MinimumNumberTokenInBank";

	public static final String JDBC_SQL_SERVER = "jdbc:sqlserver://";

	public static char[] getStringToken() {
		return STRING_TOKEN;
	}

} // end of class
