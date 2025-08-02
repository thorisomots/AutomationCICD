package onlinesystems.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import onlinesystems.pageobjects.LandingPage;

public class BaseTests2 {

	public WebDriver driver;
	public LandingPage landing;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();

		FileInputStream globalData = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/onlinesystems/resources/GlobalData.properties");

		properties.load(globalData);

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");

		// properties.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {
			System.out.println("No compatible browser detected!!");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();

		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage luanchApplication() throws IOException {
		driver = initializeDriver();

		landing = new LandingPage(driver);

		landing.goTo();

		return landing;

	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();

	}

	public List<HashMap<String, String>> getJSONFile(String filePath) throws IOException {

		String jsonFileCOntent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapData = new ObjectMapper();

		List<HashMap<String, String>> dataBind = mapData.readValue(jsonFileCOntent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return dataBind;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File screenshotSource = screenShot.getScreenshotAs(OutputType.FILE);
		File fileDestination = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");

		FileUtils.copyFile(screenshotSource, fileDestination);

		return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
	}

}
