package com.qa.gorest.tests;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.gorest.utils.XMLPathValidator;
import com.qa.rest.constants.APIHttpStatus;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class xmlCircuitApiTest1 {

	@Test
	public void xmlTestValidation() {

		int year = 2017;

		Response response = given().baseUri("http://ergast.com").pathParam("year", year).log().all().when()
				.get("api/f1/{year}/circuits.xml");

	
		int status = response.statusCode();
		Assert.assertEquals(status, APIHttpStatus.OK_200.getCode());

		//extracting String
		String url = XMLPathValidator.xmlPathValidator(response, "MRData.@url");
		System.out.println("URL:" + url);
		System.out.println();
		
		String url1 = XMLPathValidator.xmlPathValidator(response, "MRData.CircuitTable.Circuit[0].@url");
		System.out.println("URL1:" + url1);
		System.out.println();
		
		String url2 = XMLPathValidator.xmlPathValidator(response, "MRData.CircuitTable.Circuit.findAll{it.Location.Country == 'China'}.@url");
		System.out.println("URL2:" + url2);
		System.out.println();
		
		
		String locality = XMLPathValidator.xmlPathValidator(response, "**.findAll{it.@circuitId=='albert_park'}.Location.Locality");
		System.out.println("locality:" + locality);
		System.out.println();
		
		String lat = XMLPathValidator.xmlPathValidator(response, "**.findAll { it.Country == 'Australia' }.@lat");
		System.out.println("lat:" + lat);
		System.out.println();
		
		
		//extracting List
		System.out.println("circuitName****");
		List<String> listCircuitName = XMLPathValidator.xmlPathListValidator(response, "MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("listCircuitName:" + listCircuitName);
		System.out.println();
		
		System.out.println("Country****");
		List<String> CountryLIST = XMLPathValidator.xmlPathListValidator(response, "MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("listCircuitName:" + listCircuitName);
		for (String Country : CountryLIST) {
			System.out.println(Country);
		}
		System.out.println();		

	}
}
