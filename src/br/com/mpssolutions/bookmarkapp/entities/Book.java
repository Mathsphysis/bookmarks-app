package br.com.mpssolutions.bookmarkapp.entities;

import java.util.Arrays;

public class Book extends Bookmark {
	private int publicationYear;
	private String publisher;
	private String[] authors;
	private String genre;
	private double amazonRating;

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getAmazonRating() {
		return amazonRating;
	}

	public void setAmazonRating(double amazonRating) {
		this.amazonRating = amazonRating;
	}
	
	@Override
	public String toString() {
		return String.format("Book ID: %s%nTitle: %s%nPublication Year: %d%nAuthor(s): %s%n"
				+ "Genre: %s%nAmazon Rating: %.1f%nProfile URL: %s%n%n", this.getId(), this.getTitle(), publicationYear,
				Arrays.deepToString(authors), genre, amazonRating, this.getProfileUrl());
	}

}
