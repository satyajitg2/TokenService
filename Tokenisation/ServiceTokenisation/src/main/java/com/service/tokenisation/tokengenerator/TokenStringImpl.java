package com.service.tokenisation.tokengenerator;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.sequential.StringToken;

/**
 * TokenIntImpl class
 * 
 * @author: Satyajit
 * @version: 1
 */
public class TokenStringImpl {

	public String generateOnDemandToken(String businessEnitity) throws TokenException {
		return new StringToken().createStringToken(businessEnitity);
	}

}
