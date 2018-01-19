package com.service.tokenisation.model;

/**
 * POJO class for TokenSetDecision
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class TokenSetDecision {
	private int tokenSet;
	private String tokenIntegerTable;
	private String tokenStringTable;
	private String tokenDateTimeTable;

	public int getTokenSet() {
		return tokenSet;
	}

	public void setTokenSet(int tokenSet) {
		this.tokenSet = tokenSet;
	}

	public String getTokenIntegerTable() {
		return tokenIntegerTable;
	}

	public void setTokenIntegerTable(String tokenIntegerTable) {
		this.tokenIntegerTable = tokenIntegerTable;
	}

	public String getTokenStringTable() {
		return tokenStringTable;
	}

	public void setTokenStringTable(String tokenStringTable) {
		this.tokenStringTable = tokenStringTable;
	}

	public String getTokenDateTimeTable() {
		return tokenDateTimeTable;
	}

	public void setTokenDateTimeTable(String tokenDateTimeTable) {
		this.tokenDateTimeTable = tokenDateTimeTable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tokenDateTimeTable == null) ? 0 : tokenDateTimeTable.hashCode());
		result = prime * result + ((tokenIntegerTable == null) ? 0 : tokenIntegerTable.hashCode());
		result = prime * result + tokenSet;
		result = prime * result + ((tokenStringTable == null) ? 0 : tokenStringTable.hashCode());
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
		TokenSetDecision other = (TokenSetDecision) obj;
		if (tokenDateTimeTable == null) {
			if (other.tokenDateTimeTable != null) {
				return false;
			}
		} else if (!tokenDateTimeTable.equals(other.tokenDateTimeTable)) {
			return false;
		}
		if (tokenIntegerTable == null) {
			if (other.tokenIntegerTable != null) {
				return false;
			}
		} else if (!tokenIntegerTable.equals(other.tokenIntegerTable)) {
			return false;
		}
		if (tokenSet != other.tokenSet) {
			return false;
		}
		if (tokenStringTable == null) {
			if (other.tokenStringTable != null) {
				return false;
			}
		} else if (!tokenStringTable.equals(other.tokenStringTable)) {
			return false;
		}
		return true;
	}

}
