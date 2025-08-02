package onlinesystems.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import onlinesystems.TestComponents.BaseTests;
import onlinesystems.pageobjects.CartPage;
import onlinesystems.pageobjects.CheckoutPage;
import onlinesystems.pageobjects.ConfirmationPage;
import onlinesystems.pageobjects.LandingPage;
import onlinesystems.pageobjects.OrdersPage;
import onlinesystems.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTests {
String productName = "IPHONE 13 PRO";
	@Test(dataProvider = "getData", groups = "purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException {

		String country = "South Africa";

		ProductCatalog prodCatalog = landing.loginApplication(input.get("usrEmail"), input.get("pass"));

		List<WebElement> prodName = prodCatalog.getProductList();

		prodCatalog.addProductToCart(input.get("productName"));

		CartPage cartPage = prodCatalog.goToCartPage();

		Boolean matchProd = cartPage.verifyProductDisplays(input.get("productName"));

		Assert.assertTrue(matchProd);

		CheckoutPage checkoutPage = cartPage.goToCheckOut();

		checkoutPage.selectCountry(country);

		ConfirmationPage confirmPage = checkoutPage.submitOrder();

		String messageOrder = confirmPage.getConfirmationMsg();

		Assert.assertTrue(messageOrder.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {

		ProductCatalog prodCatalog = landing.loginApplication("jakop.musk@x.com", "@One1234");

		OrdersPage ordersPage = prodCatalog.goToOrderHistory();

		Assert.assertTrue(ordersPage.VerifyOrderHistoryDisplays(productName));
	}



	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJSONFile(
				System.getProperty("user.dir") + "/src/test/java/onlinesystems/data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//		return new Object [] [] {{"jakop.musk@x.com","@One1234","IPHONE 13 PRO"},{"jakop.musk@x.com","@One1234", "ADIDAS ORIGINAL"}};
//	}
//	HashMap<String, String> mapCredentials = new HashMap<String,String>();
//	
//	mapCredentials.put("usrEmail", "jakop.musk@x.com");
//	mapCredentials.put("pass", "@One1234");
//	mapCredentials.put("productName", "IPHONE 13 PRO");
//	
//HashMap<String, String> mapCredentials1 = new HashMap<String,String>();
//	
//	mapCredentials1.put("usrEmail", "jakop.musk@x.com");
//	mapCredentials1.put("pass", "@One1234");
//	mapCredentials1.put("productName", "ADIDAS ORIGINAL");

}
