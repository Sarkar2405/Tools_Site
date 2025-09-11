package tools.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.javafaker.Faker;

import utils.BaseUtils;
import utils.ReportUtils;

public class BaseTest {

	WebDriver driver;
	Faker faker;

	@BeforeSuite
	public void init() throws IOException {
		ReportUtils.initReport();
	}

	@BeforeMethod
	public void initializeApp(Method testmethod) throws IOException {

		ReportUtils.createTest(testmethod.getName());

		faker = new Faker();

		String mybrowser = BaseUtils.getConfigValue("browser").toLowerCase();
		switch (mybrowser) {
		case "edge":
			driver = new EdgeDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		}

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.valueOf(BaseUtils.getConfigValue("implicitwait"))));
		driver.manage().window().maximize();
		driver.get(BaseUtils.getConfigValue("url"));
		ReportUtils.log.info(BaseUtils.getConfigValue("url") + " site is launched successfully");
	}
	
	@AfterMethod
	// ITestResult is an interface provided by testng which keeps all the
	// information(test status, class, method)related to test
	public void endTest(ITestResult result) throws IOException {
	
		//getThrowable--> get us the log
		if (result.getStatus() == ITestResult.FAILURE) {
			ReportUtils.getLog().fail(result.getThrowable(), 
					MediaEntityBuilder.createScreenCaptureFromPath(BaseUtils.getScreenshotPath(driver, 
							result.getInstanceName().getClass().getSimpleName() + "."
									+ result.getMethod().getMethodName())).build());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {		
			ReportUtils.getLog().pass("Test pass");
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			ReportUtils.getLog().skip("Test skipped");
		}
	}

	@AfterSuite
	public void createReport() {
		ReportUtils.generateReport();
	}

}
