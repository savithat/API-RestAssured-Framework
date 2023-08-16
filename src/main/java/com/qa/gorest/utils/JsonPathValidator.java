package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkException.APIFrameworkException;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	public static String responseJsonToString(Response response) {
		return response.getBody().asString();
	}
	
	
	//I am using static for this methods(Nav not used static created a obj when he is calling this method)
	public  static <T> T  read(Response response, String jasonPath) {
		String strResponse = responseJsonToString(response);
		//created common method for the below line
//		String strResponse = response.getBody().asString();
		try {
		return JsonPath.read(strResponse, jasonPath);
		}
		catch(PathNotFoundException e){
			e.fillInStackTrace();
			throw new APIFrameworkException(jasonPath + " is not found");
		}
		
	}
	
	
	public static <T> List<T> readList(Response response, String jasonPath) {
	//	String strResponse = response.getBody().asString();
		String strResponse = responseJsonToString(response);
		try {
		return JsonPath.read(strResponse, jasonPath);
		}
		catch(PathNotFoundException e){
			e.fillInStackTrace();
			throw new APIFrameworkException(jasonPath + " is not found");
		}
		
	}
	
	public static <T> List<Map<String, T>> readListOfMap(Response response, String jasonPath) {
	//	String strResponse = response.getBody().asString();
		String strResponse = responseJsonToString(response);
		try {
		return JsonPath.read(strResponse, jasonPath);
		}
		catch(PathNotFoundException e){
			e.fillInStackTrace();
			throw new APIFrameworkException(jasonPath + " is not found");
		}
		
	}
	
}
