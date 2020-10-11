package br.com.mpssolutions.bookmarkapp;

import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.User;
import br.com.mpssolutions.bookmarkapp.managers.BookmarkManager;
import br.com.mpssolutions.bookmarkapp.managers.UserManager;

public class Launch {
	
	private static User[] users;
	private static Bookmark[][] bookmarks;
	
	private static void loadData() {
		System.out.println("1. Loading Data...");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
		
		System.out.println("2. Data Loaded.");
		
		System.out.println("Printing data ...%n");
		
		printUserData();
		printBookmarkData();
	}
	
	private static void printUserData() {
		for(User user : users) {
			System.out.println(user);
		}
		
	}
	
	private static void printBookmarkData() {
		for(Bookmark[] bookmarkList : bookmarks) {
			for(Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
		
	}
	
	public static void main(String[] args) {
		loadData();
	}	
}
