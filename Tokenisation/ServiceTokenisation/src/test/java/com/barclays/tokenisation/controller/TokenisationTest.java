package com.service.tokenisation.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.service.tokenisation.controller.tokenisation.Tokenisation;
import com.service.tokenisation.model.RequestData;

public class TokenisationTest {

	Tokenisation tokenisationMainObj = new Tokenisation();


	/** Test method to assert if field is empty*/
	@Test
	public void testCheckForEmptyFields() {
		
		// Case 1: when all fields are present
		RequestData requestDataObj1 = new RequestData();
		requestDataObj1.setDomain("PBTV");
		requestDataObj1.setOwningBusinessEntity("CH");
		requestDataObj1.setSourceSystemName("CDI");
		boolean expectedResponse1 = true;
		boolean response1 = tokenisationMainObj.checkForEmptyFields(requestDataObj1);
		assertEquals(response1, expectedResponse1);

		// Case 2: when any of them is empty
		RequestData requestDataObj2 = new RequestData();
		requestDataObj2.setDomain("PBTV");
		requestDataObj2.setOwningBusinessEntity("CH");
		requestDataObj2.setSourceSystemName("");
		boolean expectedResponse2 = false;
		boolean response2 = tokenisationMainObj.checkForEmptyFields(requestDataObj2);
		assertEquals(response2, expectedResponse2);
	}

}
