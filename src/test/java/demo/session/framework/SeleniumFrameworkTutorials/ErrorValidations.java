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

public class ErrorValidations extends BaseTest {

	@Test (groups= {"ErrorHandling"})
	public void submitOrder() throws InterruptedException, IOException {

		landingPage.loginApplication("mayureshpi2@gmail.com", "Placed@1007");
		driver.manage().window().maximize();
		String errorRet = landingPage.getErrorMessage();
		Assert.assertEquals( errorRet ,"Incorrect email or password.");

	}

}
