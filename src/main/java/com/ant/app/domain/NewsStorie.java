package com.ant.app.domain;

public class NewsStorie {
	
	private String heading;
	private String message;
	private String image;
	
	public NewsStorie() {
		super();
	}

	public NewsStorie(String heading, String message, String image) {
		super();
		this.heading = heading;
		this.message = message;
		this.image = image;
	}
	
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "NewsStorie [heading=" + heading + ", message=" + message + ", image=" + image + "]";
	}
	
	public String htmlString() {
		return "<h3>"+this.getHeading()+"</h3><br />"+this.getMessage()+"<br /><br />";
	}

}
