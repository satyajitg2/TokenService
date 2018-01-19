package com.service.tokenisation.model;

/**
 * POJO class for DetokeniseRequestData
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class DetokeniseRequestData {
	private EncryptionReqObj encryption;
	private String domain;

	public EncryptionReqObj getEncryption() {
		return encryption;
	}

	public void setEncryption(EncryptionReqObj encryption) {
		this.encryption = encryption;
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
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((encryption == null) ? 0 : encryption.hashCode());
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
		DetokeniseRequestData other = (DetokeniseRequestData) obj;
		if (domain == null) {
			if (other.domain != null) {
				return false;
			}
		} else if (!domain.equals(other.domain)) {
			return false;
		}
		if (encryption == null) {
			if (other.encryption != null) {
				return false;
			}
		} else if (!encryption.equals(other.encryption)) {
			return false;
		}
		return true;
	}

}
