package com.service.tokenisation.model;

/**
 * CsdRequest
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class CsdRequest {

	String id;
	String sourceFieldName;
	String targetFieldName;
	String tokenType;
	String isRepeatable;
	String fieldValue;
	String status;
	String code;
	String message;
	String tokenCreationDateTime;
	String metadata;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceFieldName() {
		return sourceFieldName;
	}

	public void setSourceFieldName(String sourceFieldName) {
		this.sourceFieldName = sourceFieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getIsRepeatable() {
		return isRepeatable;
	}

	public void setIsRepeatable(String isRepeatable) {
		this.isRepeatable = isRepeatable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTokenCreationDateTime() {
		return tokenCreationDateTime;
	}

	public void setTokenCreationDateTime(String tokenCreationDateTime) {
		this.tokenCreationDateTime = tokenCreationDateTime;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getTargetFieldName() {
		return targetFieldName;
	}

	public void setTargetFieldName(String targetFieldName) {
		this.targetFieldName = targetFieldName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((fieldValue == null) ? 0 : fieldValue.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isRepeatable == null) ? 0 : isRepeatable.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
		result = prime * result + ((sourceFieldName == null) ? 0 : sourceFieldName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((targetFieldName == null) ? 0 : targetFieldName.hashCode());
		result = prime * result + ((tokenCreationDateTime == null) ? 0 : tokenCreationDateTime.hashCode());
		result = prime * result + ((tokenType == null) ? 0 : tokenType.hashCode());
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
		CsdRequest other = (CsdRequest) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (fieldValue == null) {
			if (other.fieldValue != null) {
				return false;
			}
		} else if (!fieldValue.equals(other.fieldValue)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (isRepeatable == null) {
			if (other.isRepeatable != null) {
				return false;
			}
		} else if (!isRepeatable.equals(other.isRepeatable)) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (metadata == null) {
			if (other.metadata != null) {
				return false;
			}
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (sourceFieldName == null) {
			if (other.sourceFieldName != null) {
				return false;
			}
		} else if (!sourceFieldName.equals(other.sourceFieldName)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (targetFieldName == null) {
			if (other.targetFieldName != null) {
				return false;
			}
		} else if (!targetFieldName.equals(other.targetFieldName)) {
			return false;
		}
		if (tokenCreationDateTime == null) {
			if (other.tokenCreationDateTime != null) {
				return false;
			}
		} else if (!tokenCreationDateTime.equals(other.tokenCreationDateTime)) {
			return false;
		}
		if (tokenType == null) {
			if (other.tokenType != null) {
				return false;
			}
		} else if (!tokenType.equals(other.tokenType)) {
			return false;
		}
		return true;
	}

}
