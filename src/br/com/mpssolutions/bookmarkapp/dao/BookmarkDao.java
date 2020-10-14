package br.com.mpssolutions.bookmarkapp.dao;

import java.util.List;

import br.com.mpssolutions.bookmarkapp.DataStore;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.UserBookmark;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
}
