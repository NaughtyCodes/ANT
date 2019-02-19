package com.ant.app.utils;

import java.util.List;

import com.ant.app.domain.NewsStorie;

public interface SourceReader {
	
	void sourceInitilizer();
	void sourceReader();
	List<NewsStorie> getNewsStories();

}
