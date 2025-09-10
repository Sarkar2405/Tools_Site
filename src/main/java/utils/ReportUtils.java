package utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtils {
	//ExtentSparkReporter- this contains methods which define the look and feel of the report.
	public static ExtentSparkReporter spark ;
	
	//ExtentReports- the main class used to create and manage the test report.
	public static ExtentReports extent;
	
	public static ExtentTest log;
	
	//to initialize and configure the Report
	public static ExtentReports initReport() throws IOException {
		
		//gets the current date and time using LocalDateTime.now().
		String time = String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy hh.mm.ss a")));
		
		//Creates a ExtentSparkReporter object named spark and 
		//generates an HTML report at the path target/AutomationReport_" + time + ".html
		spark = new ExtentSparkReporter("target/AutomationReport_" + time + ".html");
		
		//Creates a ExtentReports object named extent
		extent = new ExtentReports();
		
		//to understand what type of report should be genarated we have to call attachReporter method of ExtentReports class and 
		//passing ExtentReporter objects as arguments, this tells ExtentReports to use the configuration and output format 
		//defined in spark
		extent.attachReporter(spark); 
		
		//deciding the look of the report.
		spark.config().setDocumentTitle(BaseUtils.getConfigValue("documentTitle"));
		spark.config().setReportName(BaseUtils.getConfigValue("reportTitle"));
		spark.config().setTheme(Theme.STANDARD);
		
		//return the fully configured report
		return extent;
	}
	
	//to to create an entry for each test method within the generated report
	public static void createTest(String methodname) {
		 log= extent.createTest(methodname);
	}
	
	/*
	 * public static ExtentTest getLog() { 
	 * return log;
	 *  }
	 */
	
	//to generate the report
	public static void generateReport() {
		extent.flush();
	}

}
