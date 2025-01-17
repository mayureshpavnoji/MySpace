package demo.session.framework.SeleniumFrameworkTutorials;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.session.framework.AbstractComponentsRepo.AbstractClassesDemo;

public class CartPage extends AbstractClassesDemo {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userDetails = driver.findElement(By.id("userEmail"));

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	@FindBy(xpath = "(//button[normalize-space()='Buy Now'])[1]")
	WebElement proceedNextPage;

	public boolean verifyProductDisplay(String expectedProduct) {

		Boolean isDisplayed = cartProducts.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(expectedProduct));
		return isDisplayed;
	}

	public CheckOutPage goToCheckOut() {

		proceedNextPage.click();
		return new CheckOutPage(driver);

	}

}
