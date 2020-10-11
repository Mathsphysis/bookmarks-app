package br.com.mpssolutions.bookmarkapp.entities;

public class UserBookmark {
	private Bookmark bookmark;
	private User user;

	public Bookmark getBookmark() {
		return bookmark;
	}

	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
