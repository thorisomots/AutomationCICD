package onlinesystems.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlinesystems.abstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productName;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By product = By.cssSelector(".mb-3");

	By addToCart = By.cssSelector(".card-body button:last-of-type");

	By toastLocator = By.xpath("//div[@id='toast-container']");

	By cartlocator = By.xpath("//button[@routerlink='/dashboard/cart']");

	public List<WebElement> getProductList() {
		waitForElementToAppearPF(product);

		return productName;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector(".card-body h5")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);

		prod.findElement(addToCart).click();
		
		waitForElementToAppearPF(toastLocator);
		waitForElementToDissapearEL(spinner);
		waitForElementToDissappearPF(toastLocator);
		goToCartPage();
		waitForElementToAppearPF(cartlocator);
	}

}
