package com.service.tokenisation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.service.tokenisation.controller.DetokenisationTest;
import com.service.tokenisation.controller.RetokenisationControllerTest;
import com.service.tokenisation.controller.RetokenisationTest;
import com.service.tokenisation.controller.TokenisationControllerTest;
import com.service.tokenisation.controller.TokenisationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TokenisationControllerTest.class,TokenisationTest.class,DetokenisationTest.class,RetokenisationControllerTest.class,RetokenisationTest.class
	 })

public class TokenisationJunitTestSuite {
	
}
