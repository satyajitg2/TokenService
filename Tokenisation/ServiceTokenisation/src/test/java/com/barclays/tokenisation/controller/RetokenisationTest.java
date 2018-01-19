package com.service.tokenisation.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.service.tokenisation.controller.retokenisation.Retokenisation;
import com.service.tokenisation.model.RetokeniseRequest;

public class RetokenisationTest {
	Retokenisation retokenisationObj = new Retokenisation();


	@Test
	public void testCheckForEmptyFields() {
		boolean response = false;

		// Case 1: when all fields are present
		RetokeniseRequest retokeniseRequest1 = new RetokeniseRequest();
		retokeniseRequest1.setSourceDomain("PBTV");
		retokeniseRequest1.setTargetDomain("ACTIMISE");
		retokeniseRequest1.setSourceSystemName("CDI");
		boolean expectedResponse1 = true;
		response = retokenisationObj.checkForEmptyFields(retokeniseRequest1);
		assertEquals(response, expectedResponse1);

		// Case 2: when any of them is empty
		RetokeniseRequest retokeniseRequest2 = new RetokeniseRequest();
		retokeniseRequest2.setSourceDomain("PBTV");
		retokeniseRequest2.setTargetDomain("");
		retokeniseRequest2.setSourceSystemName("CDI");
		boolean expectedResponse2 = false;
		response = retokenisationObj.checkForEmptyFields(retokeniseRequest2);
		assertEquals(response, expectedResponse2);
	}
	

}
