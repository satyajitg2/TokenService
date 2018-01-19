package com.service.tokenisation.tokengenerator;

import java.sql.Timestamp;

import com.service.tokenisation.exception.TokenException;
import com.service.tokenisation.sequential.DateTimeToken;

/**
 * The class TokenDateImpl implements the generate real time DateTime tokens and returns the list of DateTime
 * 
 * @author: Satyajit
 * @version 1.0
 */
public class TokenDateImpl {

	public Timestamp generateOnDemandToken(String businessEnitity) throws TokenException {
		return new DateTimeToken().createDateTimeToken(businessEnitity);
	}

}
