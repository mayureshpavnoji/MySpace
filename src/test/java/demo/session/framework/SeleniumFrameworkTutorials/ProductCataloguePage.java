package demo.session.framework.SeleniumFrameworkTutorials;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import demo.session.TestComponents.BaseTest;

public class ProductCataloguePage extends BaseTest {

	String expectedProduct = "IPHONE 13 PRO";

	@Test (groups= {"BookOrderSuccess"},dataProvider="dataProviderUtility") 
	public void validateOrderSubmission(String username, String password) throws InterruptedException {

		landingPage.loginApplication(username, password);
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

	@Test(dependsOnMethods = { "validateOrderSubmission" })
	public void validateOrderSummary() throws InterruptedException {

		landingPage.loginApplication("mayureshpavnoji1@gmail.com", "Placed@1007");
		OrdersPage page = landingPage.goToOrdersPage();
		Boolean isOrderDisplayed = page.verifyOrderISDisplay(expectedProduct);
		Assert.assertTrue(isOrderDisplayed);

	}
	
	@DataProvider
	public Object [][] dataProviderUtility () {
		
		return new Object [][] {{"mayureshpavnoji1@gmail.com", "Placed@1007"}};
		
	}

}
