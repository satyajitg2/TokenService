package com.service.tokenisation.utility;

import java.io.StringReader;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;

/**
 * SSLParser class is responsible for reading the Http Request, extract the x509 certificate from the request and
 * process the certificates. The Authentication Yes/No is controlled from external property file,
 * serviceCommon.properties - isCertAuthenticationReq
 * 
 * @author: Satyajit Singh
 * @version: 1.1
 */
public class SSLParser {

	private static final Logger LOG = Logger.getLogger(SSLParser.class);

	public SSLParser() {
	}

	/**
	 * @param request
	 * @return boolean True if valid, else returns false.
	 * @throws TokenException
	 */
	public String verifySSL(HttpServletRequest request) throws TokenException {
		LOG.debug(" Invoking verifySSL");

		String commonName = "";
		try {

			StringBuilder stringBuilder = new StringBuilder();
			X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");

			for (X509Certificate cert : certs) {

				stringBuilder.append(cert.getSubjectX500Principal().getName());
				commonName = cert.getSubjectX500Principal().getName();
				Principal principalX500 = cert.getSubjectX500Principal();

				Properties prop = new Properties();
				prop.load(new StringReader(principalX500.getName().replaceAll(",", "\n")));
				commonName = (String) prop.get("CN");

				LOG.debug(" **** Print Certificate Details ***** ");
				LOG.debug(" Certificate CN name :" + cert.getSubjectX500Principal().getName() + "\n");
			}
			if (commonName == null || commonName == "") {
				throw new TokenException(Constants.API_NOAUTH);
			}

		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
			throw new TokenException(Constants.API_NOAUTH);
		}
		LOG.debug(" Leaving verifySSL \n");
		return commonName.trim();
	}

}
