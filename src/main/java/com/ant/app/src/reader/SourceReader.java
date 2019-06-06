package com.ant.app.src.reader;

import java.util.List;

import com.ant.app.domain.NewsStorie;

public interface SourceReader {
	
	void sourceInitilizer();
	void sourceReader();
	List<NewsStorie> getNewsStories();

}
