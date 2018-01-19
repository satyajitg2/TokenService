package com.service.tokenisation.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.hibernate.HibernateUtil;
import com.service.tokenisation.utility.Constants;

public class EncryptService {
	private static final Logger LOG = Logger.getLogger(EncryptService.class);

	public String getEncryptedDetokenResponseString(String detokenResponseSrting) throws TokenException {
		LOG.debug("Invoking getEncryptedDetokenResponseString");
		try {
			Client client = ClientBuilder.newClient();
			String url = HibernateUtil.getEncryptionURL();
			LOG.debug("Encryption URL:" + url);

			WebTarget target = client.target(url);
			String encryptResponse = target.request(MediaType.APPLICATION_JSON).post(
					Entity.json(detokenResponseSrting), String.class);
			return encryptResponse;
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
	}
}
