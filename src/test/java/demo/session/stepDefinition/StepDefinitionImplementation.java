package demo.session.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import demo.session.TestComponents.BaseTest;
import demo.session.framework.SeleniumFrameworkTutorials.CartPage;
import demo.session.framework.SeleniumFrameworkTutorials.CheckOutPage;
import demo.session.framework.SeleniumFrameworkTutorials.ConfirmationPage;
import demo.session.framework.SeleniumFrameworkTutorials.LandingPage;
import demo.session.framework.SeleniumFrameworkTutorials.OrdersPage;
import demo.session.framework.SeleniumFrameworkTutorials.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {

	LandingPage landingpage;
	ProductCatalogue catalogue;
	ConfirmationPage confirmation;
	CartPage cart;

	@Given("I landed on the ecommerce page")
	public void i_landed_on_the_ecommerce_page() throws IOException {

		landingpage = launchApp();

	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {

		catalogue = landingpage.loginApplication(username, password);


	}

	@When("^I add product (.+) to the cart$")

	public void i_add_product_to_the_cart(String productName) throws InterruptedException {
		
//		OrdersPage page = landingPage.goToOrdersPage();
//		Boolean isOrderDisplayed = page.verifyOrderISDisplay(productName);
//		Assert.assertTrue(isOrderDisplayed);
		
		ProductCatalogue productObj = new ProductCatalogue(driver);
		productObj.addProductToCart(productName);
		productObj.goToCartPage();	


	}

	@And("^CheckOut (.+) and submit the order$")

	public void checkOut_and_submit_the_order(String productName) throws InterruptedException {

		CartPage cart = new CartPage(driver);
		Boolean match = cart.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkout = cart.goToCheckOut();
		checkout.selectCountry("India");
		confirmation = checkout.proceedCheckout();



	}
	
	
	@Then ("{string} message is displayed on confirmationPage")
	
	public void message_is_displayed_on_confirmationPage (String string) throws InterruptedException {

		confirmation.orderSubmittion(string);

	}
	
	@Then ("^Expected error message (.+) message is displayed on confirmationPage$")
	
	public void expected_error_message_is_displayed_on_confirmationPage (String string) throws InterruptedException {

		String errorRet = landingPage.getErrorMessage();
		Assert.assertEquals(string, errorRet);

	}
	

}
