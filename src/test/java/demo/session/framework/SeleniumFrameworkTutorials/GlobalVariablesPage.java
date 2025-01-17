package demo.session.framework.SeleniumFrameworkTutorials;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import demo.session.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GlobalVariablesPage extends BaseTest {

	@Test
	public void submitOrder() throws InterruptedException, IOException {

		// TODO Auto-generated method stub
		String expectedProduct = "IPHONE 13 PRO";
		landingPage.loginApplication("mayureshpavnoji1@gmail.com", "Placed@1007");
		driver.manage().window().maximize();
		ProductCatalogue productObj = new ProductCatalogue(driver);
		productObj.addProductToCart(expectedProduct);
		productObj.goToCartPage();
		CartPage cart = new CartPage(driver);
		Boolean match = cart.verifyProductDisplay(expectedProduct);
		Assert.assertTrue(match);
		CheckOutPage checkout = cart.goToCheckOut();
		checkout.selectCountry("India");
		ConfirmationPage confirmation = checkout.proceedCheckout();
		confirmation.orderSubmittion("Thankyou for the order.");

	}

}
