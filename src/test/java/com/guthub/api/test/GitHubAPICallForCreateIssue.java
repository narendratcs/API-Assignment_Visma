package com.guthub.api.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.github.api.utils.RestAPIUtility;

import io.restassured.path.json.JsonPath;
import pojo.objects.IssueRequest;

public class GitHubAPICallForCreateIssue {
	
	
	
	@Test
	public void testGitHubPOSTIssue() 
	{
		IssueRequest issueReq = new IssueRequest();
		issueReq.setBody("Test Body");
		issueReq.setTitle("Test Title");
		String responseBody = RestAPIUtility.postGitHubCreateIssue(issueReq);
		JsonPath jsPath = new JsonPath(responseBody);
		assertEquals(jsPath.get("title"),"Test Title");	
		int number= jsPath.getInt("number");
	
		//Validating issue By calling  GetAPI end point for issues  
		responseBody = RestAPIUtility.getIssueUsingIssueNumber(number);
		jsPath = new JsonPath(responseBody);
		assertEquals(jsPath.get("title"),"Test Title");			
		
	}
	
	@Test
	public void testGitHubPATCHIssue() 
	{
		IssueRequest issueReq = new IssueRequest();
		issueReq.setBody("Test Body");
		issueReq.setTitle("Test Title");
		String responseBody = RestAPIUtility.postGitHubCreateIssue(issueReq);
		JsonPath jsPath = new JsonPath(responseBody);
		assertEquals(jsPath.get("title"),"Test Title");	
		int number= jsPath.getInt("number");
	
		//Validating issue By calling  GetAPI end point for issues  
		responseBody = RestAPIUtility.getIssueUsingIssueNumber(number);
		jsPath = new JsonPath(responseBody);
		assertEquals(jsPath.get("title"),"Test Title");	
		
		
		issueReq = new IssueRequest();
		issueReq.setTitle("Updated Test Title");
		responseBody = RestAPIUtility.patchGitHubCreateIssue(issueReq,number);
		jsPath = new JsonPath(responseBody);
		assertEquals(jsPath.get("title"),"Updated Test Title");	
		number= jsPath.getInt("number");		
				
		
	}
	
	@Test
	public void testGitHubPATCHIssueWithHardCodedNumber() 
	{
		IssueRequest issueReq = new IssueRequest();				
		issueReq.setTitle("Updated Test Title");
		String responseBody = RestAPIUtility.patchGitHubCreateIssue(issueReq,3);
		JsonPath jsPath = new JsonPath(responseBody);
		assertEquals(jsPath.get("title"),"Updated Test Title");				
		
	}
	
	@Test
	public void testGitHubPUTIssueWithHardCodedNumber() 
	{
		IssueRequest issueReq = new IssueRequest();				
		issueReq.setTitle("Updated Test Title");
		String responseBody = RestAPIUtility.patchGitHubCreateIssue(issueReq,3);
		JsonPath jsPath = new JsonPath(responseBody);
		assertEquals(jsPath.get("title"),"Updated Test Title");				
		
	}
	

}
