package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDUserTest {
	
	static int userId;
	static String name;
	static String job;

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String name, String job) {
		User user = new User(name, job);
		Response response = UserEndpoints.createUser(user);
		
		System.out.println("POST Response: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 201);
		//Extract user ID from response
		userId = response.jsonPath().getInt("id");
	}
}
