package onlinesystems.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlinesystems.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	
	@FindBy (xpath = "//*[@class='cartSection']/h3")
	List<WebElement> cartProds;
	
	@FindBy (xpath = "//ul/li[3]/button[@class='btn btn-primary']")
	WebElement checkOutElement;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	

	
	public Boolean verifyProductDisplays(String productName) {
		Boolean matchProd = cartProds.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		
		return matchProd;
	}
	
	public CheckoutPage goToCheckOut() {
		checkOutElement.click();
		
		return new CheckoutPage(driver);
	}
}
