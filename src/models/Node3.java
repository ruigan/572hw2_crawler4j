package models;

public class Node3 {
	private String url;
	private boolean inSite;
	
	public Node3(String url, boolean inSite) {
		this.url = url;
		this.inSite = inSite;
	}
	
	public String toString() {
		String str = "";
		if (inSite == true) {
			str = "OK";
		} else {
			str = "N_OK";
		}
		return url + "," + str;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isInSite() {
		return inSite;
	}
	public void setInSite(boolean inSite) {
		this.inSite = inSite;
	}
	
	
}
