package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private ExtentSparkReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest test;

	// constructor
	public ExtentManager() {

		// to append date and time with milli seconds at the end of the report
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());

		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\extentReports\\extentReport_" + timestamp + ".html");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("API testing");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

	}

	// methods
	public void extentCreateTest(String extenttestName) {
		test = extent.createTest(extenttestName);

	}

	public void extentTestLog(Status st, String details) {
		test.log(st, details);
	}

	public synchronized void extentFlush() {
		extent.flush();
	}

}
