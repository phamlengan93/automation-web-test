package com.automation.utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class CaptureScreenshot {
	static String filename = null;
	public static void takeScreenshot (WebDriver driver, String name) {
		try {
			//ten anh la phan ten email dang nhap, kieu anh la png
			String imageName = name +".png";
			
			//thuc hien chup anh man hinh, lay ra doi tuong file anh source
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			
			//tao doi tuong file voi ten da dat ben tren tai thu muc /ScreenShots
			//Sau do thuc hien copy file anh nguon vao file dich do
			filename = "./screenshots/" + imageName;
			File destiny = new File(filename);
			FileHandler.copy(source, destiny);
		} catch (Exception ex) {
			System.out.println("Da xay ra loi khi chup man hinh");
			ex.printStackTrace();
		}
		attachScreenshotToReport(filename);
	}
	
	public static void attachScreenshotToReport(String filename) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			File f = new File (filename);
			Reporter.log ("<a title=\"Snapshot\" href=\"" +f.getAbsolutePath()+"\">"+ 
			"<img width=\"418\" height=\"240\" alt=\"alternativeName\" title=\"title\" src=\"../surefire-reports/html/screenShots/"+
					filename+"\"</a>");
		} catch (Exception e) {
			System.out.println("Not able to take screenshot");
		}
}
}
