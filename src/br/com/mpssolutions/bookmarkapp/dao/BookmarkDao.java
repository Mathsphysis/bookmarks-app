package br.com.mpssolutions.bookmarkapp.dao;

import br.com.mpssolutions.bookmarkapp.DataStore;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}
}
