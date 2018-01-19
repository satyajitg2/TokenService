package com.service.tokenisation.model;

/**
 * POJO class for CsdResponse
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class CsdResponse {

	private String id;
	private String sourceFieldName;
	private String tokenType;
	private Boolean isRepeatable;
	private String fieldValue;
	private String token;
	private String status;
	private String code;
	private String message;
	private String tokenCreationDateTime;
	private String metadata;

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

	public String gettokenType() {
		return tokenType;
	}

	public void settokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Boolean getIsRepeatable() {
		return isRepeatable;
	}

	public void setIsRepeatable(Boolean isRepeatable) {
		this.isRepeatable = isRepeatable;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		CsdResponse other = (CsdResponse) obj;
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
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.equals(other.token)) {
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
