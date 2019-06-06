package com.ant.app.src.reader;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

@Component
public class ToiSourceReaderImpl implements SourceReader {
	
	@Autowired
	AppProperties appProperties;

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
		url = this.appProperties.getAppSrcToiUrl();
		cssSelectors = this.appProperties.getToiCssSelectors();
		phantomPath = this.appProperties.getAppPhantomPath();
	}

	@Override
	public void sourceReader() {
		// TODO Auto-generated method stub
		
		WebDriver iDriver = null;
		WebDriver driver = null;
		DesiredCapabilities dcap = new DesiredCapabilities();
		String[] phantomArgs = new String[] { this.appProperties.getWebDriverParam() };
		dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);

		File file = new File(phantomPath);
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		driver = new PhantomJSDriver(dcap);
		driver.get(url);
		int i = 0;
		for (WebElement e : driver.findElements(By.tagName("link"))) {
			System.out.println(e.getText());
			NewsStorie ns = new NewsStorie();
			if (i<=2) {
				try {
					iDriver = new PhantomJSDriver(dcap);
					iDriver.get(e.getText());
					ns.setHeading(iDriver.findElement(By.cssSelector(cssSelectors.get(0))).getText());
					ns.setMessage(iDriver.findElement(By.cssSelector(cssSelectors.get(1))).getText());
					ns.setImage("");
					newsStories.add(ns);
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
		return newsStories;
	}
	
	

}
