package demo.session.framework.AbstractComponentsRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.session.framework.SeleniumFrameworkTutorials.CartPage;
import demo.session.framework.SeleniumFrameworkTutorials.OrdersPage;

public class AbstractClassesDemo {
	
	WebDriver driver;
	CartPage cart;

	
	
	public AbstractClassesDemo(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	

	@FindBy(css = "[routerlink*='cart']")
	WebElement CartLink;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement CartHeaders;

	public void waitForElementToAppear (By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	

	public void waitForWebElementToAppear (WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear (WebElement element) throws InterruptedException {
		
		Thread.sleep (1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage () {
		
		CartLink.click();
		return cart;
	}
	
	public OrdersPage goToOrdersPage () {
		
		CartHeaders.click();
		OrdersPage cartPage = new OrdersPage (driver);
		return cartPage;
	}
		
	
	
	

}
