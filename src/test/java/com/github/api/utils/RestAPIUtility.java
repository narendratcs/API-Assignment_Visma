package com.github.api.utils;



import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import pojo.objects.IssueRequest;
import pojo.objects.Repository;


public class RestAPIUtility {
	
	static String resoucesURL = "https://api.github.com";
	static String users = "/users/";
	static String gitUserName= "narendratcs";
	static String issueURL =  "/repos/{username}/testing-api/issues";	
	static String repoURL = "/user/repos";
	static String oAuthToken = "Bearer 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";
	
	public static String  postGitHubCreateRepo(Repository repository)
	{		
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());		
		String responseStr =  RestAssured.
				given()	
				.baseUri(resoucesURL)		
				.header("Authorization",oAuthToken)			
				.body(repository)
				.contentType(ContentType.JSON)
				.post(repoURL).then()
				.statusCode(201).extract().asString();			
		return responseStr;		
		
	}
	
	public static void  deleteGitHubCreateRepo(String repositoryName)
	{		 
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());		
		 RestAssured.
				given()	
				.baseUri(resoucesURL)		
				.header("Authorization",oAuthToken)		
				.pathParam("username", gitUserName)
				.contentType(ContentType.JSON)
				.delete("repos/{username}/"+repositoryName).then()
				.statusCode(204);			
		
	}
	
	
	public static String  getGitHubUserDetails()
	{		
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());		
		String responseStr =  RestAssured.
				given()
				.contentType(ContentType.JSON)
				.pathParam("username", gitUserName)
				.header("Authorization",oAuthToken)				
				.get(resoucesURL+users+"{username}")
				.then().statusCode(200).extract().asString();			
		return responseStr;				
		
	}
	
	public static String  postGitHubCreateIssue(IssueRequest issueReqeust)
	{		
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());		
		String responseStr =  RestAssured.
				given()				
				.pathParam("username", gitUserName)
				.header("Authorization",oAuthToken)	
				.body(issueReqeust)
				.contentType(ContentType.JSON)
				.post(resoucesURL+issueURL).then()
				.statusCode(201).extract().asString();			
		return responseStr;		
		
	}
	public static String  getIssueUsingIssueNumber(int issueNumber)
	{
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());		
		String responseStr =  RestAssured.
				given()				
				.pathParam("username", gitUserName)
				.header("Authorization",oAuthToken)
				.pathParam("issue_number", issueNumber)				
				.contentType(ContentType.JSON)
				.get(resoucesURL+issueURL+"/"+"{issue_number}").then()
				.statusCode(200).extract().asString();			
		return responseStr;		
		
	}
	
	public static String  patchGitHubCreateIssue(IssueRequest issueReqeust,int number)
	{		
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());		
		String responseStr =  RestAssured.
				given()				
				.pathParam("username", gitUserName)
				.header("Authorization",oAuthToken)	
				.pathParam("issue_number", number)	
				.body(issueReqeust)
				.contentType(ContentType.JSON)
				.patch(resoucesURL+issueURL+"/"+"{issue_number}").then()
				.statusCode(200).extract().asString();			
		return responseStr;		
		
	}
	


}
