package br.com.mpssolutions.bookmarkapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.mpssolutions.bookmarkapp.DataStore;
import br.com.mpssolutions.bookmarkapp.entities.Book;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.Movie;
import br.com.mpssolutions.bookmarkapp.entities.UserBookmark;
import br.com.mpssolutions.bookmarkapp.entities.WebLink;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/bookmark_app_db?useSSL=false",
				"root", "root"); Statement stmt = conn.createStatement();) {
			if (userBookmark.getBookmark() instanceof Book) {
				saveUserBook(userBookmark, stmt);
			} else if (userBookmark.getBookmark() instanceof Movie) {
				saveUserMovie(userBookmark, stmt);
			} else {
				saveUserWebLink(userBookmark, stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saveUserWebLink(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into user_weblink (user_id, weblink_id) values (" + userBookmark.getUser().getId() + ", "
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);

	}

	private void saveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into user_movie (user_id, movie_id) values (" + userBookmark.getUser().getId() + ", "
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);

	}

	private void saveUserBook(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into user_book (user_id, book_id) values (" + userBookmark.getUser().getId() + ", "
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);

	}

	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks = bookmarks.get(0);

		for (Bookmark bookmark : allWebLinks) {
			result.add((WebLink) bookmark);
		}

		return result;
	}

	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
		List<WebLink> result = new ArrayList<>();
		List<WebLink> allWebLinks = getAllWebLinks();
		for (WebLink webLink : allWebLinks) {
			if (webLink.getDownloadStatus().equals(downloadStatus)) {
				result.add(webLink);
			}
		}

		return result;
	}

	public void updateKidFriendlyStatus(Bookmark bookmark) {
		int kidFriendlyStatus = bookmark.getKidFriendlyStatus().ordinal();
		long userId = bookmark.getKidFriendlyMarkedBy().getId();

		String tableToUpdate = "book";
		if (bookmark instanceof Movie) {
			tableToUpdate = "movie";
		} else if (bookmark instanceof WebLink) {
			tableToUpdate = "weblink";
		}

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/bookmark_app_db?useSSL=false",
				"root", "root"); Statement stmt = conn.createStatement();) {
			String query = "update " + tableToUpdate + " set kid_friendly_status = " + kidFriendlyStatus
					+ ", kid_friendly_marked_by = " + userId + " where id = " + bookmark.getId();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSharedBy(Bookmark bookmark) {
		long userId = bookmark.getSharedBy().getId();

		String tableToUpdate = "book";
		if (bookmark instanceof WebLink) {
			tableToUpdate = "weblink";
		}

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/bookmark_app_db?useSSL=false",
				"root", "root"); Statement stmt = conn.createStatement();) {
			String query = "update " + tableToUpdate + " set shared_by = " + userId + " where id = " + bookmark.getId();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
