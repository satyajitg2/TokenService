package com.service.tokenisation.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * POJO class for TokenString
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class TokenString {

	private String token;
	private boolean repeatableFlag;
	private Date tokenExpiryDate;
	private Timestamp tokenCreationDatetime;
	private String cSDValue;
	private String sourceFieldName;
	private String targetFieldName;
	private String tokenMetadata;
	private String domainName;
	private String businessEntityName;
	private String systemName;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isRepeatableFlag() {
		return repeatableFlag;
	}

	public Date getTokenExpiryDate() {
		return tokenExpiryDate;
	}

	public void setTokenExpiryDate(Date tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}

	public void setRepeatableFlag(boolean repeatableFlag) {
		this.repeatableFlag = repeatableFlag;
	}

	public Timestamp getTokenCreationDatetime() {
		return tokenCreationDatetime;
	}

	public void setTokenCreationDatetime(Timestamp tokenCreationDatetime) {
		this.tokenCreationDatetime = tokenCreationDatetime;
	}

	public String getcSDValue() {
		return cSDValue;
	}

	public void setcSDValue(String cSDValue) {
		this.cSDValue = cSDValue;
	}

	public String getSourceFieldName() {
		return sourceFieldName;
	}

	public void setSourceFieldName(String sourceFieldName) {
		this.sourceFieldName = sourceFieldName;
	}

	public String getTokenMetadata() {
		return tokenMetadata;
	}

	public void setTokenMetadata(String tokenMetadata) {
		this.tokenMetadata = tokenMetadata;
	}

	public String getTargetFieldName() {
		return targetFieldName;
	}

	public void setTargetFieldName(String targetFieldName) {
		this.targetFieldName = targetFieldName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getBusinessEntityName() {
		return businessEntityName;
	}

	public void setBusinessEntityName(String businessEntityName) {
		this.businessEntityName = businessEntityName;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessEntityName == null) ? 0 : businessEntityName.hashCode());
		result = prime * result + ((cSDValue == null) ? 0 : cSDValue.hashCode());
		result = prime * result + ((domainName == null) ? 0 : domainName.hashCode());
		result = prime * result + (repeatableFlag ? 1231 : 1237);
		result = prime * result + ((sourceFieldName == null) ? 0 : sourceFieldName.hashCode());
		result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
		result = prime * result + ((targetFieldName == null) ? 0 : targetFieldName.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((tokenCreationDatetime == null) ? 0 : tokenCreationDatetime.hashCode());
		result = prime * result + ((tokenExpiryDate == null) ? 0 : tokenExpiryDate.hashCode());
		result = prime * result + ((tokenMetadata == null) ? 0 : tokenMetadata.hashCode());
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
		TokenString other = (TokenString) obj;
		if (businessEntityName == null) {
			if (other.businessEntityName != null) {
				return false;
			}
		} else if (!businessEntityName.equals(other.businessEntityName)) {
			return false;
		}
		if (cSDValue == null) {
			if (other.cSDValue != null) {
				return false;
			}
		} else if (!cSDValue.equals(other.cSDValue)) {
			return false;
		}
		if (domainName == null) {
			if (other.domainName != null) {
				return false;
			}
		} else if (!domainName.equals(other.domainName)) {
			return false;
		}
		if (repeatableFlag != other.repeatableFlag) {
			return false;
		}
		if (sourceFieldName == null) {
			if (other.sourceFieldName != null) {
				return false;
			}
		} else if (!sourceFieldName.equals(other.sourceFieldName)) {
			return false;
		}
		if (systemName == null) {
			if (other.systemName != null) {
				return false;
			}
		} else if (!systemName.equals(other.systemName)) {
			return false;
		}
		if (targetFieldName == null) {
			if (other.targetFieldName != null) {
				return false;
			}
		} else if (!targetFieldName.equals(other.targetFieldName)) {
			return false;
		}
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.equals(other.token)) {
			return false;
		}
		if (tokenCreationDatetime == null) {
			if (other.tokenCreationDatetime != null) {
				return false;
			}
		} else if (!tokenCreationDatetime.equals(other.tokenCreationDatetime)) {
			return false;
		}
		if (tokenExpiryDate == null) {
			if (other.tokenExpiryDate != null) {
				return false;
			}
		} else if (!tokenExpiryDate.equals(other.tokenExpiryDate)) {
			return false;
		}
		if (tokenMetadata == null) {
			if (other.tokenMetadata != null) {
				return false;
			}
		} else if (!tokenMetadata.equals(other.tokenMetadata)) {
			return false;
		}
		return true;
	}

}
