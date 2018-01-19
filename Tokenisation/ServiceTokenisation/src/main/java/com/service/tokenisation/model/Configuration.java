package com.service.tokenisation.model;

/**
 * POJO class for Configuration
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class Configuration {

	private int configurationIdentifier;
	private String stringVaultIdentifer;
	private long integerTokenStartValue;
	private long integerTokenEndValue;
	private int dateTimeTokenYearStart;
	private int dateTimeTokenYearEnd;
	private String businessEntityName;

	public int getConfigurationIdentifier() {
		return configurationIdentifier;
	}

	public void setConfigurationIdentifier(int configurationIdentifier) {
		this.configurationIdentifier = configurationIdentifier;
	}

	public String getStringVaultIdentifer() {
		return stringVaultIdentifer;
	}

	public void setStringVaultIdentifer(String stringVaultIdentifer) {
		this.stringVaultIdentifer = stringVaultIdentifer;
	}

	public long getIntegerTokenStartValue() {
		return integerTokenStartValue;
	}

	public void setIntegerTokenStartValue(long integerTokenStartValue) {
		this.integerTokenStartValue = integerTokenStartValue;
	}

	public long getIntegerTokenEndValue() {
		return integerTokenEndValue;
	}

	public void setIntegerTokenEndValue(long integerTokenEndValue) {
		this.integerTokenEndValue = integerTokenEndValue;
	}

	public int getDateTimeTokenYearStart() {
		return dateTimeTokenYearStart;
	}

	public void setDateTimeTokenYearStart(int dateTimeTokenYearStart) {
		this.dateTimeTokenYearStart = dateTimeTokenYearStart;
	}

	public int getDateTimeTokenYearEnd() {
		return dateTimeTokenYearEnd;
	}

	public void setDateTimeTokenYearEnd(int dateTimeTokenYearEnd) {
		this.dateTimeTokenYearEnd = dateTimeTokenYearEnd;
	}

	public String getBusinessEntityName() {
		return businessEntityName;
	}

	public void setBusinessEntityName(String businessEntityName) {
		this.businessEntityName = businessEntityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessEntityName == null) ? 0 : businessEntityName.hashCode());
		result = prime * result + configurationIdentifier;
		result = prime * result + dateTimeTokenYearEnd;
		result = prime * result + dateTimeTokenYearStart;
		result = prime * result + (int) (integerTokenEndValue ^ (integerTokenEndValue >>> 32));
		result = prime * result + (int) (integerTokenStartValue ^ (integerTokenStartValue >>> 32));
		result = prime * result + ((stringVaultIdentifer == null) ? 0 : stringVaultIdentifer.hashCode());
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
		Configuration other = (Configuration) obj;
		if (businessEntityName == null) {
			if (other.businessEntityName != null) {
				return false;
			}
		} else if (!businessEntityName.equals(other.businessEntityName)) {
			return false;
		}
		if (configurationIdentifier != other.configurationIdentifier) {
			return false;
		}
		if (dateTimeTokenYearEnd != other.dateTimeTokenYearEnd) {
			return false;
		}
		if (dateTimeTokenYearStart != other.dateTimeTokenYearStart) {
			return false;
		}
		if (integerTokenEndValue != other.integerTokenEndValue) {
			return false;
		}
		if (integerTokenStartValue != other.integerTokenStartValue) {
			return false;
		}
		if (stringVaultIdentifer == null) {
			if (other.stringVaultIdentifer != null) {
				return false;
			}
		} else if (!stringVaultIdentifer.equals(other.stringVaultIdentifer)) {
			return false;
		}
		return true;
	}

}
