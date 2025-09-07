package tools.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

public class ContactPage {
	
	WebDriver driver;
	Faker faker;
	
	public ContactPage(WebDriver driver, Faker faker) {
		this.driver= driver;
		this.faker= faker;
	}
	 By contact_ele= By.linkText("Contakt");
	 By first_name_ele= By.id("first_name");
	 By last_name_ele= By.id("last_name");
	 By email_ele= By.id("email");
	 By send_btn_ele= By.xpath("//input[@data-test='contact-submit']");

	public void goToContactPage() {
		driver.findElement(contact_ele).click();
	}
	
	public void enterFisrtName() {
		String first_name= faker.name().firstName();		
		driver.findElement(first_name_ele).sendKeys(first_name);
	}
	
	public void enterLastName() {
		String last_name= faker.name().lastName();	
		driver.findElement(last_name_ele).sendKeys(last_name);
	}
	
	public void enterEmail() {
		String email= faker.internet().emailAddress();
		driver.findElement(email_ele).sendKeys(email);
	}
	
	public void selectSubject() {
		WebElement subject= driver.findElement(By.id("subject"));
		Select sub= new Select(subject);
		sub.selectByValue("payments");
	}
	
	public void enterMessage() {
		String msg= faker.lorem().sentence();
		driver.findElement(By.id("message")).sendKeys(msg);
	}
	
	public void send() {
		driver.findElement(send_btn_ele).click();
	}
}
