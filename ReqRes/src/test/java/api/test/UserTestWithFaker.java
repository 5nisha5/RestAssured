package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestWithFaker {

	static int userId;
	static Faker faker;
	static String name;
	static String job;
	public Logger logger = LogManager.getLogger(this.getClass());
	
	@BeforeClass
	public void setupTestData(){
		
		faker = new Faker();
		name = faker.name().fullName();
		job = faker.job().title();
		
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		logger.info("************************creating user*******************************8");
		User user = new User(name, job);
		Response response = UserEndpoints.createUser(user);
		
		System.out.println("POST Response: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 201);
		//Extract user ID from response
		userId = response.jsonPath().getInt("id");
		logger.info("************************created user*******************************8");
	}
	
	@Test(priority =2)
	public void testGetUser() {
		logger.info("************************getting user*******************************8");
		Response response = UserEndpoints.getUser(2);
		System.out.println("GET Response: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************************got user*******************************8");
	}
	
	@Test(priority=3, dependsOnMethods = "testCreateUser")
	public void testUpdateUser() {
		logger.info("************************update user*******************************8");
		User user = new User("John Doe", "Senior Engineer");
		Response response = UserEndpoints.updateUser(userId, user);
		System.out.println("PUT Response: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************************user updated*******************************8");
	}
	
	@Test(priority=4, dependsOnMethods="testCreateUser")
	public void testDeleteUser() {
		logger.info("************************deleting user*******************************8");
		Response response = UserEndpoints.deleteUser(userId);
		Assert.assertEquals(response.getStatusCode(), 204);
		logger.info("************************user deleted*******************************8");
	}
}
