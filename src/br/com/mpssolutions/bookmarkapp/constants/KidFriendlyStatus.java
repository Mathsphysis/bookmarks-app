package br.com.mpssolutions.bookmarkapp.constants;

public enum KidFriendlyStatus {
	
	APPROVED("approved"),
	REJECTED("rejected"),
	UNKNOW("unknow");
	
	private KidFriendlyStatus(String name) {
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
}
