package com.ant.app.controller;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class AntController {

	@RequestMapping("/ant")
	public String callAnt() {

		StringBuilder sb = new StringBuilder();
		WebDriver iDriver = null;
		WebDriver driver = null;
		DesiredCapabilities dcap = new DesiredCapabilities();
		String[] phantomArgs = new String[] { "--webdriver-loglevel=NONE" };
		dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);

		File file = new File("G:\\sw\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		driver = new PhantomJSDriver(dcap);
		driver.get("http://www.thehindu.com/news/cities/chennai/feeder/default.rss");
		int i = 0;
		for (WebElement e : driver.findElements(By.tagName("link"))) {
			System.out.println(e.getText());
			if (!e.getText().contentEquals("https://www.thehindu.com/") && (i<=2)) {
				try {
					iDriver = new PhantomJSDriver(dcap);
					iDriver.get(e.getText());
					sb.append("<h3>");
					sb.append(iDriver.findElement(By.cssSelector("h1[class='title']")).getText());
					sb.append("<h3 />");
					sb.append("<br /><br />");
					sb.append(iDriver.findElement(By.cssSelector("div[id^='content-body-']")).getText());
					sb.append("<br /><br />");
					iDriver.quit();
					i++;
					//break;
				} catch (Exception exception) {
					iDriver.quit();
					driver.quit();
				}
			}
		}

		driver.quit();

		return sb.toString();

	}

}
