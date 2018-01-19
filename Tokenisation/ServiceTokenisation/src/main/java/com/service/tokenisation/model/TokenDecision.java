package com.service.tokenisation.model;

import java.sql.Timestamp;

/**
 * POJO class for TokenDecision
 * 
 * @author: Satyajit Singh
 * @version: 1
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
	private Timestamp updatedDateTime;
	private long updateCount;

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

	public void setTokenToStartGenerateDateTime(
			Timestamp tokenToStartGenerateDateTime) {
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

	public Timestamp getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Timestamp updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(long updateCount) {
		this.updateCount = updateCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + configurationIdentifier;
		result = prime * result + id;
		result = prime * result + maxTokenToGenerateDateTime;
		result = prime * result + maxTokenToGenerateInteger;
		result = prime * result + maxTokenToGenerateString;
		result = prime * result + minTokenInDateTime;
		result = prime * result + minTokenInInteger;
		result = prime * result + minTokenInString;
		result = prime
				* result
				+ ((nextRotationTime == null) ? 0 : nextRotationTime.hashCode());
		result = prime * result + tableSetToUseByTokenEngine;
		result = prime * result + tableSetToUseByTokenSeeder;
		result = prime * result + tokenRotationFreq;
		result = prime
				* result
				+ ((tokenToStartGenerateDateTime == null) ? 0
						: tokenToStartGenerateDateTime.hashCode());
		result = prime
				* result
				+ (int) (tokenToStartGenerateInteger ^ (tokenToStartGenerateInteger >>> 32));
		result = prime
				* result
				+ ((tokenToStartGenerateString == null) ? 0
						: tokenToStartGenerateString.hashCode());
		result = prime * result + (int) (updateCount ^ (updateCount >>> 32));
		result = prime * result
				+ ((updatedDateTime == null) ? 0 : updatedDateTime.hashCode());
		return result;
	}

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
		if (maxTokenToGenerateDateTime != other.maxTokenToGenerateDateTime) {
			return false;
		}
		if (maxTokenToGenerateInteger != other.maxTokenToGenerateInteger) {
			return false;
		}
		if (maxTokenToGenerateString != other.maxTokenToGenerateString) {
			return false;
		}
		if (minTokenInDateTime != other.minTokenInDateTime) {
			return false;
		}
		if (minTokenInInteger != other.minTokenInInteger) {
			return false;
		}
		if (minTokenInString != other.minTokenInString) {
			return false;
		}
		if (nextRotationTime == null) {
			if (other.nextRotationTime != null) {
				return false;
			}
		} else if (!nextRotationTime.equals(other.nextRotationTime)) {
			return false;
		}
		if (tableSetToUseByTokenEngine != other.tableSetToUseByTokenEngine) {
			return false;
		}
		if (tableSetToUseByTokenSeeder != other.tableSetToUseByTokenSeeder) {
			return false;
		}
		if (tokenRotationFreq != other.tokenRotationFreq) {
			return false;
		}
		if (tokenToStartGenerateDateTime == null) {
			if (other.tokenToStartGenerateDateTime != null) {
				return false;
			}
		} else if (!tokenToStartGenerateDateTime
				.equals(other.tokenToStartGenerateDateTime)) {
			return false;
		}
		if (tokenToStartGenerateInteger != other.tokenToStartGenerateInteger) {
			return false;
		}
		if (tokenToStartGenerateString == null) {
			if (other.tokenToStartGenerateString != null) {
				return false;
			}
		} else if (!tokenToStartGenerateString
				.equals(other.tokenToStartGenerateString)) {
			return false;
		}
		if (updateCount != other.updateCount) {
			return false;
		}
		if (updatedDateTime == null) {
			if (other.updatedDateTime != null) {
				return false;
			}
		} else if (!updatedDateTime.equals(other.updatedDateTime)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TokenDecision [id=" + id + ", tableSetToUseByTokenSeeder="
				+ tableSetToUseByTokenSeeder + ", tableSetToUseByTokenEngine="
				+ tableSetToUseByTokenEngine + ", tokenToStartGenerateInteger="
				+ tokenToStartGenerateInteger + ", tokenToStartGenerateString="
				+ tokenToStartGenerateString
				+ ", tokenToStartGenerateDateTime="
				+ tokenToStartGenerateDateTime + ", maxTokenToGenerateInteger="
				+ maxTokenToGenerateInteger + ", maxTokenToGenerateString="
				+ maxTokenToGenerateString + ", maxTokenToGenerateDateTime="
				+ maxTokenToGenerateDateTime + ", minTokenInInteger="
				+ minTokenInInteger + ", minTokenInString=" + minTokenInString
				+ ", minTokenInDateTime=" + minTokenInDateTime
				+ ", tokenRotationFreq=" + tokenRotationFreq
				+ ", nextRotationTime=" + nextRotationTime
				+ ", configurationIdentifier=" + configurationIdentifier
				+ ", updatedDateTime=" + updatedDateTime + ", updateCount="
				+ updateCount + "]";
	}

}