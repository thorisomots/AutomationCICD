package onlinesystems.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlinesystems.abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement enterCountry;

	@FindBy(xpath = "//section/button/span")
	WebElement selectCountryButton;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;

	By result = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

	public void selectCountry(String country) {

		enterCountry.sendKeys(country);

		waitForElementToAppearPF(result);

		selectCountryButton.click();

	}

	public ConfirmationPage submitOrder() {
		submit.click();

		return new ConfirmationPage(driver);
	}
}
