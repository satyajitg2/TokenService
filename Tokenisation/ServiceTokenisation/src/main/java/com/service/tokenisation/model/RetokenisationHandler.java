package com.service.tokenisation.model;

public class RetokenisationHandler {

	private int retokenId;
	private String targetFieldName;
	private String dataType;
	private String oldToken;
	private String newToken;

	public String getTargetFieldName() {
		return targetFieldName;
	}

	public void setTargetFieldName(String targetFieldName) {
		this.targetFieldName = targetFieldName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getNewToken() {
		return newToken;
	}

	public void setNewToken(String newToken) {
		this.newToken = newToken;
	}

	public int getRetokenId() {
		return retokenId;
	}

	public void setRetokenId(int retokenId) {
		this.retokenId = retokenId;
	}

	public String getOldToken() {
		return oldToken;
	}

	public void setOldToken(String oldToken) {
		this.oldToken = oldToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((newToken == null) ? 0 : newToken.hashCode());
		result = prime * result + ((oldToken == null) ? 0 : oldToken.hashCode());
		result = prime * result + retokenId;
		result = prime * result + ((targetFieldName == null) ? 0 : targetFieldName.hashCode());
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
		RetokenisationHandler other = (RetokenisationHandler) obj;
		if (dataType == null) {
			if (other.dataType != null) {
				return false;
			}
		} else if (!dataType.equals(other.dataType)) {
			return false;
		}
		if (newToken == null) {
			if (other.newToken != null) {
				return false;
			}
		} else if (!newToken.equals(other.newToken)) {
			return false;
		}
		if (oldToken == null) {
			if (other.oldToken != null) {
				return false;
			}
		} else if (!oldToken.equals(other.oldToken)) {
			return false;
		}
		if (retokenId != other.retokenId) {
			return false;
		}
		if (targetFieldName == null) {
			if (other.targetFieldName != null) {
				return false;
			}
		} else if (!targetFieldName.equals(other.targetFieldName)) {
			return false;
		}
		return true;
	}

}
