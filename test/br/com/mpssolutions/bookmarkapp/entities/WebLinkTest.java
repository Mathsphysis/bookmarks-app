package br.com.mpssolutions.bookmarkapp.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mpssolutions.bookmarkapp.managers.BookmarkManager;

class WebLinkTest {

	static WebLink webLink;

	@BeforeEach
	void reset() {
		webLink = null;
	}

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: porn in url -- false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/porn.html", "http://www.javaworld.com", "unknown");

		boolean actual = webLink.isKidFriendlyEligible();
		Assertions.assertFalse(actual, "For porn in url - isKidFriendlyEligible() should return false");

		// Test 2: porn in title -- false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Porn Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com", "unknown");

		actual = webLink.isKidFriendlyEligible();
		Assertions.assertFalse(actual, "For porn in title - isKidFriendlyEligible() should return false");

		// Test 3: adult in host -- false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.adultworld.com", "unknown");

		actual = webLink.isKidFriendlyEligible();
		Assertions.assertFalse(actual, "For adult in host - isKidFriendlyEligible() should return false");

		// Test 4: adult in url but not in host -- true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/adult.html", "http://www.javaworld.com", "unknown");

		actual = webLink.isKidFriendlyEligible();
		Assertions.assertTrue(actual, "For adult in url but not in host - isKidFriendlyEligible() should return true");

		// Test 5: adult in title only -- true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Adult Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com", "unknown");

		actual = webLink.isKidFriendlyEligible();
		Assertions.assertTrue(actual, "For adult in title only - isKidFriendlyEligible() should return true");
	}

}
