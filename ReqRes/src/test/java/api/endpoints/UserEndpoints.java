package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class UserEndpoints {
	public static Response createUser(User user) {
		return given()
				.contentType(ContentType.JSON)
				.body(user)
			.when()
				.post(Routes.CREATE_USER);
	}
	
	public static Response getUser(int userId) {
		return given()
				.pathParam("id", userId)
			.when()
				.get(Routes.GET_USER);
	}
	
	public static Response updateUser(int userId, User user) {
		return given()
				.contentType(ContentType.JSON)
				.pathParam("id", userId)
				.body(user)
			.when()
				.put(Routes.UPDATE_USER);
	}
	
	public static Response deleteUser(int userId) {
		return given()
				.pathParam("id", userId)
			.when()
				.delete(Routes.DELETE_USER);
	}
}
