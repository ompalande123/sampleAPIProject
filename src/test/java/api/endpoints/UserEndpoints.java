package api.endpoints;

//static packages import
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	/*
	 * Now, we will implement the methods for sending these requests to the user
	 * service like the CRUD method implementation, we will implement these methods
	 * in UserEndPoints.java class
	 */

	
	// need to send userData as body of the request
	public static Response createUser(User userData) {
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userData)

				.when()
				.post(Routes.post_url);

		return response;

	}
	
	// to get data we need to provide userName
	public static Response getUser(int id)
	{
		Response response=
				given()
				.pathParam("id", id)
				
				.when()
				.get(Routes.get_url);
		
		return response;
				
	}
	
	public static Response getAllUsersfromPageNumber(int id)
	{
		Response response=
				given()
				.pathParam("id", id)
				
				.when()
				.get(Routes.get_all_users_from_page_number);
		
		return response;
				
	}
	

}
