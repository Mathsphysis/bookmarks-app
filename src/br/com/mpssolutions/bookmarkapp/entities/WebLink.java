package br.com.mpssolutions.bookmarkapp.entities;

import br.com.mpssolutions.bookmarkapp.partner.Shareable;

public class WebLink extends Bookmark implements Shareable {
	
	private String url;
	private String host;
	private String htmlPage;
	private DownloadStatus downloadStatus = DownloadStatus.NOT_ATTEMPTED;

	public enum DownloadStatus {
		NOT_ATTEMPTED,
		SUCCESS,
		FAILED,
		NOT_ELIGIBLE;
	}

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
		if (url.toLowerCase().contains("porn") || this.getTitle().toLowerCase().contains("porn")
				|| host.toLowerCase().contains("adult"))
			return false;

		return true;
	}

	@Override
	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
			builder.append("<type>WebLink</type>");
			builder.append("<title>").append(getTitle()).append("</title>");
			builder.append("<url>").append(url).append("</url>");
			builder.append("<host>").append(host).append("</host>");
		builder.append("</item>");
		return builder.toString();
	}

	public String getHtmlPage() {
		return htmlPage;
	}

	public void setHtmlPage(String htmlPage) {
		this.htmlPage = htmlPage;
	}

	public DownloadStatus getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(DownloadStatus downloadStatus) {
		this.downloadStatus = downloadStatus;
	}
}
