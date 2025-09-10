package tools.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

import utils.ReportUtils;

public class ContactPage {

	WebDriver driver;
	Faker faker = new Faker();

	public  String first_name;
	public static String last_name;
	public static String email;
	public static String msg;
	public static String contactpageheadertext;
	public String subjectselected;

	public ContactPage(WebDriver driver, Faker faker) {
		this.driver = driver;
		// this.faker= faker;
	}

	By contact_ele = By.linkText("Contact");
	By contact_header_ele = By.tagName("h3");
	By first_name_ele = By.id("first_name");
	By last_name_ele = By.id("last_name");
	By email_ele = By.id("email");
	By send_btn_ele = By.xpath("//input[@data-test='contact-submit']");

	public void checkHeader() {
		contactpageheadertext = driver.findElement(contact_header_ele).getText();
	}

	public void enterFisrtName() {
		first_name = faker.name().firstName();
		driver.findElement(first_name_ele).sendKeys(first_name);
	}

	public void enterLastName() {

		last_name = faker.name().lastName();
		driver.findElement(last_name_ele).sendKeys(last_name);
	}

	public void enterEmail() {
		email = faker.internet().emailAddress();
		driver.findElement(email_ele).sendKeys(email);
	}

	public void selectSubject() {
		WebElement subject = driver.findElement(By.id("subject"));
		Select sub = new Select(subject);
		 sub.selectByValue("payments");
		 subjectselected= sub.getFirstSelectedOption().getText();
	}

	public void enterMessage() {
		msg = faker.lorem().sentence();
		driver.findElement(By.id("message")).sendKeys(msg);
	}

	public void send() {
		driver.findElement(send_btn_ele).click();
	}
}
