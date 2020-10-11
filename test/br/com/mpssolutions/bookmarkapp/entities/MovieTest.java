package br.com.mpssolutions.bookmarkapp.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mpssolutions.bookmarkapp.constants.MovieGenre;
import br.com.mpssolutions.bookmarkapp.managers.BookmarkManager;

class MovieTest {

	static Movie movie;

	@BeforeEach
	void reset() {
		movie = null;
	}

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: Movie genre is horror -- false
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5, "unknown");
		boolean actual = movie.isKidFriendlyEligible();
		Assertions.assertFalse(actual, "For movie genre horror - isKidFriendlyEligible() should return false");

		// Test 2: Movie genre is thriller -- false
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5, "unknown");
		actual = movie.isKidFriendlyEligible();
		Assertions.assertFalse(actual, "For movie genre thriller - isKidFriendlyEligible() should return false");

		// Test 3: Movie genre is foreign thriller -- false
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" },
				MovieGenre.FOREGIN_THRILLERS, 8.5, "unknown");
		actual = movie.isKidFriendlyEligible();
		Assertions.assertFalse(actual,
				"For movie genre foreign thriller - isKidFriendlyEligible() should return false");

	}

}
