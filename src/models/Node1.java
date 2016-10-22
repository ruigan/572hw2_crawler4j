package models;

public class Node1 {
	private String url;
	private int statusCode;
	
	public Node1(String url, int statusCode) {
		this.url = url;
		this.statusCode = statusCode;
	}
	
	public String toString() {
		return url + "," + statusCode;
	}
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
