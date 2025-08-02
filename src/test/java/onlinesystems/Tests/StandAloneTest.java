package onlinesystems.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import onlinesystems.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		String usrEmail = "jakop.musk@x.com";

		String pass = "@One1234";

		String productName = "IPHONE 13 PRO";

		String country = "South Africa";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landing = new LandingPage(driver);

		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(usrEmail);

		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys(pass);

		driver.findElement(By.xpath("//input[@id='login']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));

		List<WebElement> prodName = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = prodName.stream()
				.filter(product -> product.findElement(By.cssSelector(".card-body h5")).getText().equals(productName))
				.findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProds = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));

		Boolean matchProd = cartProds.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(matchProd);

		driver.findElement(By.xpath("//ul/li[3]/button[@class='btn btn-primary']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(country);

		String countryName = driver.findElement(By.xpath("//section/button/span")).getText();

		// System.out.println(countryName.getText());

		Assert.assertEquals(country, countryName);
		
		
		driver.findElement(By.xpath("//section/button/span")).click();

		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

		String messageOrder = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(messageOrder.equalsIgnoreCase("Thankyou for the order."));

		driver.quit();

	}

}
