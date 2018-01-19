package com.service.tokenisation.tokengenerator;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.sequential.IntegerToken;

/**
 * TokenIntImpl class
 * 
 * @author: Satyajit
 * @version: 1
 */
public class TokenIntImpl {

	public String generateOnDemandToken(String businessEnitity) throws TokenException {

		long intToken = new IntegerToken().createIntegerToken(businessEnitity);
		return Long.toString(intToken);
	}

}
