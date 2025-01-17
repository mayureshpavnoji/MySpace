package demo.session.framework.SeleniumFrameworkTutorials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import demo.session.framework.AbstractComponentsRepo.AbstractClassesDemo;

public class CheckOutPage extends AbstractClassesDemo {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By elePresence = By.cssSelector(".ta-results");

	@FindBy(css = "[placeholder='Select Country']")
	WebElement CheckPlaceHolder;

	@FindBy(xpath = "//div[@class='payment__shipping']//button[2]")
	WebElement proceedNextPage;

	@FindBy(css = ".action__submit")
	private WebElement submitButton;

	public void selectCountry(String country) throws InterruptedException {

		Actions a = new Actions(driver);
		a.sendKeys(CheckPlaceHolder, country).build().perform();
		waitForElementToAppear(elePresence);
		proceedNextPage.click();
		Thread.sleep(3000);
	}

	public ConfirmationPage proceedCheckout() throws InterruptedException {

		submitButton.click();
		Thread.sleep(3000);
		return new ConfirmationPage(driver);

	}
}
