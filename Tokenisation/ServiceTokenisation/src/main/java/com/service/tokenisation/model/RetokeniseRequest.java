package com.service.tokenisation.model;

import java.util.List;

/**
 * POJO class for RetokeniseRequest Used to create Retokenisation request from JSON
 * 
 * @author: Satyajit Singh
 * @version: 1
 */
public class RetokeniseRequest {

	private String sourceSystemName;
	private String sourceDomain;
	private String targetDomain;
	private List<RetokenReqData> data;

	public List<RetokenReqData> getData() {
		return data;
	}

	public void setData(List<RetokenReqData> data) {
		this.data = data;
	}

	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}

	public String getSourceDomain() {
		return sourceDomain;
	}

	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}

	public String getTargetDomain() {
		return targetDomain;
	}

	public void setTargetDomain(String targetDomain) {
		this.targetDomain = targetDomain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((sourceDomain == null) ? 0 : sourceDomain.hashCode());
		result = prime * result + ((sourceSystemName == null) ? 0 : sourceSystemName.hashCode());
		result = prime * result + ((targetDomain == null) ? 0 : targetDomain.hashCode());
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
		RetokeniseRequest other = (RetokeniseRequest) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (sourceDomain == null) {
			if (other.sourceDomain != null) {
				return false;
			}
		} else if (!sourceDomain.equals(other.sourceDomain)) {
			return false;
		}
		if (sourceSystemName == null) {
			if (other.sourceSystemName != null) {
				return false;
			}
		} else if (!sourceSystemName.equals(other.sourceSystemName)) {
			return false;
		}
		if (targetDomain == null) {
			if (other.targetDomain != null) {
				return false;
			}
		} else if (!targetDomain.equals(other.targetDomain)) {
			return false;
		}
		return true;
	}

}
