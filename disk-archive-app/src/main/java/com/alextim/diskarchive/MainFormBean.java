package com.alextim.diskarchive;

public class MainFormBean {
	public String fullName = "Aleksey Timofeev";
	public String shortName = "ALEXEY.T";
	public String version = "1.2.2";
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String toString() {
		return String.format("%s %s (%s)", new Object[]{shortName, fullName, version}); 
	}
}
