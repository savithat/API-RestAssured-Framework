package com.qa.gorest.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.rest.constants.APIHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest{
	
	
	private String accessToken;
	
	@Parameters({"baseURI","grantType","clientId","clientSecret"})
	@BeforeMethod
	public void flightApiSetUp(String baseURI, String grantType, String clientId, String clientSecret) {
		RestClient restClient = new RestClient(prop, baseURI);
		accessToken =restClient.getAccessToken(AMEDUS_TOKEN_ENDPOINT, grantType , clientId, clientSecret);
		System.out.println(accessToken);
	}
	
	
	
	@Test
	public void getFlightInfoTest() {
		
		RestClient restClient = new RestClient(prop, baseURI);
		
	
		
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", 200);
		
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", "Bearer "+accessToken);
		
		Response response = restClient.get(AMEDUS_FLIGHT_BOOKING_ENDPOINT, queryParams,  headerMap, false, true)
		.then()
		.log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.extract()
		.response();
		
		JsonPath resJson = response.jsonPath();
		String type = resJson.get("data[0].type");	
		System.out.println("destination: "+type);

	}
	

}
