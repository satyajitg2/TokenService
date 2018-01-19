package com.service.tokenseeder.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * <p>
 * POJO for SeedDateTime2 table
 * <p>
 *
 */

public class SeedDateTime2 {

	/*
	 * ‘DD/MM/YYYY_HH:MIN:SS:sss’ The DateTime token would be made identifiable by keeping YYYY component in Date
	 * greater than 3000 and less than 9999
	 */

	private int seed;
	private Timestamp seedValue;
	private boolean seedUsedFlag;
	private int configurationIdentifier;

	public int getConfigurationIdentifier() {
		return configurationIdentifier;
	}

	public void setConfigurationIdentifier(int configurationIdentifier) {
		this.configurationIdentifier = configurationIdentifier;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public Date getSeedValue() {
		return seedValue;
	}

	public void setSeedValue(Timestamp seedValue) {
		this.seedValue = seedValue;
	}

	public boolean isSeedUsedFlag() {
		return seedUsedFlag;
	}

	public void setSeedUsedFlag(boolean seedUsedFlag) {
		this.seedUsedFlag = seedUsedFlag;
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
		result = prime * result + seed;
		result = prime * result + (seedUsedFlag ? 1231 : 1237);
		result = prime * result + ((seedValue == null) ? 0 : seedValue.hashCode());
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
		SeedDateTime2 other = (SeedDateTime2) obj;
		if (configurationIdentifier != other.configurationIdentifier) {
			return false;
		}
		if (seed != other.seed) {
			return false;
		}
		if (seedUsedFlag != other.seedUsedFlag) {
			return false;
		}
		if (seedValue == null) {
			if (other.seedValue != null) {
				return false;
			}
		} else if (!seedValue.equals(other.seedValue)) {
			return false;
		}
		return true;
	}
}
