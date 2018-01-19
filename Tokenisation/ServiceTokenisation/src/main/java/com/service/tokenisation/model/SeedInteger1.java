package com.service.tokenisation.model;

/**
 * POJO class for SeedInteger1
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class SeedInteger1 {

	private int seed;
	private long seedValue;
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

	public long getSeedValue() {
		return seedValue;
	}

	public void setSeedValue(long seedValue) {
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
		result = prime * result + (int) (seedValue ^ (seedValue >>> 32));
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
		SeedInteger1 other = (SeedInteger1) obj;
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
