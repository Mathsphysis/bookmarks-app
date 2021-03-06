package br.com.mpssolutions.bookmarkapp.controllers;

import br.com.mpssolutions.bookmarkapp.constants.KidFriendlyStatus;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.User;
import br.com.mpssolutions.bookmarkapp.managers.BookmarkManager;

public class BookmarkController {
	public static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);

	}

	public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark, User user) {
		BookmarkManager.getInstance().setKidFriendlyStatus(kidFriendlyStatus, bookmark, user);
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().share(user, bookmark);

	}

}
