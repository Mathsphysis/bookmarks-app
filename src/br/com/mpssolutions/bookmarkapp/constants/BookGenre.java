package br.com.mpssolutions.bookmarkapp.constants;

public enum BookGenre {
	
	 BIOGRAPHY("biography"),
	 CHILDREN("children"),
	 FICTION("fiction"),
	 HISTORY("history"),
	 MYSTERY("mystery"),
	 PHILOSOPHY("philosophy"),
	 RELIGION("religion"),
	 ROMANCE("romance"),
	 SELF_HELP("self help"),
	 TECHNICAL("technical");
	
	private BookGenre(String name) {
		this.name = name;
	}
	
	private String name;
	public String getName() {
		return name;
	}
	
}
