package tools.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;

public class BaseTest {

	WebDriver driver;
	Faker faker;

	@BeforeMethod
	public void initializeApp() {
		faker = new Faker();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://with-bugs.practicesoftwaretesting.com/");
	}

}
