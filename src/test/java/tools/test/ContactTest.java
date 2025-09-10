package tools.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import tools.pageobjects.ContactPage;
import tools.pageobjects.HomePage;
import utils.ReportUtils;

public class ContactTest extends BaseTest {
	@Test
	public void checkContactPageHeading() {
		
		HomePage home= new HomePage(driver, faker);
		home.goToContactPage();
		ReportUtils.log.info("User navigated to contact page successfully");	
		ContactPage contact= new ContactPage(driver, faker);
		contact.checkHeader();
		Assert.assertEquals(ContactPage.contactpageheadertext, "Contact");
		ReportUtils.log.info("Contact page header text: "+ ContactPage.contactpageheadertext);
	}
	
	
	@Test
	public void enterContactInfo() {
		
		HomePage home= new HomePage(driver, faker);
		home.goToContactPage();
		ReportUtils.log.info("User navigated to contact page successfully");
		
		ContactPage contact= new ContactPage(driver, faker);
		contact.enterFisrtName();
		ReportUtils.log.info("First name entered successfully: " + contact.first_name);
		contact.enterLastName();
		ReportUtils.log.info("Last name entered successfully: " + ContactPage.last_name);
		contact.enterEmail();
		ReportUtils.log.info("Email id entered successfully: " + ContactPage.email);
		contact.selectSubject();
		ReportUtils.log.info("Subject selected successfully:"+ contact.subjectselected);
		contact.enterMessage();
		ReportUtils.log.info("Message entered successfully: " + ContactPage.msg);
		contact.send();
		ReportUtils.log.info("Send button is clicked successfully");
	}

}
