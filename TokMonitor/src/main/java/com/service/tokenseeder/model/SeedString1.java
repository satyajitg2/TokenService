package com.service.tokenseeder.model;

/**
 * 
 * <p>
 * POJO for SeedString1 table
 * <p>
 *
 */
public class SeedString1 {

	private int seed;
	private String seedValue;
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

	public String getSeedValue() {
		return seedValue;
	}

	public void setSeedValue(String seedValue) {
		this.seedValue = seedValue;
	}

	public boolean isSeedUsedFlag() {
		return seedUsedFlag;
	}

	public void setSeedUsedFlag(boolean seedUsedFlag) {
		this.seedUsedFlag = seedUsedFlag;
	}

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
		SeedString1 other = (SeedString1) obj;
		if (configurationIdentifier != other.configurationIdentifier) {
			return false;
		}
		if (seed != other.seed) {
			return false;
		}
		if (seedUsedFlag != other.seedUsedFlag) {
			return false;
		}
		if (seedValue != other.seedValue) {
			return false;
		}
		return true;
	}

}