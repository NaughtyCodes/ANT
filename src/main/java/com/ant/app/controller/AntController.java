package com.ant.app.controller;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.app.config.AppProperties;
import com.ant.app.domain.NewsStorie;
import com.ant.app.src.reader.HinduSourceReaderImpl;
import com.ant.app.src.reader.ToiSourceReaderImpl;

@RestController
@RequestMapping("/call")
public class AntController {

	@Autowired
	AppProperties appProperties;
	
	@Autowired
	HinduSourceReaderImpl hinduSourceReaderImpl;
	
	@Autowired
	ToiSourceReaderImpl toiSourceReaderImpl;
	
	@RequestMapping("/news/hindu/today")
	public List<NewsStorie> readHinduNewsToday() {
		
		StringBuilder sb = new StringBuilder();
		hinduSourceReaderImpl.sourceInitilizer();
		hinduSourceReaderImpl.sourceReader();
		for(NewsStorie n : hinduSourceReaderImpl.getNewsStories()) {
			sb.append(n.htmlString());
		}
		return hinduSourceReaderImpl.getNewsStories();
	}
	
	@RequestMapping("/news/toi/today")
	public List<NewsStorie> readToiNewsToday() {
		
		StringBuilder sb = new StringBuilder();
		toiSourceReaderImpl.sourceInitilizer();
		toiSourceReaderImpl.sourceReader();
		for(NewsStorie n : toiSourceReaderImpl.getNewsStories()) {
			sb.append(n.htmlString());
		}
		return hinduSourceReaderImpl.getNewsStories();
	}
	
	@RequestMapping("/prop")
	public String readProp() {
		System.out.println(appProperties.getHinduCssSelectors().size());
		System.out.println(appProperties.getHinduCssSelectors().get(0));
		System.out.println(appProperties.getHinduCssSelectors().get(1));
		return appProperties.getWebDriverParam();
	}

}
