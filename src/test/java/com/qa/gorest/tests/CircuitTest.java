package com.qa.gorest.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitTest extends BaseTest{
	
	
	@BeforeMethod
	private void setUpRestClient() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test          
	public void circuitTest() {
		Response response = restClient.get(CIRCUIT_ENDPOINT+ "/2017/circuits.json", false, true);
		
		int  statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
			String strResponse = response.asString();
			System.out.println(strResponse);
			
//			int circuitSize = JsonPath.read(strResponse,"$..CircuitTable.Circuits.size()");
//			System.out.println("circuitSize: "+ circuitSize);
			
			//wrote utility for jasonPath
			int circuitSize = JsonPathValidator.read(response, "$..CircuitTable.Circuits.size()");
			System.out.println("circuitSize: "+ circuitSize);
			
			
			
			int circuitLength = JsonPath.read(strResponse,"$..CircuitTable.Circuits.length()");
			System.out.println("circuitLength: "+ circuitLength);
			
//			List<String> CountryList  = JsonPath.read(strResponse, "$..CircuitTable..country");
//			System.out.println(CountryList);
//			System.out.println(CountryList.size());
			
			//wrote utility for jasonPath
			List<String> CountryList  = JsonPathValidator.readList(response, "$..CircuitTable..country");
			System.out.println(CountryList);
			
			List<String> CountryList1  = JsonPathValidator.readList(response, "$..CircuitTable.Circuits[?(@.circuitId == 'catalunya')].Location.country");
			System.out.println("CountryList1: "+CountryList1);
			Assert.assertTrue(CountryList1.contains("Spain"));
			
//			String circuitId = JsonPath.read(strResponse, "$.MRData.CircuitTable.Circuits[?(@.country == 'Australia')].circuitId");
//			System.out.println(circuitId);		
			
//			List<String> circuitId = JsonPath.read(strResponse, "$.MRData.CircuitTable.Circuits.[?(@.country == 'Australia')].circuitId");
//			System.out.println("circuitId: "+circuitId);
			
		
	}
	
	
}
