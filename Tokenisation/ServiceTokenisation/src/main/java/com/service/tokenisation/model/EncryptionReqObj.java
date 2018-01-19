package com.service.tokenisation.model;

/**
 * POJO class for EncryptionReqObj
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class EncryptionReqObj {
	private String alg;
	private String encryption_key;

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getEncryption_key() {
		return encryption_key;
	}

	public void setEncryption_key(String encryption_key) {
		this.encryption_key = encryption_key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alg == null) ? 0 : alg.hashCode());
		result = prime * result + ((encryption_key == null) ? 0 : encryption_key.hashCode());
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
		EncryptionReqObj other = (EncryptionReqObj) obj;
		if (alg == null) {
			if (other.alg != null) {
				return false;
			}
		} else if (!alg.equals(other.alg)) {
			return false;
		}
		if (encryption_key == null) {
			if (other.encryption_key != null) {
				return false;
			}
		} else if (!encryption_key.equals(other.encryption_key)) {
			return false;
		}
		return true;
	}

}
