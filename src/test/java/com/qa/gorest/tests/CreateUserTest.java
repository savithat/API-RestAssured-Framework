package com.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.*;
import com.qa.gorest.utils.ExcelUtils;
import com.qa.gorest.utils.StringUtils;
import com.qa.rest.constants.APIConstants;
import com.qa.rest.constants.APIHttpStatus;

public class CreateUserTest extends BaseTest{
	

	
	@BeforeMethod
	public void setUpRestClient() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] getUserTestData() {
		return new Object[][] { 
			{"savi", "female","active"},
			{"savi1", "male","inactive"},
			{"savi2", "female","active"}
			
		};
	}
	
	
	@DataProvider
	public Object[][] getUserTestExcelData() {
		return ExcelUtils.getTestData(APIConstants.GO_REST_USER_SHEET_NAME);
		
	}
	
	
	@Test(dataProvider = "getUserTestExcelData")
	public void postUserTest(String name, String gender, String status) {

//		UserLombok user = new UserLombok("savi", "female", StringUtils.getRandomEmailID(), "active");
		UserLombok user = new UserLombok(name, gender, StringUtils.getRandomEmailID(), status);
		
		//post  - get id
		int id = restClient.post(GOREST_ENDPOINT, "json", user, true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.extract()
		.path("id");
		
		System.out.println("id:"+ id);
		
		//get method
		
		RestClient restClient = new RestClient(prop, baseURI);
		restClient.get(GOREST_ENDPOINT+"/"+ id, true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
	}
	

}
