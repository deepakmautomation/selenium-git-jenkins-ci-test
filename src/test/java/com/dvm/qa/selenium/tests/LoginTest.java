package com.dvm.qa.selenium.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver _driver;

	@BeforeMethod
	public void setUp() {

		_driver = new ChromeDriver();
		_driver.get("https://www.saucedemo.com/");
		_driver.manage().window().maximize();
		_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void verify_login() {
		_driver.findElement(By.id("user-name")).sendKeys("standard_user");
		_driver.findElement(By.id("password")).sendKeys("secret_sauce");
		_driver.findElement(By.id("login-button")).click();
		Assert.assertEquals(_driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products");

		_driver.findElement(By.id("react-burger-menu-btn")).click();

		WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(_driver.findElement(By.linkText("Logout"))));
		_driver.findElement(By.linkText("Logout")).click();
		Assert.assertEquals(_driver.getTitle(), "Swag Labs");
	}

	@AfterMethod
	public void tearDown() {
		_driver.quit();
	}

}
