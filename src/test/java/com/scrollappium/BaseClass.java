package com.scrollappium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;


public class BaseClass {
public static WebDriver driver;
	
	public static void LaunchApp() throws MalformedURLException {
		
        DesiredCapabilities capb = new DesiredCapabilities();
		
		capb.setCapability("platformName", "Android");
		capb.setCapability("appium:platformVersion", "12");
		capb.setCapability("appium:deviceName", "5ff6ad22");
		capb.setCapability("appium:automationName", "UiAutomator2" );
		capb.setCapability("appium:appPackage", "in.amazon.mShop.android.shopping");
		capb.setCapability("appium:appActivity","com.amazon.windowshop.home.HomeLauncherActivity");
		
		URL url = new URL("http://127.0.0.1:4723/");
		 driver = new AppiumDriver(url , capb);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	}
	
	public static void swipeDown() {
		
		   Dimension size = driver.manage().window().getSize();
	        int width = size.getWidth();
	        int height = size.getHeight();
	        
	        int centerX = width/2;
	        int startY = (int) (height*0.60);        
	        int endY = (int) (height*0.30);
	        
	        System.out.println("center X " + centerX);
	        System.out.println("Start Y " + startY);
	        System.out.println("End Y" + endY);
	        
	        PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
	        
	        Sequence swipe = new Sequence(pointer, 1);
	        
	        swipe.addAction(pointer.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(), centerX, startY));
	        swipe.addAction(pointer.createPointerDown(1));
	        
	        swipe.addAction(pointer.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), centerX, endY));
	        
	        swipe.addAction(pointer.createPointerUp(1));
	        
	        ((RemoteWebDriver)driver).perform(Arrays.asList(swipe));

			}
	
	
	public String getProjectPath() {
		return System.getProperty("user.dir");
		
	}
	
	public String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties=new Properties();
		properties.load(new FileInputStream(getProjectPath()+"\\config\\config.properties"));
		String value=(String)properties.get(key);
		return value;
	}

	
	public static void scrollToElementAndClick(String ele) {
	    int maxScrollAttempts = 5;
	    int scrollAttempts = 0;
	    
	    while (scrollAttempts < maxScrollAttempts) {
	        List<WebElement> click_btn = driver.findElements(By.xpath(ele));
	        
	        if (!click_btn.isEmpty()) {
	            WebElement element = click_btn.get(0);
	            
	            try {
	                // Check if the element is clickable, visible, and enabled
	                if (element.isDisplayed() && element.isEnabled()) {
	                    element.click();
	                    return; // Exit the method after successful click
	                }
	            } catch (InvalidElementStateException e) {
	                // Handle InvalidElementStateException
	                System.out.println("Caught InvalidElementStateException: " + e.getMessage());
	            }
	        }
	        
	        // If element is not found or not interactable, scroll down and try again
	        swipeDown();
	        scrollAttempts++;
	    }
	    
	    // If the element is not found or clickable after maximum attempts
	    System.out.println("Unable to click element with XPath: " + ele);
	}
	



}
