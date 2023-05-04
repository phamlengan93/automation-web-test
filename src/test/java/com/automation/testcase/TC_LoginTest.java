package com.automation.testcase;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {
	//su dung Data driven doc data test tu excel
	@Test(dataProvider="Excel")
	public void TC01_LoginFirstAccount (String email, String password) throws InterruptedException {
		//Lay URL tu properties file va tai trang
		driver.get(PropertiesFileUtils.getProperty("applicationURL"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//lay dinh danh iconSignin tu properties file va tim kiem, click
		WebElement iconSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("icon_signin_xpath"))));
		iconSignIn.click();
		//thuc hien dang nhap qua loginPage
		LoginPage loginPage = new LoginPage(driver);
		PageFactory.initElements(driver, loginPage);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();
		//lay dinh danh iconSignOut tu properties
		String elementIdentified = PropertiesFileUtils.getProperty("icon_signout_xpath");
		//kiem tra SignOut co hien thi khong, neu hien thi thi click
		WebElement iconSignOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
		iconSignOut.click();
		Thread.sleep(2000);
	}
	
	@Test
	@DataProvider(name="Excel")
	public Object [][] testDataGenerator() throws IOException{
		//doc du lieu dau vao tu file excel
		FileInputStream file = new FileInputStream("./data/assignment2_data_test.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet loginSheet = wb.getSheet("Login");
		int numberOfData = loginSheet.getPhysicalNumberOfRows();
		//mang 2 chieu
		Object [][] data = new Object [numberOfData][2];
		for (int i=0; i<numberOfData; i++) {
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell email = row.getCell(0);
			XSSFCell password = row.getCell(1);
			//luu du lieu vao mang
			data [i][0] = email.getStringCellValue();
			data [i][1] = password.getStringCellValue();
		}
		return data;
	}
	
	@AfterMethod
	public void takeScreenshot (ITestResult result) {
		//ITestResult de lay trang thai va ten va tham so cua tung testcase
		//phuong thuc nay se duoc goi moi khi @Test thuc thi xong
		//ta co the kiem tra ket qua testcase tai day
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				//1.lay tham so (parameters) dau vao cua testcase vua chay
				//email:0, password:1
				String email = (String)result.getParameters()[0];
				//2. lay ra phan ten trong email de lam ten cua screenshot
				//tim vi tri (int index) cua ky tu '@' va lay ra chuoi con
				//dung truoc @ qua thu vien cua String la: indexOf() va subString()
				int index = email.indexOf("@");
				String accountName = email.substring(0,index);
				CaptureScreenshot.takeScreenshot(driver, accountName);
			} catch (Exception e) {
				System.out.println("Loi xay ra screenshot"+e.getMessage());
			}
		}
	}
}
