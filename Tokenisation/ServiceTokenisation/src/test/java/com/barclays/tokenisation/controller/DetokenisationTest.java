package com.service.tokenisation.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.service.tokenisation.controller.detokenisation.Detokenisation;
import com.service.tokenisation.model.DetokeniseRequestData;
import com.service.tokenisation.model.EncryptionReqObj;

public class DetokenisationTest {
	
	Detokenisation detokenisationObj=new Detokenisation();

	@Test
	public void testCheckForEmptyFields() 
	{
		boolean response = false;

		// Case 1: when all fields are present
		EncryptionReqObj encryObj=new EncryptionReqObj();
		encryObj.setAlg("fhgj");
		encryObj.setEncryption_key("gdfg");
		DetokeniseRequestData detokeniseRequest1 = new DetokeniseRequestData();
		detokeniseRequest1.setEncryption(encryObj);
		detokeniseRequest1.setDomain("PBTV");
		boolean expected1= true;
		response = detokenisationObj.checkForEmptyFields(detokeniseRequest1);
		assertEquals(response, expected1);

		// Case 2: when any of them is empty
		DetokeniseRequestData detokeniseRequest2 = new DetokeniseRequestData();
		detokeniseRequest2.setEncryption(encryObj);
		encryObj.setAlg("fhgj");
		encryObj.setEncryption_key("gdfg");
		detokeniseRequest2.setDomain("");
		boolean expected2 = false;
		response = detokenisationObj.checkForEmptyFields(detokeniseRequest2);
		assertEquals(response, expected2);
		
	}

}
