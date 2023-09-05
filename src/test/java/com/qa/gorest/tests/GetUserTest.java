package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.*;
import com.qa.rest.constants.APIHttpStatus;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest{
	
	
	@BeforeMethod
	public void setUpRestClient() {
		restClient = new RestClient(prop, baseURI);
	}
	
	
	@Test(enabled=true, priority=1)
	public void getUserTest() {

		restClient.get(GOREST_ENDPOINT,true, true) 
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	
	@Test(priority=2)
	public void getSpecificUserTest() {
		
		restClient.get(GOREST_ENDPOINT+"/5115756", true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.body("id", equalTo(5115756));

	}
	
	@Test(priority=3)
	public void getUserWithQueryParamsTest() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("name", "Chatura Malik");
		queryParams.put("gender", "female");
		
		restClient.get(GOREST_ENDPOINT, null, queryParams, true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
	}
	
}
