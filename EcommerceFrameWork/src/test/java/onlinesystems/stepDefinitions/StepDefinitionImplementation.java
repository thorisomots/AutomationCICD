package onlinesystems.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onlinesystems.TestComponents.BaseTests;
import onlinesystems.pageobjects.CartPage;
import onlinesystems.pageobjects.CheckoutPage;
import onlinesystems.pageobjects.ConfirmationPage;
import onlinesystems.pageobjects.LandingPage;
import onlinesystems.pageobjects.ProductCatalog;

public class StepDefinitionImplementation extends BaseTests {

	public LandingPage landingPage;
	public ProductCatalog prodCatalog;
	public ConfirmationPage confirmPage;
	
	String country = "South Africa";

	@Given("I landed on the ecommerce page")
	public void i_landed_on_the_ecommerce_page() throws IOException {
		landingPage = luanchApplication();
	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void i_logged_in_with_username_and_password(String username, String password) {
		prodCatalog = landing.loginApplication(username, password);

	}

	@When("^I add product (.+) from Cart$")
	public void i_add_product_from_Cart(String productName) {
		List<WebElement> prodName = prodCatalog.getProductList();

		prodCatalog.addProductToCart(productName);
	}

	@And("^checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName) {
		 CartPage cartPage = prodCatalog.goToCartPage();

			Boolean matchProd = cartPage.verifyProductDisplays(productName);

			Assert.assertTrue(matchProd);

			CheckoutPage checkoutPage = cartPage.goToCheckOut();

			checkoutPage.selectCountry(country);

			 confirmPage = checkoutPage.submitOrder();
}
	
	@Then("^\"([^\"]*)\" message is displayed on the confirmation page$")
	public void message_is_displayed_on_the_confirmation_page(String message) {
		String messageOrder = confirmPage.getConfirmationMsg();

		Assert.assertTrue(messageOrder.equalsIgnoreCase(message));
		
		driver.close();
	}
	
		@Then("^\"([^\"]*)\" message is displayed$")
		public void message_is_displayed(String errorMessage) {
			
			Assert.assertEquals(errorMessage, landing.getLoginErrorMsg());
			driver.close();
			
		}
}
