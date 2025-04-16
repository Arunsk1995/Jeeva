package com.scrollappium;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumBy.ByAndroidUIAutomator;

public class ScrollAppiumRunner extends BaseClass {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		LaunchApp();
		
		Thread.sleep(3000);
		
		 WebElement element = driver.findElement(By.id("in.amazon.mShop.android.shopping:id/sso_continue"));
		 element.click();
		 Thread.sleep(3000);
	 scrollToElementAndClick("(//android.widget.Button[@text=\"Collect Coupon\"])[3]");
//		 driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"4+ star deals for you\")")).click();
	 Thread.sleep(2000);
		
	}

}
