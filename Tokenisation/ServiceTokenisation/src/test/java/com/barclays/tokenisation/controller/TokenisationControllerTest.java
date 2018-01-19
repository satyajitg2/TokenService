package com.service.tokenisation.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.service.tokenisation.controller.tokenisation.TokenisationController;
import com.service.tokenisation.model.CsdRequest;

public class TokenisationControllerTest {
	TokenisationController tokenisationController=new TokenisationController();

	@Test
	public void testIsNullOrEmpty() 
	{
		CsdRequest csdRequestObj1=new CsdRequest();
		// Case 1: when all fields are present
		csdRequestObj1.setId("3");
		csdRequestObj1.setFieldValue("MCo");
		csdRequestObj1.setIsRepeatable("Y");
		csdRequestObj1.setSourceFieldName("AVQ");
		csdRequestObj1.setTargetFieldName("PVTB");
		csdRequestObj1.setTokenType("S");
		boolean expected1=true;
		boolean reponse1=tokenisationController.isNullOrEmpty(csdRequestObj1);
		assertEquals(expected1, reponse1);
		
		// Case 2:when any of them is empty or Null
		CsdRequest csdRequestObj2=new CsdRequest();
		csdRequestObj2.setId("3");
		csdRequestObj2.setFieldValue("MCo");
		csdRequestObj2.setIsRepeatable("");
		csdRequestObj2.setSourceFieldName("AVQ");
		csdRequestObj2.setTargetFieldName("");
		csdRequestObj2.setTokenType("S");
		boolean expected2=false;
		boolean reponse2=tokenisationController.isNullOrEmpty(csdRequestObj2);
		assertEquals(expected2, reponse2);
		

	}

}
