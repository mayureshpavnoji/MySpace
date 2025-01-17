package demo.session.framework.SeleniumFrameworkTutorials;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import demo.session.TestComponents.BaseTest;
import demo.session.TestComponents.Retry;

public class ProductCatalogueHashMap extends BaseTest {

	String expectedProduct = "IPHONE 13 PRO";

	@Test (groups= {"BookOrderSuccess"},dataProvider="dataProviderUtility",retryAnalyzer=Retry.class) 
	public void validateOrderSubmission(HashMap<String,String> input) throws InterruptedException {

		landingPage.loginApplication(input.get("email"),input.get("password"));
		driver.manage().window().maximize();
		ProductCatalogue productObj = new ProductCatalogue(driver);
		productObj.addProductToCart(expectedProduct);
		productObj.goToCartPage();
		CartPage cart = new CartPage(driver);
		Boolean match = cart.verifyProductDisplay(expectedProduct);
		Assert.assertTrue(match);


	}

	@Test(groups= {"BookOrderSuccess"},dependsOnMethods = { "validateOrderSubmission" })
	public void validateOrderSummary() throws InterruptedException {

		landingPage.loginApplication("mayureshpavnoji1@gmail.com", "Placed@1007");
		OrdersPage page = landingPage.goToOrdersPage();
		Boolean isOrderDisplayed = page.verifyOrderISDisplay(expectedProduct);
		Assert.assertTrue(isOrderDisplayed);

	}
	
	@Test(groups= {"BookOrderSuccess"},dependsOnMethods = { "validateOrderSummary" },retryAnalyzer=Retry.class)
	public void validateOrderFailureDemo() throws InterruptedException {

		landingPage.loginApplication("mayureshpavnoji1@gmail.com", "Placed@1007");
		OrdersPage page = landingPage.goToOrdersPage();
		//Boolean isOrderDisplayed = page.verifyOrderISDisplay(expectedProduct);
		Assert.assertTrue(false);

	}
	
	@DataProvider
	public Object [][] dataProviderUtility () {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put ("email","mayureshpavnoji1@gmail.com");
		map.put ("password","Placed@1007");
		
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put ("email","mayureshpavnoji1@gmail.com");
		map1.put ("password","Placed@1007");
		
		
		return new Object [][] {{map},{map1}};
		
	}

}
