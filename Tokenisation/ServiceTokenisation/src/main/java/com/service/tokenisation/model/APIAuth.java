package com.service.tokenisation.model;

import java.io.Serializable;

public class APIAuth implements Serializable {

	private static final long serialVersionUID = 1L;
	private int entityId;
	private String systemName;
	private String businessEntity;
	private String domain;
	private String apiName;

	public APIAuth() {
		super();
	}

	public APIAuth(String systemName, String businessEntity, String domain, String apiName) {
		super();
		this.systemName = systemName;
		this.businessEntity = businessEntity;
		this.domain = domain;
		this.apiName = apiName;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getBusinessEntity() {
		return businessEntity;
	}

	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiName == null) ? 0 : apiName.hashCode());
		result = prime * result + ((businessEntity == null) ? 0 : businessEntity.hashCode());
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + entityId;
		result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
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
		APIAuth other = (APIAuth) obj;
		if (apiName == null) {
			if (other.apiName != null) {
				return false;
			}
		} else if (!apiName.equals(other.apiName)) {
			return false;
		}
		if (businessEntity == null) {
			if (other.businessEntity != null) {
				return false;
			}
		} else if (!businessEntity.equals(other.businessEntity)) {
			return false;
		}
		if (domain == null) {
			if (other.domain != null) {
				return false;
			}
		} else if (!domain.equals(other.domain)) {
			return false;
		}
		if (entityId != other.entityId) {
			return false;
		}
		if (systemName == null) {
			if (other.systemName != null) {
				return false;
			}
		} else if (!systemName.equals(other.systemName)) {
			return false;
		}
		return true;
	}

}
