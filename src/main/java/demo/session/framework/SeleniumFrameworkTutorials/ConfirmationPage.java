package demo.session.framework.SeleniumFrameworkTutorials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import demo.session.framework.AbstractComponentsRepo.AbstractClassesDemo;

public class ConfirmationPage extends AbstractClassesDemo {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = ".hero-primary")
	WebElement orderSubmittion;
	
	public void orderSubmittion (String ExpectedMessage) {
		
		String textReturned = orderSubmittion.getText();
		Assert.assertTrue(textReturned.equalsIgnoreCase(ExpectedMessage));
		
	}
	
//	public void encapsulationDemo() throws InterruptedException {
//		CheckOutPage ch = new CheckOutPage (driver);
//		ch.submitButton.click (); // fields to be accessed using the public methods
//		
//	}

}
