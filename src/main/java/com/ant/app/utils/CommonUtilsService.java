package com.ant.app.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.app.config.AppProperties;

@Component
public class CommonUtilsService {
	
	@Autowired
	AppProperties appProperties;
	
//	System.out.println("=====>"+iDriver.findElement(By.cssSelector(cssSelectors.get(0))).getText());
//	System.out.println("=====>"+iDriver.findElement(By.cssSelector(cssSelectors.get(1))).getText());
//	iDriver.findElements(By.cssSelector("div")).stream().filter((a) -> {
//		System.out.println(a.getTagName()+"==>"+a.getAttribute("class"));
//		return a.getAttribute("class") != null;
//	}).collect(Collectors.toList());
	
	public WebDriver getDriver(String driverName, String params){
		
		WebDriver driver = null;
		
		switch(driverName){
			case "CHROME":
		        System.setProperty("webdriver.chrome.driver", this.appProperties.getAppChromePath());
		        ChromeOptions options = new ChromeOptions();
		        options.addArguments("headless");
		        options.addArguments("window-size=1200x600");

		        driver = new ChromeDriver(options);
				break;
			case "FIREFOX":
				break;
			case "PANTOMJS":
				DesiredCapabilities dcap = new DesiredCapabilities();
				String[] phantomArgs = new String[] { this.appProperties.getWebDriverParam() };
				dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
				File file = new File(this.appProperties.getAppPhantomPath());
				System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
				driver = new PhantomJSDriver(dcap);
				break;
			default:
				break;
		}
		
		return driver;
		

		
		
	}
	

}
