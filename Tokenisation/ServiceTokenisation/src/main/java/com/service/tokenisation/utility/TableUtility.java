package com.service.tokenisation.utility;

import org.apache.log4j.Logger;


/**
 * TableUtility class This is common Utility class for DB Tables
 * 
 * @author: Satyajit Singh
 * @version: 1
 */

public class TableUtility {

	private static final Logger LOG = Logger.getLogger(TableUtility.class);

	private TableUtility() {
		super();
	}

	public static String getTokenBankTableNameForDataType(String dataType) {
		LOG.debug(" Invoking getTokenBankTableNameForDataType");
		String tokenBankTableName = null;
		if (dataType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {
			tokenBankTableName = Constants.TOKEN_BANK_STRING;
		} else if (dataType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
			tokenBankTableName = Constants.TOKEN_BANK_INTEGER;
		} else if (dataType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
			tokenBankTableName = Constants.TOKEN_BANK_DATE_TIME;
		}
		return tokenBankTableName;
	}

	public static String getTokenVaultNameForDataType(String dataType) {
		LOG.debug("Invoking getTokenVaultNameForDataType");
		String tokenVaultTableName = null;
		if (dataType.equalsIgnoreCase(Constants.DATA_TYPE_STRING)) {
			tokenVaultTableName = Constants.TABLE_STRING;
		} else if (dataType.equalsIgnoreCase(Constants.DATA_TYPE_INTEGER)) {
			tokenVaultTableName = Constants.TABLE_INTEGER;
		} else if (dataType.equalsIgnoreCase(Constants.DATA_TYPE_DATETIME)) {
			tokenVaultTableName = Constants.TABLE_DATETIME;
		}
		return tokenVaultTableName;
	}

}
