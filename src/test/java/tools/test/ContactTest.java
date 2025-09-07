package tools.test;

import org.testng.annotations.Test;

import tools.pageobjects.ContactPage;

public class ContactTest extends BaseTest {
	
	@Test
	public void enterContactInfo() {
		
		ContactPage contact= new ContactPage(driver, faker);
		
		contact.goToContactPage();
		contact.enterFisrtName();
		contact.enterLastName();
		contact.enterEmail();
		contact.selectSubject();
		contact.enterMessage();
		contact.send();
	}

}
