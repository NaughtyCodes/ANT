package com.ant.app.controller;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.app.AntApplication;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AntApplication.class);
	
	@RequestMapping("/news/hindu/today")
	public List<NewsStorie> readHinduNewsToday() {
		logger.info("Service Start Time ==>"+new java.util.Date(System.currentTimeMillis()).toString());
		StringBuilder sb = new StringBuilder();
		hinduSourceReaderImpl.sourceInitilizer();
		hinduSourceReaderImpl.sourceReader();
		for(NewsStorie n : hinduSourceReaderImpl.getNewsStories()) {
			sb.append(n.htmlString());
		}
		logger.info("Service End Time ==>"+new java.util.Date(System.currentTimeMillis()).toString());
		return hinduSourceReaderImpl.getNewsStories();
	}
	
	@RequestMapping("/news/toi/today")
	public List<NewsStorie> readToiNewsToday() {
		logger.info("Service Start Time ==>"+new java.util.Date(System.currentTimeMillis()).toString());
		StringBuilder sb = new StringBuilder();
		toiSourceReaderImpl.sourceInitilizer();
		toiSourceReaderImpl.sourceReader();
		for(NewsStorie n : toiSourceReaderImpl.getNewsStories()) {
			sb.append(n.htmlString());
		}
		logger.info("Service End Time ==>"+new java.util.Date(System.currentTimeMillis()).toString());
		return toiSourceReaderImpl.getNewsStories();
	}
	
	@RequestMapping("/prop")
	public String readProp() {
		System.out.println(appProperties.getHinduCssSelectors().size());
		System.out.println(appProperties.getHinduCssSelectors().get(0));
		System.out.println(appProperties.getHinduCssSelectors().get(1));
		return appProperties.getWebDriverParam();
	}

}
