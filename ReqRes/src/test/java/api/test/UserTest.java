package api.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	static int userId;
	
	@Test(priority=1)
	public void testCreateUser() {
		User user = new User("John Doe", "Software Engineer");
		Response response = UserEndpoints.createUser(user);
		
		System.out.println("POST Response: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 201);
		//Extract user ID from response
		userId = response.jsonPath().getInt("id");
	}
	
	@Test(priority =2)
	public void testGetUser() {
		Response response = UserEndpoints.getUser(2);
		System.out.println("GET Response: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3, dependsOnMethods = "testCreateUser")
	public void testUpdateUser() {
		User user = new User("John Doe", "Senior Engineer");
		Response response = UserEndpoints.updateUser(userId, user);
		System.out.println("PUT Response: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=4, dependsOnMethods="testCreateUser")
	public void testDeleteUser() {
		Response response = UserEndpoints.deleteUser(userId);
		Assert.assertEquals(response.getStatusCode(), 204);
	}

}
