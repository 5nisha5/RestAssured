package api.endpoints;

public class Routes {
	public static final String BASE_URL = "https://reqres.in/api";
	
	public static final String CREATE_USER = BASE_URL+"/users";
	public static final String GET_USER = BASE_URL + "/users/{id}";
	public static final String UPDATE_USER = BASE_URL +"/users/{id}";
	public static final String DELETE_USER = BASE_URL + "/users/{id}";
}
