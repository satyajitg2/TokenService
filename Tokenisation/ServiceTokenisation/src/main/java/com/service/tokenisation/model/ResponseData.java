package com.service.tokenisation.model;

import java.util.List;

/**
 * POJO class for Tokenisation ResponseData
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class ResponseData {

	private String sourceSystemName;
	private String owningBusinessEntity;
	private String domain;
	private String code;
	private String message;
	private Status status;
	private List<CsdResponse> csds;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<CsdResponse> getCsds() {
		return csds;
	}

	public void setCsds(List<CsdResponse> csds) {
		this.csds = csds;
	}

	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}

	public String getOwningBusinessEntity() {
		return owningBusinessEntity;
	}

	public void setOwningBusinessEntity(String owningBusinessEntity) {
		this.owningBusinessEntity = owningBusinessEntity;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((csds == null) ? 0 : csds.hashCode());
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((owningBusinessEntity == null) ? 0 : owningBusinessEntity.hashCode());
		result = prime * result + ((sourceSystemName == null) ? 0 : sourceSystemName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ResponseData other = (ResponseData) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (csds == null) {
			if (other.csds != null) {
				return false;
			}
		} else if (!csds.equals(other.csds)) {
			return false;
		}
		if (domain == null) {
			if (other.domain != null) {
				return false;
			}
		} else if (!domain.equals(other.domain)) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (owningBusinessEntity == null) {
			if (other.owningBusinessEntity != null) {
				return false;
			}
		} else if (!owningBusinessEntity.equals(other.owningBusinessEntity)) {
			return false;
		}
		if (sourceSystemName == null) {
			if (other.sourceSystemName != null) {
				return false;
			}
		} else if (!sourceSystemName.equals(other.sourceSystemName)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

}
