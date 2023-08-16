package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	
    public static final String GOREST_ENDPOINT = "/public/v2/users"; 
    public static final String CIRCUIT_ENDPOINT = "/api/f1";
    public static final String REQRES_ENDPOINT = "/api/users";
    public static final String AMEDUS_TOKEN_ENDPOINT = "/v1/security/oauth2/token";
    public static final String AMEDUS_FLIGHT_BOOKING_ENDPOINT = "/v1/shopping/flight-destinations";
    public static final String FAKESTORE_GETPRODUCT_ENDPOINT = "/products";
    
    
	
	protected Properties prop;
	protected RestClient restClient;
	protected ConfigurationManager configManager;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI){
		RestAssured.filters(new AllureRestAssured());
		
		configManager = new ConfigurationManager();
		prop = configManager.init();
		//If u want get baseURI from config file use the below commented line and remove baseURI passing in Parameters
//		String baseURI = prop.getProperty("baseURI");	
		this.baseURI = baseURI;
	//	restClient = new RestClient(prop, baseURI);
	}

}
