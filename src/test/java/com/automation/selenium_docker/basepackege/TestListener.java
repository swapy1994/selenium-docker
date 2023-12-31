package com.automation.selenium_docker.basepackege;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.selenium_docker.utilities.Config;
import com.automation.selenium_docker.utilities.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.configureReport();
	// Here we have made the ExtentTest thread safe as below
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static TakesScreenshot driver;

	@Override
	public void onTestStart(ITestResult result) {
		// Here we are creating the Test name to display in report
		// By fetching name of currently running test class and current method name
		// by using object of ITestResult i.e. result
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
		extentTest.set(test);

		// check if grid is enabled

		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String logText = "<h4><b>Test Method :: " + result.getMethod().getMethodName() + " Successful.</b></h4>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		ITestListener.super.onTestSuccess(result);
	}

	@Override

	public void onTestFailure(ITestResult result) {
		// Here we are fetching the error message from console and converting it to
		// string
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = result.getThrowable().getLocalizedMessage();
		String exceptionDetails = Arrays.toString(result.getThrowable().getStackTrace());
		
		extentTest.get()
				.fail("<details><summary><b><font color=red>Exception Occured ::"+exceptionMessage
						+" click for details :</font></b>"
						+ "</summary>" + exceptionDetails.replaceAll(",", "<br>")
						+ "</details> \n");

		// ------Here we are fetching the driver from abstract class to give it to below  method
		// ------This is alternate way to fetch the driver==>
		//  WebDriver driver = ((AbstractTest)result.getInstance()).driver;
		
		driver = (TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
		
		String ss = takeScreenshot(driver, methodName);
		try {
			if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
				extentTest.get().fail("<b><font color=red>" + "Screnshot of failure moment" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(ss).build());
			}else {
				extentTest.get().fail("<b><font color=red>" + "Screnshot of failure moment" + "</font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(ss).build());
			}
		} catch (Exception e) {
			extentTest.get().fail("Test Failed... Unabled to Attach Screenshot File.");
			e.printStackTrace();
		}
		String logText = "<h5><b>Test Method :: " + methodName + "</b></h5>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method :: <h3>" + result.getMethod().getMethodName() + "</h3> Skipped.</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onStart(ITestContext context) {

		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
		ITestListener.super.onFinish(context);
	}

	private String takeScreenshot(Object driver, String methodName) {
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir") + "/test-output/extent-reports/screenshots/";
		new File(directory).mkdirs();
		String filePath = directory + fileName;
		try {
			if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
				String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				// String htmlImageFormat = "<img src='data:image/png;base64,%s' />";
				// String htmlImage = String.format(htmlImageFormat, screenshot);
				filePath = screenshot;
			} else {
				File screenCapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenCapture, new File(filePath));
				System.out.println("###########################");
				System.out.println("Screenshot stored at :" + filePath);
				System.out.println("###########################");
			}
		} catch (Exception e) {
			System.out.println("Error while taking the screenshot:" + e);
		}
		return filePath;
	}

	private static String getScreenshotName(String methodName) {
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		String ssName = methodName + "-" + dateTime.toString().replace(":", "_").replace(" ", "_") + ".png";

		return ssName;

	}

}
