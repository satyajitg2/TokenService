package com.service.tokenseeder.model;

import java.sql.Timestamp;

/**
 * <p>
 * POJO class for TokenDecision
 * <p>
 * 
 * @version: 1.0
 */

public class TokenDecision {
	private int id;
	private int tableSetToUseByTokenSeeder;
	private int tableSetToUseByTokenEngine;
	private long tokenToStartGenerateInteger;
	private String tokenToStartGenerateString;
	private Timestamp tokenToStartGenerateDateTime;
	private int maxTokenToGenerateInteger;
	private int maxTokenToGenerateString;
	private int maxTokenToGenerateDateTime;
	private int minTokenInInteger;
	private int minTokenInString;
	private int minTokenInDateTime;
	private int tokenRotationFreq;
	private Timestamp nextRotationTime;
	private int configurationIdentifier;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableSetToUseByTokenSeeder() {
		return tableSetToUseByTokenSeeder;
	}

	public void setTableSetToUseByTokenSeeder(int tableSetToUseByTokenSeeder) {
		this.tableSetToUseByTokenSeeder = tableSetToUseByTokenSeeder;
	}

	public int getTableSetToUseByTokenEngine() {
		return tableSetToUseByTokenEngine;
	}

	public void setTableSetToUseByTokenEngine(int tableSetToUseByTokenEngine) {
		this.tableSetToUseByTokenEngine = tableSetToUseByTokenEngine;
	}

	public int getMaxTokenToGenerateInteger() {
		return maxTokenToGenerateInteger;
	}

	public void setMaxTokenToGenerateInteger(int maxTokenToGenerateInteger) {
		this.maxTokenToGenerateInteger = maxTokenToGenerateInteger;
	}

	public int getMaxTokenToGenerateString() {
		return maxTokenToGenerateString;
	}

	public void setMaxTokenToGenerateString(int maxTokenToGenerateString) {
		this.maxTokenToGenerateString = maxTokenToGenerateString;
	}

	public int getMaxTokenToGenerateDateTime() {
		return maxTokenToGenerateDateTime;
	}

	public void setMaxTokenToGenerateDateTime(int maxTokenToGenerateDateTime) {
		this.maxTokenToGenerateDateTime = maxTokenToGenerateDateTime;
	}

	public int getConfigurationIdentifier() {
		return configurationIdentifier;
	}

	public void setConfigurationIdentifier(int configurationIdentifier) {
		this.configurationIdentifier = configurationIdentifier;
	}

	public long getTokenToStartGenerateInteger() {
		return tokenToStartGenerateInteger;
	}

	public void setTokenToStartGenerateInteger(long tokenToStartGenerateInteger) {
		this.tokenToStartGenerateInteger = tokenToStartGenerateInteger;
	}

	public String getTokenToStartGenerateString() {
		return tokenToStartGenerateString;
	}

	public void setTokenToStartGenerateString(String tokenToStartGenerateString) {
		this.tokenToStartGenerateString = tokenToStartGenerateString;
	}

	public Timestamp getTokenToStartGenerateDateTime() {
		return tokenToStartGenerateDateTime;
	}

	public void setTokenToStartGenerateDateTime(Timestamp tokenToStartGenerateDateTime) {
		this.tokenToStartGenerateDateTime = tokenToStartGenerateDateTime;
	}

	public int getMinTokenInInteger() {
		return minTokenInInteger;
	}

	public void setMinTokenInInteger(int minTokenInInteger) {
		this.minTokenInInteger = minTokenInInteger;
	}

	public int getMinTokenInString() {
		return minTokenInString;
	}

	public void setMinTokenInString(int minTokenInString) {
		this.minTokenInString = minTokenInString;
	}

	public int getMinTokenInDateTime() {
		return minTokenInDateTime;
	}

	public void setMinTokenInDateTime(int minTokenInDateTime) {
		this.minTokenInDateTime = minTokenInDateTime;
	}

	public int getTokenRotationFreq() {
		return tokenRotationFreq;
	}

	public void setTokenRotationFreq(int tokenRotationFreq) {
		this.tokenRotationFreq = tokenRotationFreq;
	}

	public Timestamp getNextRotationTime() {
		return nextRotationTime;
	}

	public void setNextRotationTime(Timestamp nextRotationTime) {
		this.nextRotationTime = nextRotationTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenDecision [id=" + id + ", tableSetToUseByTokenSeeder=" + tableSetToUseByTokenSeeder
				+ ", tableSetToUseByTokenEngine=" + tableSetToUseByTokenEngine + ", tokenToStartGenerateInteger="
				+ tokenToStartGenerateInteger + ", tokenToStartGenerateString=" + tokenToStartGenerateString
				+ ", tokenToStartGenerateDateTime=" + tokenToStartGenerateDateTime + ", maxTokenToGenerateInteger="
				+ maxTokenToGenerateInteger + ", maxTokenToGenerateString=" + maxTokenToGenerateString
				+ ", maxTokenToGenerateDateTime=" + maxTokenToGenerateDateTime + ", minTokenInInteger="
				+ minTokenInInteger + ", minTokenInString=" + minTokenInString + ", minTokenInDateTime="
				+ minTokenInDateTime + ", tokenRotationFreq=" + tokenRotationFreq + ", nextRotationTime="
				+ nextRotationTime + ", configurationIdentifier=" + configurationIdentifier + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + configurationIdentifier;
		result = prime * result + id;
		result = prime * result + tableSetToUseByTokenEngine;
		result = prime * result + tableSetToUseByTokenSeeder;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TokenDecision other = (TokenDecision) obj;
		if (configurationIdentifier != other.configurationIdentifier) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (tableSetToUseByTokenEngine != other.tableSetToUseByTokenEngine) {
			return false;
		}
		if (tableSetToUseByTokenSeeder != other.tableSetToUseByTokenSeeder) {
			return false;
		}
		return true;
	}

}
