package com.service.tokenisation.model;

import java.util.List;

/**
 * POJO class for Tokenisation RequestData
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class RequestData {

	private String sourceSystemName;
	private String owningBusinessEntity;
	private String domain;
	private List<CsdRequest> csds;

	public List<CsdRequest> getCsds() {
		return csds;
	}

	public void setCsds(List<CsdRequest> csds) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((csds == null) ? 0 : csds.hashCode());
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((owningBusinessEntity == null) ? 0 : owningBusinessEntity.hashCode());
		result = prime * result + ((sourceSystemName == null) ? 0 : sourceSystemName.hashCode());
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
		RequestData other = (RequestData) obj;
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
		return true;
	}

}
