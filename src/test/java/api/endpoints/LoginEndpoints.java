package api.endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginEndpoints {

	// POST request for Login
	public static Response checkLogin(Login loginData) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(loginData)

				.when().post("https://reqres.in/api/login");

		return response;

	}

}
