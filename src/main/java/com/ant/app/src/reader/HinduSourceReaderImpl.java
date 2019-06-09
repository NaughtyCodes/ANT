package com.ant.app.src.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ant.app.config.AppProperties;
import com.ant.app.domain.NewsStorie;
import com.ant.app.utils.CommonUtilsService;
import com.ant.app.utils.StaticNames;


@Component
public class HinduSourceReaderImpl implements SourceReader {
	
	@Autowired
	AppProperties appProperties;
	
	@Autowired
	CommonUtilsService commonUtilsService;

	private static String url;
	private static String phantomPath;
	private static String webDriverParam;
	private static List<String> cssSelectors = new ArrayList<String>();
	private static List<NewsStorie> newsStories = new ArrayList<NewsStorie>();

	@Override
	public void sourceInitilizer() {
		// TODO Auto-generated method stub
		newsStories.clear();
		webDriverParam = this.appProperties.getWebDriverParam();
		url = this.appProperties.getAppSrcHinduUrl();
		cssSelectors = this.appProperties.getHinduCssSelectors();
		phantomPath = this.appProperties.getAppPhantomPath();
	}

	@Override
	public void sourceReader() {
		// TODO Auto-generated method stub
		WebDriver driver = this.commonUtilsService.getDriver(StaticNames.PANTOMJS,"");
		driver.get(url);
		List<String> links = new ArrayList<String>();
		driver.findElements(By.tagName("link")).forEach((e) -> {
				System.out.println(e.getText());
				links.add(e.getText());
			});
		int i = 0;
		for (String e : links) {
			System.out.println(e);
			NewsStorie ns = new NewsStorie();
			if (!e.contentEquals("https://www.thehindu.com/") && (i<=2)) {
				try {
					driver.navigate().to(e);
					ns.setHeading(driver.findElement(By.cssSelector(cssSelectors.get(0))).getText());
					ns.setMessage(driver.findElement(By.cssSelector(cssSelectors.get(1))).getText());
					ns.setImage("");
					newsStories.add(ns);
				} catch (Exception exception) {
					driver.quit();
				}
			}
			i++;
		}
		driver.quit();
	}

	@Override
	public List<NewsStorie> getNewsStories() {
		// TODO Auto-generated method stub
		return newsStories;
	}
	
	

}
