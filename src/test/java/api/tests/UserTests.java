package api.tests;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import api.endpoints.User;
import api.endpoints.UserEndpoints;
import api.utilities.ExcelUtils;
import api.utilities.ExtentManager;
import dummyData.MyDummyData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTests {

	User userData = new User();
	Response res = null;
	ExtentManager extentManager = new ExtentManager(); // To access extent report features from utility class
														// ExtentManager
	Map<String, String> testData;

	@Given("user wants to read test data from sheet {string} with ID {string}")
	public void readDatafromExcel(String sheetName, String testDataID) throws IOException {
		testData = ExcelUtils.getTestData(sheetName, testDataID);

	}

	
	@Given("user wants to parse json data")
	public void userwantstoparsejsondata() {
		String captureResponse=MyDummyData.testComplexJSONResponseString();
		System.out.println(captureResponse);
		JsonPath jp=new JsonPath(captureResponse);
		int id=jp.getInt("[0].id");
		System.out.println("ID is: "+id);
		String collection=jp.getString("[0].collection");
		System.out.println("Collection of : "+collection);
		
		Boolean fav=jp.getBoolean("[0].\'favorite actor\'");
		System.out.println("boolean value is : "+fav);
		
		//to get the list array
		System.out.println("title is:: "+jp.getString("[0].List[0].title"));
		System.out.println("VAlue is:: "+jp.getString("[0].List[0].'cast roles'[2]"));
		
		
	}
	
	
	
	
	@Given("user wants to read data")
	public void user_wants_to_read_data(DataTable dataTable) {
	    String myName=dataTable.cell(1, 0);
	    System.out.println("Name is : "+myName);
	    
//	    | name       | age | gender | occupation | salary |
//	      | Ethan Hunt |  28 | Male   | MI Agent   |  80000 |
	    
	    String age=dataTable.cell(1, 1);
	    System.out.println("Age : "+age);
	    
	    
	    
	    
	}
	
	
	
	@Given("user read {string} and {string} from sheet")
	public void userretrivessomedata(String Username, String Jobname) {
		System.out.println(testData.get(Username));
		System.out.println(testData.get(Jobname));

	}

	@When("user creates a post request for new user creation")
	public void userCreatesPostRequest() {
		extentManager.extentCreateTest("Post request to create user ::: ");
		userData.setName(testData.get("Username").toString());
		userData.setJob(testData.get("Jobname").toString());
		userData.setId(Integer.valueOf(testData.get("UserID")));

		res = UserEndpoints.createUser(userData);
		res.then().log().all();
	}

	@Given("^user creates a post request with name \"([^\"]*)\" job \"([^\"]*)\" and id \"([^\"]*)\"$")
	public void user_creates_a_post_requestwithnamejobandid(String name, String job, int id) {
		extentManager.extentCreateTest("Post request to create user ::: ");
		userData.setName(name);
		userData.setJob(job);
		userData.setId(id);

		res = UserEndpoints.createUser(userData);
		res.then().log().all();

	}

	@Given("user creates a patch request with name {string} job {string}")
	public void user_creates_a_patch_requestwithnamejobandid(String name, String job) {
		extentManager.extentCreateTest("Patch request to update existing user ::: ");
		userData.setName(name);
		userData.setJob(job);
//		userData.setId(id);

		res = UserEndpoints.patchUser(userData);
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
