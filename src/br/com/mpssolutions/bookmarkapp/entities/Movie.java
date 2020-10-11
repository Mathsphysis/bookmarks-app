package br.com.mpssolutions.bookmarkapp.entities;

import java.util.Arrays;

public class Movie extends Bookmark {
	private int releaseYear;
	private String[] cast;
	private String[] directors;
	private String genre;
	private double imdbRating;

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String[] getCast() {
		return cast;
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
	
	@Override
	public String toString() {
		return String.format("Movie ID: %s%nTitle: %s%nRelease Year: %d%nCast: %s%nDiretor(s): %s%n"
				+ "Genre: %s%nIMDB Rating: %.1f%nProfile URL: %s%n%n", this.getId(), this.getTitle(), releaseYear,
				Arrays.deepToString(cast), Arrays.deepToString(directors), genre, imdbRating, this.getProfileUrl());
	}

}
