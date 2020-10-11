package br.com.mpssolutions.bookmarkapp.entities;

import java.util.Arrays;

public class WebLink extends Bookmark {
	private String url;
	private String host;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return String.format("Web Link ID: %d%nTitle: %s%nURL: %s%nHost: %s%nProfile URL: %s%n%n", this.getId(),
				this.getTitle(), url, host, this.getProfileUrl());
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if(url.toLowerCase().contains("porn") || this.getTitle().toLowerCase().contains("porn") || host.toLowerCase().contains("adult")) return false;
		
		return true;
	}
}
