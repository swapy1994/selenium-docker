package com.automation.selenium_docker.basepackege;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends AbstractTest{
	private static ExtentReports extent;
	private static ExtentSparkReporter spark;
	
	
	public static ExtentReports configureReport() {
//		spark = new ExtentSparkReporter("./reports/extent-report.html");
		String fileName = getReportName();
		String directory = System.getProperty("user.dir")+"/test-output/extent-reports/";
		new File(directory).mkdirs();
		String filePath = directory+fileName;
		
		spark = new ExtentSparkReporter(filePath);
		extent = new ExtentReports();
		
		spark.config().setEncoding("UTF-8");
		spark.config().setDocumentTitle("Automation reports");
		spark.config().setReportName("Automation Test Results");
		spark.config().setTheme(Theme.DARK);

		
		extent.setSystemInfo("Operating System", "<h5><b><font color=green>Windows</font></b></h5>");
		extent.setSystemInfo("Browser", "<h5><b><font color=green>Chrome</font></b></h5>");
		extent.attachReporter(spark);	
		
		return extent;
		
	}
	
	private static String getReportName() {
		Date date = new Date();
		String fileName = "AutomationReport-"+date
		.toString()
		.replace(":", "_")
		.replace(" ", "_")+".html";
		
		return fileName;
				
	}
	
}
