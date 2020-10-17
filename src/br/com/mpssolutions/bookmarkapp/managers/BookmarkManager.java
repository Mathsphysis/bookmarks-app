package br.com.mpssolutions.bookmarkapp.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import br.com.mpssolutions.bookmarkapp.constants.BookGenre;
import br.com.mpssolutions.bookmarkapp.constants.KidFriendlyStatus;
import br.com.mpssolutions.bookmarkapp.constants.MovieGenre;
import br.com.mpssolutions.bookmarkapp.dao.BookmarkDao;
import br.com.mpssolutions.bookmarkapp.entities.Book;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.Movie;
import br.com.mpssolutions.bookmarkapp.entities.User;
import br.com.mpssolutions.bookmarkapp.entities.UserBookmark;
import br.com.mpssolutions.bookmarkapp.entities.WebLink;
import br.com.mpssolutions.bookmarkapp.partner.Shareable;
import br.com.mpssolutions.bookmarkapp.util.HttpConnect;
import br.com.mpssolutions.bookmarkapp.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();

	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, int releaseYear, String[] cast, String[] directors,
			MovieGenre genre, double imdbRating, String profileUrl) {
		Movie movie = new Movie();
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setId(id);
		movie.setImdbRating(imdbRating);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setTitle(title);

		return movie;
	}

	public WebLink createWebLink(long id, String title, String url, String host, String profileUrl) {
		WebLink webLink = new WebLink();
		webLink.setHost(host);
		webLink.setId(id);
		webLink.setProfileUrl(profileUrl);
		webLink.setTitle(title);
		webLink.setUrl(url);

		return webLink;
	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors,
			BookGenre genre, double amazonRating, String profileUrl) {
		Book book = new Book();
		book.setAmazonRating(amazonRating);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setId(id);
		book.setProfileUrl(profileUrl);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setTitle(title);

		return book;
	}

	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);

		if (bookmark instanceof WebLink) {
			try {
				String url = ((WebLink) bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(url);
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark, User user) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);

		dao.updateKidFriendlyStatus(bookmark);
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);

		dao.updateSharedBy(bookmark);
		System.out.println(((Shareable) bookmark).getItemData());

	}
}
