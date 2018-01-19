package com.service.tokenisation.utility;

/**
 * Constants class Project Constants
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class Constants {

	private Constants() {
		super();
	}

	public static final String TOKEN_BANK_INTEGER = "SeedInteger";
	public static final String TOKEN_BANK_STRING = "SeedString";
	public static final String TOKEN_BANK_DATE_TIME = "SeedDateTime";

	public static final String DATA_TYPE_STRING = "S";
	public static final String DATA_TYPE_INTEGER = "I";
	public static final String DATA_TYPE_DATETIME = "D";

	public static final String TABLE_STRING = "TokenString";
	public static final String TABLE_INTEGER = "TokenInteger";
	public static final String TABLE_DATETIME = "TokenDateTime";

	public static final String DB_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";
	public static final String REQUEST_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final boolean TRUE = true;
	public static final boolean FALSE = false;

	public static final String YES = "Yes";
	public static final String NO = "No";

	public static final String MCO = "MCO";
	public static final String GVA = "GVA";
	public static final String CH = "CH";

	public static final String REPETABLE_TRUE = "Y";
	public static final String REPETABLE_FALSE = "N";

	public static final String NOT_TOKEN = "Not Token";

	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;

	public static final String API_TOKENISATION_NAME = "TOKENISE";
	public static final String API_DETOKENISATION_NAME = "DETOKENISE";
	public static final String API_RETOKENISATION_NAME = "RETOKENISE";

	public static final String AUTHORISED = "AUTHORISED";
	public static final String NOT_AUTHORISED = "NOT AUTHORISED’";

	public static final String PROPERTIES_FOLDER_NAME = "TokenisationProperties";
	public static final String BARCLAYS_PROPERTIES_FILE = "serviceCommon.properties";
	public static final String ENCRYPTION_SERVICE_URL = "encryptionServiceURL";

	/** For Error Response */
	public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
	public static final String DB_UNAVAILABLE = "DB_UNAVAILABLE";
	public static final String VAULT_ERROR = "VAULT_ERROR";
	public static final String VALIDATION_FAILED = "VALIDATION_FAILED";
	public static final String API_NOAUTH = "API_NOAUTH";
	public static final String BU_NOAUTH = "BU_NOAUTH";
	public static final String INVALID_REQUEST = "INVALID_REQUEST";
	public static final String FAILED = "FAILED";
	public static final String SUCCESS = "SUCCESS";
	public static final String PARTIAL_SUCCESS = "PARTIAL_SUCCESS";
	public static final String MULTI_ERRORS = "MULTI_ERRORS";

	public static final String EXCEPTION = "EXCEPTION :";

	public static final String NOT_FOUND = "NOT_FOUND";

	public static final String NOT_VALID_JASON_REASON = " Invalid JSON";
	public static final String NOT_AUTHENTICATION_REASON = "Authentication failure";
	public static final String NOT_AUTH_REASON = "Authorisation failure";
	public static final String NOT_FOUND_REASON = "Few tokens are not found in the database";
	public static final String MULTI_ERR_REASON = "Multiple errors found";
	public static final String MULTI_ERR_REASON_DETOKEN = "Some tokens are not found, and you do not have sufficient access to detokenise others";

	public static final String EXIT_TOKENISATION = "Exiting Tokenisation Service";
	public static final String EXIT_DETOKENISATION = "Exiting De-Tokenisation Service";
	public static final String EXIT_RETOKENISATION = "Exiting Re-Tokenisation Service";

	public static final String START_TOKENISATION = "Starting Tokenisation Service";
	public static final String START_DETOKENISATION = "Starting De-Tokenisation Service";
	public static final String START_RETOKENISATION = "Starting Re-Tokenisation Service";

	public static final String MISSING_MANDATORY_FIELDS = "Missing Mandatory Field(s) - ";

	public static final String NOAUTH = "NOAUTH";

	public static final String DB_TE = "te";
	public static final String DB_MCO = "vaultmco";
	public static final String DB_VAULT = "vault";
	public static final String DB_PROPERTIES_FILE = "serviceConnection.properties";

	public static final String IS_CERT_AUTH_REQ = "isCertAuthenticationReq";

	/** For Token Seeder Service */
	public static final int SEED_NOTUSED = 0;
	public static final String STRING_TOKEN_PATTERN = "^^";
	private static final char[] STRING_TOKEN = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9' };

	public static char[] getStringToken() {
		return STRING_TOKEN;
	}

	public static final String STRING_APPENDER = "^^";

	/** For DeTokenisation */
	public static final String STR_TKN_REG_EXP = "\\^\\^[@*$^+][A-Za-z0-9]";
	public static final String INT_TKN_REG_EXP = "(\\d{19})";
	public static final String DATETIME_TKN_REG_EXP = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([3-9][0-9][0-9][0-9]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.(\\d{3})";

}
