package models;
// This Node2 type is for requirement 4.2
public class Node2 {
	private String url;
	private int fileSize;
	private int numOfOutlinks;
	private String contentType;
	
	// constructor
	public Node2(String url, int fileSize, int numOfOutlinks, String contentType) {
		this.url = url;
		this.fileSize = fileSize;
		this.numOfOutlinks = numOfOutlinks;
		this.contentType = contentType;
	}
	
	// toString
	public String toString() {
		return url + "," + fileSize + "," + numOfOutlinks + "," + contentType;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public int getNumOfOutlinks() {
		return numOfOutlinks;
	}
	public void setNumOfOutlinks(int numOfOutlinks) {
		this.numOfOutlinks = numOfOutlinks;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
