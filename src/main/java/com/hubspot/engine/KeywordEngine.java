package com.hubspot.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hubspot.Base.Base;

public class KeywordEngine {
	public static Workbook book;
    public static Sheet sheet;
    public final String SCENARIO_SHEET_PATH = "C:\\Users\\avije\\eclipse-workspace\\KeywordDrivenFramework"
    		+ "\\src\\main\\java\\com\\hubspot\\scenario\\HubSpot.xlsx";
    public WebDriver driver;
    public Properties prop;
    public Base base;
    public WebElement element;

    public void startExecution(String sheetName) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        FileInputStream file = null;

        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            try {
                book = WorkbookFactory.create(file);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {
                String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); /* id = Usrname */
                String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

                switch (action) {
                    case "open browser":
                        base = new Base();
                        prop = base.init_properties();
                        if (value.isEmpty() || value.equals("NA")) {
                            driver = base.init_driver(prop.getProperty("browser"));
                        } else {
                            driver = base.init_driver(value);
                        }
                        break;

                    case "enter url":
                        if (value.isEmpty() || value.equals("NA")) {
                            driver.get(prop.getProperty("url"));
                        } else {
                            driver.get(value);
                        }
                        Thread.sleep(3000);
                        break;

                    case "quit":
                        driver.quit();
                        break;

                    default:
                        break;
                }

                switch (locatorType) {

                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from Element : "+ elementText);
                        }
                        locatorType = null;
                        break;

                    case "name":
                        element = driver.findElement(By.name(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from Element : "+ elementText);
                        }
                        locatorType = null;
                        break;

                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from Element : "+ elementText);
                        }
                        locatorType = null;
                        break;

                    case "cssSelector":
                        element = driver.findElement(By.cssSelector(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from Element : "+ elementText);
                        }
                        locatorType = null;
                        break;

                    case "className":
                        element = driver.findElement(By.className(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from Element : "+ elementText);
                        }else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from Element : "+ elementText);
                        }
                        locatorType = null;
                        break;


                    case "linkText":
                        element = driver.findElement(By.linkText(locatorValue));
                        element.click();
                        locatorType = null;
                        break;

                    case "partiaLinkText":
                        element = driver.findElement(By.partialLinkText(locatorValue));
                        element.click();
                        locatorType = null;
                        break;


                    default:
                        break;
                }
            } catch (Exception e) {

            }
        }

    }
}


	
	