package onlinesystems.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlinesystems.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement userEmailElement;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement userPasswordElement;

	@FindBy(xpath = "//input[@id='login']")
	WebElement submitElement;

	@FindBy(css = ".ng-star-inserted")
	WebElement loginErrorMsg;

	public ProductCatalog loginApplication(String userEmail, String userPassword) {
		userEmailElement.sendKeys(userEmail);
		userPasswordElement.sendKeys(userPassword);
		submitElement.click();
		ProductCatalog prodCatalog = new ProductCatalog(driver);
		return prodCatalog;
	}

	public String getLoginErrorMsg() {

		waitForElementToApearEL(loginErrorMsg);

		String errorMessage = loginErrorMsg.getText();

		return errorMessage;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
