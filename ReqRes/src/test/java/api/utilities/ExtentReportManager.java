package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReportor;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReportor = new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReportor.config().setDocumentTitle("RestAssuredAutomationProject");
		sparkReportor.config().setReportName("ReqRes user API");
		sparkReportor.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(sparkReportor);
		extent.setSystemInfo("Application", "PetStore users API");
		extent.setSystemInfo("UserName", System.getProperty("User.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "pavan");
	}
	
	public void onTextSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailur(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Skipped");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	

}
