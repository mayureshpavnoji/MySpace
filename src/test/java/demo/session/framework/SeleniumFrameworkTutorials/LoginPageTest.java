package demo.session.framework.SeleniumFrameworkTutorials;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		String expectedProduct = "IPHONE 13 PRO";
		LandingPage b = new LandingPage(driver);
		b.goTo("https://rahulshettyacademy.com/client");
		b.loginApplication("mayureshpavnoji1@gmail.com", "Placed@1007");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> list = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = list.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartItems.stream().anyMatch(product -> product.getText().equalsIgnoreCase(expectedProduct));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("(//button[normalize-space()='Buy Now'])[1]")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//div[@class='payment__shipping']//button[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".action__submit")).click();
		Thread.sleep(3000);
		String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();

	}

}
