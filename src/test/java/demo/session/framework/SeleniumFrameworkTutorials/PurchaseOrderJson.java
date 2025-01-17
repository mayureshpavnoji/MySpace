package demo.session.framework.SeleniumFrameworkTutorials;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import demo.session.TestComponents.BaseTest;

public class PurchaseOrderJson extends BaseTest {

	String expectedProduct = "IPHONE 13 PRO";

	@Test (groups= {"BookOrderSuccess"},dataProvider="dataProviderUtility") 
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

	@Test(dependsOnMethods = { "validateOrderSubmission" })
	public void validateOrderSummary() throws InterruptedException {

		landingPage.loginApplication("mayureshpavnoji1@gmail.com", "Placed@1007");
		OrdersPage page = landingPage.goToOrdersPage();
		Boolean isOrderDisplayed = page.verifyOrderISDisplay(expectedProduct);
		Assert.assertTrue(isOrderDisplayed);

	}
	
	@DataProvider
	public Object [][] dataProviderUtility () throws IOException {
		
		List <HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")
				+ "//src//test//java//demo//session//framework//TestData//PurchaseOrderData.json");
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put ("email","mayureshpavnoji1@gmail.com");
//		map.put ("password","Placed@1007");
//		
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put ("email","mayureshpavnoji1@gmail.com");
//		map1.put ("password","Placed@1007");
		
		
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
	}

}
