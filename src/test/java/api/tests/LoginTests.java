package api.tests;

import org.json.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import api.endpoints.Login;
import api.endpoints.LoginEndpoints;
import api.endpoints.User;
import api.endpoints.UserEndpoints;
import api.utilities.ExtentManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class LoginTests {
	
	Login loginData=new Login();
	Response res = null;
	ExtentManager extentManager = new ExtentManager(); // To access extent report features from utility class
														// ExtentManager
	
	
	
//	user sends a post request with email "eve.holt@reqres.in" and password "cityslicka"
//	@Given("^user creates a post request with name \"([^\"]*)\" job \"([^\"]*)\" and id \"([^\"]*)\"$")
	@Given("^user sends a post request with email \"([^\\\"]*)\" and password \"([^\\\"]*)\"$")
	public void user_sends_post_request_to_validate_login(String email, String pass) {
		extentManager.extentCreateTest("Post request to validate login ::: ");
		loginData.setEmail(email);
		loginData.setPswd(pass);
		System.out.println("Pass value = "+pass);
		System.out.println("email value = "+email);
		
		res=LoginEndpoints.checkLogin(loginData);
		res.then().log().all();
		System.out.println("captured response = "+res.then().log().all());

	}
	
	
	 @Then("user captures the token value and status code")
	 public void userCapturestheTokenandStatusCode()
	 {
		 
		 JSONObject jsonObject = new JSONObject(res.asString());
			System.out.println("get Response is - " + jsonObject.toString());
			String tokenValue = jsonObject.get("token").toString();
			System.out.println("Token value is = "+tokenValue);
			extentManager.extentTestLog(Status.PASS, "Token value : " + tokenValue);
			
			Assert.assertEquals(res.getStatusCode(), 200);
			extentManager.extentTestLog(Status.PASS, "Response body : <br>" + res.body().asString());
			extentManager.extentTestLog(Status.PASS,
					"<b>" + String.valueOf(res.statusCode() + String.valueOf(res.statusLine()) + "<b>"));
	 }
	

}
