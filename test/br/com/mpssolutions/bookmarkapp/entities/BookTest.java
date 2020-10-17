package br.com.mpssolutions.bookmarkapp.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mpssolutions.bookmarkapp.constants.BookGenre;
import br.com.mpssolutions.bookmarkapp.managers.BookmarkManager;

class BookTest {

	static Book book;

	@BeforeEach
	void reset() {
		book = null;
	}

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: Book genre is philosophy -- false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3, "unknown");

		boolean actual = book.isKidFriendlyEligible();
		Assertions.assertFalse(actual, "For book genre philosophy - isKidFriendly() should return false");

		// Test 2: Book genre is self help -- false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3, "unknown");

		actual = book.isKidFriendlyEligible();
		Assertions.assertFalse(actual, "For book genre self help - isKidFriendly() should return false");
	}

}
