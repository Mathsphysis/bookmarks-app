package br.com.mpssolutions.bookmarkapp;

import java.util.ArrayList;
import java.util.List;

import br.com.mpssolutions.bookmarkapp.constants.Gender;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.User;
import br.com.mpssolutions.bookmarkapp.entities.UserBookmark;
import br.com.mpssolutions.bookmarkapp.managers.BookmarkManager;
import br.com.mpssolutions.bookmarkapp.managers.UserManager;
import br.com.mpssolutions.bookmarkapp.util.IOUtil;

public class DataStore {

	private static List<User> users = new ArrayList<>();
	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static List<UserBookmark> getUserBookmarks() {
		return userBookmarks;
	}

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadBooks();
		loadMovies();
	}

	private static void loadUsers() {

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User.txt");
		for (String row : data) {
			String[] values = row.split("\t");

			int gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.NOTINFORMED;
			}

			User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2],
					values[3], values[4], gender, values[6]);
			users.add(user);
		}
	}

	private static void loadWebLinks() {

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Web Link");
		List<Bookmark> bookmarkList = new ArrayList<>();

		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark webLink = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
					values[2], values[3], "unknown");
			bookmarkList.add(webLink);
		}
		
		bookmarks.add(bookmarkList);
	}

	private static void loadBooks() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book.txt");

		List<Bookmark> bookmarkList = new ArrayList<>();

		for (String row : data) {
			String[] values = row.split("\t");
			String[] authors = values[4].split(",");
			Bookmark book = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
					Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6]), "unknown");
			bookmarkList.add(book);
		}
		
		bookmarks.add(bookmarkList);
	}

	private static void loadMovies() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie.txt");

		List<Bookmark> bookmarkList = new ArrayList<>();

		for (String row : data) {
			String[] values = row.split("\t");
			String[] directors = values[4].split(",");
			String[] cast = values[3].split(",");
			Bookmark movie = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1],
					Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6]), "unknown");
			bookmarkList.add(movie);
		}
		
		bookmarks.add(bookmarkList);
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}

}
