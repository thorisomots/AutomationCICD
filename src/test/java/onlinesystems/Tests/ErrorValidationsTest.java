package onlinesystems.Tests;

import org.testng.annotations.Test;
import onlinesystems.TestComponents.Retry; // Correct import

import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;

import org.testng.annotations.*;

import onlinesystems.TestComponents.BaseTests;

public class ErrorValidationsTest extends BaseTests {
	

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginValidation() throws IOException {

		String usrEmail = "jakop.musk@x.com1";

		String pass = "@One12343";
		landing.loginApplication(usrEmail, pass);

		Assert.assertEquals("Incorrect email or password.", landing.getLoginErrorMsg());

	}

	@Test
	public void submitOrder() throws IOException {

		String usrEmail = "jakop.musk@x.com1";

		String pass = "@One12343";
		landing.loginApplication(usrEmail, pass);

		Assert.assertEquals("Incorrect email or password.", landing.getLoginErrorMsg());

	}

}
