package com.service.tokenisation.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.service.tokenisation.controller.retokenisation.RetokenisationController;
import com.service.tokenisation.model.RetokenReqData;

public class RetokenisationControllerTest {
	RetokenisationController retokenisationControllerObj=new RetokenisationController();

	@Test
	public void testIsNullOrEmpty() 
	{
		
		// Case 1: when all fields are present
		RetokenReqData retokenReqDataObj1=new RetokenReqData();
		retokenReqDataObj1.setId("1");
		retokenReqDataObj1.setTargetFieldName("PVTB");
		retokenReqDataObj1.setToken("^^#WaaaacU");
		boolean expected1=false;
		boolean response1=retokenisationControllerObj.isNullOrEmpty(retokenReqDataObj1);
		assertEquals(expected1, response1);
		
		// Case 2: when any of them is empty or null
		RetokenReqData retokenReqDataObj2=new RetokenReqData();
		retokenReqDataObj2.setId("1");
		retokenReqDataObj2.setTargetFieldName("");
		retokenReqDataObj2.setToken("^^#WaaaacU");
		boolean expected2=true;
		boolean response2=retokenisationControllerObj.isNullOrEmpty(retokenReqDataObj2);
		assertEquals(expected2, response2);
		
		
	}

}
