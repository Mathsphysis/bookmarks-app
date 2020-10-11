package br.com.mpssoulutions.bookmarkapp.managers;

import br.com.mpssolutions.bookmarkapp.dao.BookmarkDao;
import br.com.mpssolutions.bookmarkapp.entities.Book;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.Movie;
import br.com.mpssolutions.bookmarkapp.entities.WebLink;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();

	private static BookmarkDao dao = new BookmarkDao();
	
	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, int releaseYear, String[] cast, String[] directors, String genre,
			double imdbRating, String profileUrl) {
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

	public WebLink createWebLink(long id, String title, String profileUrl, String host, String url) {
		WebLink webLink = new WebLink();
		webLink.setHost(host);
		webLink.setId(id);
		webLink.setProfileUrl(profileUrl);
		webLink.setTitle(title);
		webLink.setUrl(url);
		
		return webLink;
	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, String genre, 
			double amazonRating,  String profileUrl) {
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
	
	public Bookmark[][] getBookmarks(){
		return dao.getBookmarks();
	}
}
