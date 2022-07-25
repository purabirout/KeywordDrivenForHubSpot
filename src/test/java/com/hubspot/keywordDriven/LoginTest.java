package com.hubspot.keywordDriven;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;


import com.hubspot.engine.KeywordEngine;

public class LoginTest {
	
	public KeywordEngine keywordEngine;
	
	 @Test  
	    public void LoginTest() throws InvalidFormatException {
	        keywordEngine = new KeywordEngine();
	        keywordEngine.startExecution("Sheet1");
	    }

	  /*  @Test
	    public void signUpTest() throws InvalidFormatException {
	        keywordEngine = new KeywordEngine();
	        keywordEngine.startExecution("signup");
	    }*/
}
