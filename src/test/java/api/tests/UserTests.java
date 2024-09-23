package api.tests;

import org.json.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import api.endpoints.User;
import api.endpoints.UserEndpoints;
import api.utilities.ExtentManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserTests {

	User userData = new User();
	Response res = null;
	ExtentManager extentManager = new ExtentManager(); // To access extent report features from utility class
														// ExtentManager

	@Given("^user creates a post request with name \"([^\"]*)\" job \"([^\"]*)\" and id \"([^\"]*)\"$")
	public void user_creates_a_post_requestwithnamejobandid(String name, String job, int id) {
		extentManager.extentCreateTest("Post request to create user ::: ");
		userData.setName(name);
		userData.setJob(job);
		userData.setId(id);

		res = UserEndpoints.createUser(userData);
		res.then().log().all();

	}

	@When("user receives the successful response")
	public void user_receives_the_successful_response() {
		int statusCode = res.statusCode();

		switch (statusCode) {

		case 201:
			Assert.assertEquals(res.getStatusCode(), 201);
			extentManager.extentTestLog(Status.PASS, res.body().asString());
			extentManager.extentTestLog(Status.PASS,
					"<b>" + String.valueOf(res.statusCode() + String.valueOf(res.statusLine()) + "<b>"));
			break;

		case 200:
			Assert.assertEquals(res.getStatusCode(), 200);
			extentManager.extentTestLog(Status.PASS, "Response body : <br>" + res.body().asString());
			extentManager.extentTestLog(Status.PASS,
					"<b>" + String.valueOf(res.statusCode() + String.valueOf(res.statusLine()) + "<b>"));
			break;

		}

	}

	@And("user generating report")
	public void user_generating_report() {

		extentManager.extentFlush();
		res = null;

	}

	@And("user creates get request with path parameter value as id {int}")
	public void userwantstoreaddata(int id) {
		extentManager.extentCreateTest("Get Request::");
		res = UserEndpoints.getUser(id);

	}

	@Then("user validates the response")
	public void user_validates_the_response() {
		// capture response in JSONObject for validation
		JSONObject jsonObject = new JSONObject(res.asString());
		System.out.println("get Response is - " + jsonObject.toString());
		String actualFname = jsonObject.getJSONObject("data").get("first_name").toString();
		String expectedFname = "Janet123";
		if (actualFname.equalsIgnoreCase(expectedFname)) {
			extentManager.extentTestLog(Status.PASS, "first_name matched : <br> <p> Actual value = " + actualFname
					+ "<br> Expected value = " + expectedFname + "</p>");
		} else {
			extentManager.extentTestLog(Status.FAIL, "first_name not matched : <br> <p> Actual value = " + actualFname
					+ "<br> Expected value = " + expectedFname + "</p>");
		}
	}

	@And("user sends get request to retrieve all users from page number {int}")
	public void getAllUsersAndValidate(int pageNumber) {
		res = UserEndpoints.getAllUsersfromPageNumber(pageNumber);

	}

	@Then("user wants to validate the last name value")
	public void validateResponse() {
		JSONObject jsonObject = new JSONObject(res.asString());

		extentManager.extentCreateTest("Validate last name from response");
		String lastName = "Funke";
		for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {
			if (jsonObject.getJSONArray("data").getJSONObject(i).get("last_name").toString()
					.equalsIgnoreCase(lastName)) {
				extentManager.extentTestLog(Status.PASS,
						"last_name matched : <br> <p> Actual value = "
								+ jsonObject.getJSONArray("data").getJSONObject(i).get("last_name").toString()
								+ "<br> Expected value = " + lastName + "</p>");
				break;
			}

		}

	}

}
