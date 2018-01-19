package com.service.tokenseeder.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


	public class TokenException extends Exception {

		/**
		 * 
		 */
		private static final Logger LOGGER = LoggerFactory.getLogger(TokenException.class);
		private static final long serialVersionUID = 1L;

		public TokenException(String s) {
			super(s);
			try {
				
				LOGGER.error("EXIT With Error Code : 1");
				System.exit(1);
			} catch (Exception e) {
				LOGGER.error("Exception ", e);
			}
		}
	}

