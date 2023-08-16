package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.UserLombok;
import com.qa.gorest.utils.StringUtils;
import com.qa.rest.constants.APIHttpStatus;

public class APISchemaValidator extends BaseTest{
	
	@BeforeMethod
	public void setUpRestClient() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void createUserApiSchemaTest() {

		UserLombok user = new UserLombok("savi", "female", StringUtils.getRandomEmailID(), "active");
		
		//post  
		restClient.post(GOREST_ENDPOINT, "json", user, true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
		.body(matchesJsonSchemaInClasspath("createUserSchema.json"));
		
	}
}
