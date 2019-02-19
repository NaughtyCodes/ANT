package com.ant.app.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ant.app.domain.NewsStorie;

public class UrlSourceReader implements SourceReader {

	private static String url;
	private static List<String> cssSelectors = new ArrayList<String>();
	private static String phantomPath;
	private static List<NewsStorie> newsStories = new ArrayList<NewsStorie>();
	
	@Override
	public void sourceInitilizer() {
		// TODO Auto-generated method stub
		this.newsStories.clear();
		this.cssSelectors.clear();
		this.url = "http://www.thehindu.com/news/cities/chennai/feeder/default.rss";
		this.cssSelectors.add("h1[class='title']");
		this.cssSelectors.add("div[id^='content-body-']");
		this.phantomPath = "G:\\sw\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";
		
	}

	@Override
	public void sourceReader() {
		// TODO Auto-generated method stub
		
		WebDriver iDriver = null;
		WebDriver driver = null;
		DesiredCapabilities dcap = new DesiredCapabilities();
		String[] phantomArgs = new String[] { "--webdriver-loglevel=NONE" };
		dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);

		File file = new File(this.phantomPath);
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		driver = new PhantomJSDriver(dcap);
		driver.get(this.url);
		int i = 0;
		for (WebElement e : driver.findElements(By.tagName("link"))) {
			System.out.println(e.getText());
			NewsStorie ns = new NewsStorie();
			if (!e.getText().contentEquals("https://www.thehindu.com/") && (i<=2)) {
				try {
					iDriver = new PhantomJSDriver(dcap);
					iDriver.get(e.getText());
					ns.setHeading(iDriver.findElement(By.cssSelector(this.cssSelectors.get(0))).getText());
					ns.setMessage(iDriver.findElement(By.cssSelector(this.cssSelectors.get(1))).getText());
					ns.setImage("");
					this.newsStories.add(ns);
					iDriver.quit();
					i++;
				} catch (Exception exception) {
					iDriver.quit();
					driver.quit();
				}
			}
		}
		driver.quit();
	}

	@Override
	public List<NewsStorie> getNewsStories() {
		// TODO Auto-generated method stub
		return this.newsStories;
	}
	
	

}
