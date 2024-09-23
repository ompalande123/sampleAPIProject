package api.tests;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import api.endpoints.User;
import api.endpoints.UserEndpoints;
import api.utilities.ExcelUtils;
import api.utilities.ExtentManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserTests {

	User userData=new User();
	Response res=null;
	ExtentManager extentManager=new ExtentManager(); //To access extent report features
	
	
	
	//scenario outline step definition
	@When("^user creates a post request with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_creates_a_post_requestwithnamejobandid(String name, String job, int id)
	{
		extentManager.extentCreateTest("Post request to create user : ");
		userData.setName(name);
		userData.setJob(job);
		userData.setId(id);
		
		Response response=UserEndpoints.createUser(userData);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		
		extentManager.extentTestLog(Status.PASS, response.body().asString());
		extentManager.extentTestLog(Status.PASS, "<b>"+String.valueOf(response.statusCode()+String.valueOf(response.statusLine())+"<b>"));
		extentManager.extentFlush();
		
	}
	
	
	
	
	
	
	@And("user wants to get data for username {int}")
	public void userwantstoreaddata(int id)
	{
		extentManager.extentCreateTest("Get Request::");
		Response response=UserEndpoints.getUser(id);
		
		//capture response for validation
		JSONObject jsonObject=new JSONObject(response.asString());
		System.out.println("get Response is - "+jsonObject.toString());
		String expectedFname=jsonObject.getJSONObject("data").get("first_name").toString();
		String actualFname="Janet1";
		if(actualFname.equalsIgnoreCase(expectedFname))
		{
			extentManager.extentTestLog(Status.PASS, "first_name matched : <br> <p> Actual value = "+actualFname + "<br> Expected value = "
					+expectedFname+"</p>");
		}
		else
		{
			extentManager.extentTestLog(Status.FAIL, "first_name not matched : <br> <p> Actual value = "+actualFname + "<br> Expected value = "
					+expectedFname+"</p>");
		}
		
		extentManager.extentTestLog(Status.PASS, "Response body : <br>"+response.body().asString());
		extentManager.extentTestLog(Status.PASS,"<b>"+String.valueOf(response.statusCode()+" "+String.valueOf(response.statusLine())+"<b>"));
		extentManager.extentFlush();
		
	}
	
	
	@And("user sends get request to retrieve all users from page number {int}")
	public void getAllUsersAndValidate(int pageNumber)
	{
		res=UserEndpoints.getAllUsersfromPageNumber(pageNumber);
		
		
	}
	
	
	@Then("user validate the response")
	public void validateResponse()
	{
		JSONObject jsonObject=new JSONObject(res.asString());
		System.out.println("get all users Response is - "+jsonObject.toString());
		
		extentManager.extentCreateTest("Get Request:: Validate response");
		String lastName="Funke";
		for(int i=0;i<jsonObject.getJSONArray("data").length();i++)
		{
			if(jsonObject.getJSONArray("data").getJSONObject(i).get("last_name").toString().equalsIgnoreCase(lastName))
			{
				extentManager.extentTestLog(Status.PASS, "last_name matched : <br> <p> Actual value = "+jsonObject.getJSONArray("data").getJSONObject(i).get("last_name").toString() + "<br> Expected value = "
						+lastName+"</p>");
				break;
			}
			
		}
		
		extentManager.extentFlush();
	}
	
	
	@Given("user wants to create an user by reading test id {string} in sheet {string}")
	public void readDatafromExcel(String sheet, String tcid) throws IOException
	{
		String path=System.getProperty("user.dir")+"\\testData\\myData.xlsx";
		ExcelUtils xl=new ExcelUtils(path);
		
		int rownum=xl.getRowCount(sheet);
		String shData[]=new String[rownum];
		for(int i=1;i<=rownum;i++)
		{
			shData[i-1]=xl.getCellData(sheet, rownum, i);
			System.out.println(shData);
		}
		
		
	}
	
	
}