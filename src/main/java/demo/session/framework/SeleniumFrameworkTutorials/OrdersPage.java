package demo.session.framework.SeleniumFrameworkTutorials;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.session.framework.AbstractComponentsRepo.AbstractClassesDemo;

public class OrdersPage extends AbstractClassesDemo {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> orderDetails;

	public boolean verifyOrderISDisplay(String expectedOrder) {

		Boolean isDisplayed = orderDetails.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(expectedOrder));
		return isDisplayed;
	}

}
