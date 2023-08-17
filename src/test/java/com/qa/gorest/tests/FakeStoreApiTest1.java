package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.rest.constants.APIHttpStatus;

import io.restassured.response.Response;

public class FakeStoreApiTest1 extends BaseTest{
	@BeforeMethod
	private void setUpRestClient() {
		restClient = new RestClient(prop, baseURI);
	}	
	
	@Test
	public void getAllProduct_FakeStoreApi() {
		Response getAllProduct_Res = restClient.get(FAKESTORE_GETPRODUCT_ENDPOINT, false, true)
		.then()
		.log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.extract().response();
		
		String res = getAllProduct_Res.asString();
		
	}
}
