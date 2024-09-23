package api.endpoints;

//static package import
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	// This class will use to store CRUD methods [Create, Read, Update, Delete]
	// Similar to [POST, GET, PUT, DELETE]

	// POST request
	public static Response createUser(User userData) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(userData)

				.when().post(Routes.post_url);

		return response;

	}

	// GET request
	public static Response getUser(int id) {
		Response response = given().pathParam("id", id)

				.when().get(Routes.get_url);

		return response;

	}

	// GET request
	public static Response getAllUsersfromPageNumber(int id) {
		Response response = given().pathParam("id", id)

				.when().get(Routes.get_all_users_from_page_number);

		return response;

	}

}
