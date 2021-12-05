package com.guthub.api.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

import com.github.api.utils.RestAPIUtility;

import io.restassured.path.json.JsonPath;

public class GithubAPICallForUserDetailsTest {
	
	@Test
	public void testGitHubrDetails()
	{
		String responseBody = RestAPIUtility.getGitHubUserDetails();
		JsonPath jsPath = new JsonPath(responseBody);
		
		int id = jsPath.get("id");
		String logiName = jsPath.get("login");
		String strAvatarURL = jsPath.get("avatar_url");
		String strURL = jsPath.get("url");
		String strtypeL = jsPath.get("type");
		
		
		assertEquals(id, 48873486);
		assertEquals(logiName, "narendratcs");
		assertEquals(strAvatarURL, "https://avatars.githubusercontent.com/u/48873486?v=4");
		assertEquals(strURL, "https://api.github.com/users/narendratcs");
		assertNotEquals(strtypeL, "users");		
	}
	
	

}
