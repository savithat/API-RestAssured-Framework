package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathValidator {
	
	public static XmlPath getXmlXpath(Response response) {
		String responseStr = response.getBody().asString();
		return new XmlPath(responseStr);
	}
	
	
	public static <T> T xmlPathValidator(Response response, String xmlPathExpression){
			XmlPath xmlPath = getXmlXpath(response);
			return xmlPath.get(xmlPathExpression);
	}
	
	public static <T> List<T>  xmlPathListValidator(Response response, String xmlPathExpression){
		XmlPath xmlPath = getXmlXpath(response);
		return xmlPath.getList(xmlPathExpression);
	}
	
	public static <T> List<Map<String, T>>  xmlPathLstOfMapValidator(Response response, String xmlPathExpression){
		XmlPath xmlPath = getXmlXpath(response);
		return xmlPath.getList(xmlPathExpression);
	}

}
