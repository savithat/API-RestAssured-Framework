package com.qa.gorest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkException.APIFrameworkException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
//	private static final String BASEURI = "https://gorest.co.in";
//	private static final String BEARER_TOKEN = "3bf166a186d56fad4a4de83b598d7200541015e059bdc75714864d9fa322d6f9";
	static RequestSpecBuilder reqSpecBuilder;
	
	
	private String baseURI;
	private Properties prop;
	
	public RestClient(Properties prop, String baseURI) {
		reqSpecBuilder  = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
		
	}
	
	private void addAuthorizationHeader() {
		reqSpecBuilder.addHeader("Authorization", "Bearer "+ prop.getProperty("tokenId"));
	}
	
	private void setrequestContentType(String ContentType) {
		
		switch(ContentType.toLowerCase()) {
		case "json":
			reqSpecBuilder.setContentType(io.restassured.http.ContentType.JSON);
			break;
		case "xml":
			reqSpecBuilder.setContentType(io.restassured.http.ContentType.XML);
			break;
		case "html":
			reqSpecBuilder.setContentType(io.restassured.http.ContentType.HTML);
			break;	
		case "text":
			reqSpecBuilder.setContentType(io.restassured.http.ContentType.TEXT);
			break;	
		case "multipart":
			reqSpecBuilder.setContentType(io.restassured.http.ContentType.MULTIPART);
			break;		
		default:
			System.out.println("please pass the right content type...");
			throw new APIFrameworkException("Invalid content type");

			}
		}	
	
	
	private RequestSpecification createRequestSpecBuilder(boolean includeAuth) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(includeAuth) {
		addAuthorizationHeader();
		}
		return reqSpecBuilder.build();
	}
	
	private RequestSpecification createRequestSpecBuilder(Map<String, String> headerMap, boolean includeAuth) {
		reqSpecBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
			addAuthorizationHeader();
			}
		
		if(headerMap != null) {
			reqSpecBuilder.addHeaders(headerMap);
		}
		return reqSpecBuilder.build();
	}
	
	private RequestSpecification createRequestSpecBuilder(Map<String, String> headerMap, Map<String, Object> queryParams, boolean includeAuth) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		if(headerMap != null) {
			reqSpecBuilder.addHeaders(headerMap);
		}
		
		if(queryParams != null) {
			reqSpecBuilder.addParams(queryParams);
		}
		
		return reqSpecBuilder.build();
	}
	
	
	
	private RequestSpecification createRequestSpecBuilder(Object requestBody, String contentType, boolean includeAuth) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		if(requestBody != null) {
			reqSpecBuilder.setBody(requestBody);
		}
		setrequestContentType(contentType);
		
		return reqSpecBuilder.build();
	}
	
	
	private RequestSpecification createRequestSpecBuilder(Object requestBody, String contentType, Map<String, String> headerMap, boolean includeAuth) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		if(requestBody != null) {
			reqSpecBuilder.setBody(requestBody);
		}
		
		if(headerMap != null) {
			
		}
		setrequestContentType(contentType);
		
		return reqSpecBuilder.build();
	}

	
	//http method utils
	
	public Response get(String serviceUrl, boolean includeAuth, boolean log) {
		if(log) {
			return given(createRequestSpecBuilder(includeAuth))
			.log().all()
			.when().log().all()
			.get(serviceUrl);
		}
		else {
			return given(createRequestSpecBuilder(includeAuth))
			.when()
			.get(serviceUrl);
		}
		
	}
	
	public Response get(String serviceUrl, Map<String, String> headerMap, boolean includeAuth, boolean log) {
		if(log) {
			return given(createRequestSpecBuilder(headerMap, includeAuth))
			.log().all()
			.when()
			.get( serviceUrl);
		}
		else {
			return given(createRequestSpecBuilder(headerMap, includeAuth))
			.when()
			.get(serviceUrl);
		}
		
	}
	
	public Response get(String serviceUrl, Map<String, Object> queryParams, Map<String, String> headerMap,boolean includeAuth, boolean log) {
		if(log) {
			return given(createRequestSpecBuilder(headerMap, queryParams,includeAuth))
			.log().all()
			.when()
			.get(serviceUrl);
		}
		else {
			return given(createRequestSpecBuilder(headerMap, queryParams, includeAuth))
			.when()
			.get(serviceUrl);
		}
	}
	
	
	//POST
	
	
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if(log) {
			return given(createRequestSpecBuilder(requestBody, contentType, includeAuth))
			.log().all()
			.when()
			.post( serviceUrl);
		}
		else {
			return given(createRequestSpecBuilder(requestBody, contentType, includeAuth))
			.log().all()
			.when()
			.post(serviceUrl);
		}
	
}
	
		
		public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headerMap, boolean includeAuth, boolean log) {
			if(log) {
				return given(createRequestSpecBuilder(requestBody, contentType, headerMap, includeAuth))
				.log().all()
				.when()
				.post( serviceUrl);
			}
			else {
				return given(createRequestSpecBuilder(requestBody, contentType, headerMap, includeAuth))
				.log().all()
				.when()
				.post(serviceUrl);
			}
		
	}
	
	
	//PUT
		
		
		public Response put(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
			if(log) {
				return given(createRequestSpecBuilder(requestBody, contentType, includeAuth))
				.log().all()
				.when()
				.put( serviceUrl);
			}
			else {
				return given(createRequestSpecBuilder(requestBody, contentType, includeAuth))
				.log().all()
				.when()
				.put(serviceUrl);
			}
		
	}
		
		
		
		public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headerMap, boolean includeAuth, boolean log) {
			if(log) {
				return given(createRequestSpecBuilder(requestBody, contentType, headerMap, includeAuth))
				.log().all()
				.when()
				.put( serviceUrl);
			}
			else {
				return given(createRequestSpecBuilder(requestBody, contentType, headerMap, includeAuth))
				.log().all()
				.when()
				.put(serviceUrl);
			}
		
	}
		
		
	//PATCH
		
		
		public Response patch(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
			if(log) {
				return given(createRequestSpecBuilder(requestBody, contentType, includeAuth))
				.log().all()
				.when()
				.patch( serviceUrl);
			}
			else {
				return given(createRequestSpecBuilder(requestBody, contentType, includeAuth))
				.log().all()
				.when()
				.patch(serviceUrl);
			}
		
	}
		
		
		
		public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headerMap, boolean includeAuth, boolean log) {
			if(log) {
				return given(createRequestSpecBuilder(requestBody, contentType, headerMap, includeAuth))
				.log().all()
				.when()
				.put( serviceUrl);
			}
			else {
				return given(createRequestSpecBuilder(requestBody, contentType, headerMap, includeAuth))
				.log().all()
				.when()
				.put(serviceUrl);
			}
		
	}
		
		
		//DELETE
		
		public Response delete(String serviceUrl, boolean includeAuth, boolean log) {
			if(log) {
				return given(createRequestSpecBuilder(includeAuth))
				.log().all()
				.when()
				.delete(serviceUrl);
			}
			else {
				return given(createRequestSpecBuilder(includeAuth))
				.log().all()
				.when()
				.delete(serviceUrl);
			}
		
	}
		
	
	//generate token for AmedusFlight(OAuth)	
		
		public String getAccessToken(String serviceUrl, String grantType, String clientId, String clientSecreat) {
	//		baseURI = "https://test.api.amadeus.com";
			
			 String acessToken = given()
					 .log().all()
				.baseUri(baseURI)
	//			.header("Content-Type", "application/x-www-form-urlencoded")
				.contentType(ContentType.URLENC)
				.formParam("grant_type", grantType)
				.formParam("client_id", clientId)
				.formParam("client_secret", clientSecreat)
				.log().all()
			.when()
				.post(serviceUrl)
			.then()
				.log().all()
				.statusCode(200)
				.extract()
				.path("access_token");
			
			System.out.println("acessToken: "+acessToken);
			return acessToken;
			
		}
		
}
