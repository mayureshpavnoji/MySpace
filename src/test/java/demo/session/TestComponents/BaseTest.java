package demo.session.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.session.framework.SeleniumFrameworkTutorials.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeBrowser() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties");
		prop.load(fis);
		//String browser = prop.getProperty("browser");

		//browser = "chrome";
		
		//Added commit to push to git
		
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser"): "chrome";		

		if (browser.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions ();

			
			if (browser.contains("headless")) {
				
				options.addArguments ("headless");
			}

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension (1440,900));
			
		
		} else if (browser.equalsIgnoreCase("firefox")) {

			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;

	}

	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		@SuppressWarnings("deprecation")
		// convert json to string
		String jsonContent = FileUtils.readFileToString(
				new File(filePath),StandardCharsets.UTF_8);

		// String to Hashmap using jakson data bind dependency

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

	}

	
	public String getScreenShot (String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File (System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException {

		driver = initializeBrowser();
		landingPage = new LandingPage(driver);
		landingPage.goTo("https://rahulshettyacademy.com/client");
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void closeApp() throws IOException {

		driver.quit();

	}

}
