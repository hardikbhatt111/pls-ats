package com.pls.utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {
	private final String propertyFilePath= "E:\\Poonam Data\\eclipse-workspace\\PremiumLegalStaffing\\src\\main\\resources\\config.properties";
	public Config() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	static Properties properties = new Properties();

	/**
	 * This method is used to read the value of the given property from the
	 * properties file.
	 *
	 * @param property
	 *            : the property whose value is to be fetched.
	 * @return the value of the given property.
	 * @throws IOException
	 */
	public String readProperty(String property) {		
		String driverPath = properties.getProperty(property);
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");	
	}

	
	public static void writeProperty(String property, String value) {
		// Write properties file.
		// OutputStream outPropFile = null;

		try {
			InputStream inPropFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\config.properties");
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\config.properties");
			// System.getProperty("user.dir")+"\\AnswerKeys\\"+top+".properties"

			properties.setProperty(property, value);
			properties.store(outPropFile, null);
			outPropFile.close();
		} catch (IOException e) {
		}
	}

	public static void clearProperty() {
		try {
			InputStream inPropFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\config.properties");
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\testdata\\config.properties");
			properties.clear();
			outPropFile.close();
		} catch (IOException e) {
		}
	}

	public static void clearProperty(String property) {
		try {
			InputStream inPropFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\testdata\\config.properties");
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\config.properties");
			// properties.clear();
			properties.remove(property);
			properties.store(outPropFile, null);
			outPropFile.close();
		} catch (IOException e) {
		}
	}

	public static void writeDataInReportsData(String property, String str) {
		try {
			InputStream inPropFile = new FileInputStream(
					System.getProperty("user.dir") + "\\ReportsData\\config.properties");
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(
					System.getProperty("user.dir") + "\\ReportsData\\config.properties");
			// System.getProperty("user.dir")+"\\AnswerKeys\\"+top+".properties"

			properties.setProperty(property, str);
			properties.store(outPropFile, null);
			outPropFile.close();
		} catch (IOException e) {
		}
	}

	public static String readReportsData(String property) {
		InputStream inPropFile = null;
		try {
			inPropFile = new FileInputStream(System.getProperty("user.dir") + "\\ReportsData\\config.properties");

			properties.load(inPropFile);

		} catch (IOException e) {
			System.out.println("There was Exception reading the Test data");
		}
		String value = properties.getProperty(property);
		return value;
	}

	public static void clearPropertyFromReports() {
		try {
			InputStream inPropFile = new FileInputStream(
					System.getProperty("C:\\Users\\poonam.deotale\\eclipse-workspace\\PremiumLegalStaffing\\src\\main\\resources\\config.properties"));
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(
					System.getProperty("C:\\Users\\poonam.deotale\\eclipse-workspace\\PremiumLegalStaffing\\src\\main\\resources\\config.properties"));
			properties.clear();
			outPropFile.close();
		} catch (IOException e) {
		}
	}

}