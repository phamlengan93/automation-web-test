package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {
	protected WebDriver driver;
	@BeforeClass
	public void initDriverInstance() {
		//khoi tao chrome driver
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		//set max size cho cua so trinh duyet
		driver.manage().window().maximize();
	}
	@AfterClass
	public void closeDriverInstance() {
		//dong trinh duyet
		driver.close();
	}
}
