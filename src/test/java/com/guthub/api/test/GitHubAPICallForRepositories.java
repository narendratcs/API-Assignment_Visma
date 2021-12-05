package com.guthub.api.test;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.testng.annotations.Test;

import com.github.api.utils.RestAPIUtility;

import io.restassured.path.json.JsonPath;
import pojo.objects.Repository;

public class GitHubAPICallForRepositories {
	
	@Test
	public void CreateRepo() {
		Random random = new Random();
		int value = random.nextInt(100000);
		String strRepoName = "test-github-repo-create-functionality-"+ value;		
		Repository repo = new Repository();
		repo.setName(strRepoName);		
		String responseBody =	RestAPIUtility.postGitHubCreateRepo(repo);		
		JsonPath jsPath = new JsonPath(responseBody);
		String resRepoName = jsPath.get("name");
		assertEquals(resRepoName, strRepoName);	
	}
	
	
	@Test
	public void CreateAndDeleteRepo() {
		String strRepoName = "test-github-repo-delete-functionality";
		Repository repo = new Repository();
		repo.setName(strRepoName);		
		String responseBody =	RestAPIUtility.postGitHubCreateRepo(repo);		
		JsonPath jsPath = new JsonPath(responseBody);
		String resRepoName = jsPath.get("name");
		assertEquals(resRepoName, strRepoName);		
		RestAPIUtility.deleteGitHubCreateRepo(strRepoName);	
		
		
	}

}
