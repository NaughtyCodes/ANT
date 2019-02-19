package com.ant.app.controller;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.app.domain.NewsStorie;
import com.ant.app.utils.UrlSourceReader;

@RestController
@RequestMapping("/call")
public class AntController {

	@RequestMapping("/news/today")
	public List<NewsStorie> readNewsToday() {
		
		UrlSourceReader sourceReader = new UrlSourceReader();
		StringBuilder sb = new StringBuilder();
		sourceReader.sourceInitilizer();
		sourceReader.sourceReader();
		for(NewsStorie n : sourceReader.getNewsStories()) {
			sb.append(n.htmlString());
		}
		return sourceReader.getNewsStories();
	}

}
