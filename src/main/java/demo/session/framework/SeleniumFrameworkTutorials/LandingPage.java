package demo.session.framework.SeleniumFrameworkTutorials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.session.framework.AbstractComponentsRepo.AbstractClassesDemo;

public class LandingPage extends AbstractClassesDemo {

	WebDriver driver;
	ProductCatalogue catalogue;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userDetails = driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement UserName;

	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorObj;

	public ProductCatalogue loginApplication(String email, String password) {

		UserName.sendKeys(email);
		Password.sendKeys(password);
	    submit.click();
		return catalogue;

	}

	public void goTo(String url) {

		// driver.get("https://rahulshettyacademy.com/client");
		driver.get(url);

	}
	
	public String getErrorMessage () {
		
		
		waitForWebElementToAppear (errorObj);
		return errorObj.getText();

		
	}
	

}
