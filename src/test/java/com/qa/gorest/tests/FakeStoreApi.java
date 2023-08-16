package com.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.FakeStoreAPIProducts;
import com.qa.rest.constants.APIHttpStatus;

import io.restassured.response.Response;

public class FakeStoreApi extends BaseTest{
	
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
	
	
	@Test
	public void getSingleProduct_FakeStoreApi() {
		Response getSingleProduct_Res = restClient.get(FAKESTORE_GETPRODUCT_ENDPOINT + "/" + 1, false, true)
		.then()
		.log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.extract().response();
		
	}
	
	
	
	@Test
	public void addAnewProduct_FakeStoreApi() {
		
		FakeStoreAPIProducts fakeStoreAPIProd = new FakeStoreAPIProducts("test product", 13.5, "lorem ipsum set","https://i.pravatar.cc", "electronics");
		
		Response addANewProduct_Res = restClient.post(FAKESTORE_GETPRODUCT_ENDPOINT, "json", fakeStoreAPIProd, false, true)
		.then()
		.log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.extract().response();
		
	}
	
}
