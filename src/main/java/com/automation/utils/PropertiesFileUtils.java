package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
//duong dan den properties file trong folder configuration
	private static String CONFIG_PATH = "./configuration/configs.properties";
	
	//lay ra gia tri property bat ky theo key
	public static String getProperty (String key) {
		Properties properties = new Properties();
		String value = null;
		FileInputStream fileInputStream = null;
		
	//bat exception
		try {
			fileInputStream = new FileInputStream(CONFIG_PATH);
			properties.load(fileInputStream);
			value = properties.getProperty(key);
			return value;
		} catch (Exception ex) {
			System.out.println("Xay ra loi khi dua gia tri cua "+key);
			ex.printStackTrace();
		} finally {
			//luon nhay vao day du co xay ra exception hay khong
			//thuc hien dong dong luong doc
			if (fileInputStream!= null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	// ghi property vao file
	public static void setProperty (String key, String value) {
		Properties properties = new Properties();
		FileOutputStream fileOutputStream = null;
		try {
			//Khoi tao gia tri cho doi tuong FileOutputStream
			fileOutputStream = new FileOutputStream(CONFIG_PATH);
			//load properties file hien thi va thuc hien mapping value voi key tuong ung
			properties.setProperty(key, value);
			//luu key va value vao properties file
			properties.store(fileOutputStream, "Set new value in properties");
			System.out.println("Set new value in file properties success.");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			//luon nhay vao day du co xay ra exception hay khong
			// thuc hien dong luong ghi
				if (fileOutputStream!=null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
	}
}

