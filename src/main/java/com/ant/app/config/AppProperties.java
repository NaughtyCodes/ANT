package com.ant.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class AppProperties {
	
	@Value("${app.phantomPath}")
	private String appPhantomPath;
	
	@Value("${app.webDriverParam}")
	private String webDriverParam;
	
	@Value("${app.src.hindu.url}")
	private String appSrcHinduUrl;
	
	@Value("${app.src.hindu.cssSelectors}") 
	private List<String> hinduCssSelectors;
	
	@Value("${app.src.toi.url}")
	private String appSrcToiUrl;

	@Value("${app.src.toi.cssSelectors}") 
	private List<String> toiCssSelectors;
	
	
	public String getAppSrcToiUrl() {
		return appSrcToiUrl;
	}

	public void setAppSrcToiUrl(String appSrcToiUrl) {
		this.appSrcToiUrl = appSrcToiUrl;
	}

	public List<String> getToiCssSelectors() {
		return toiCssSelectors;
	}

	public void setToiCssSelectors(List<String> toiCssSelectors) {
		this.toiCssSelectors = toiCssSelectors;
	}

	public List<String> getHinduCssSelectors() {
		return hinduCssSelectors;
	}

	public void setHinduCssSelectors(List<String> hinduCssSelectors) {
		this.hinduCssSelectors = hinduCssSelectors;
	}

	public String getAppPhantomPath() {
		return appPhantomPath;
	}

	public void setAppPhantomPath(String appPhantomPath) {
		this.appPhantomPath = appPhantomPath;
	}

	public String getWebDriverParam() {
		return webDriverParam;
	}

	public void setWebDriverParam(String webDriverParam) {
		this.webDriverParam = webDriverParam;
	}

	public String getAppSrcHinduUrl() {
		return appSrcHinduUrl;
	}

	public void setAppSrcHinduUrl(String appSrcHinduUrl) {
		this.appSrcHinduUrl = appSrcHinduUrl;
	}
	

}
