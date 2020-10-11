package br.com.mpssolutions.bookmarkapp;

import java.util.Arrays;

import br.com.mpssolutions.bookmarkapp.constants.KidFriendlyStatus;
import br.com.mpssolutions.bookmarkapp.constants.UserType;
import br.com.mpssolutions.bookmarkapp.controllers.BookmarkController;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.User;

public class View {

	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println('\n' + user.getEmail() + " is browsing items.");

		int bookmarkCount = 0;

		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking
				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						bookmarkCount++;
					}
				}

				// Mark as kid-friendly
				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOW)) {
						String kidFriendlyStatus = getKidFriendlyDecision();
						if(kidFriendlyStatus != KidFriendlyStatus.UNKNOW) {
							bookmark.setKidFriendlyStatus(kidFriendlyStatus);
							System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", " + bookmark.getTitle());
						}
					}
				}
			}
		}
	}

	private static String getKidFriendlyDecision() {
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: (Math.random() >= 0.4 && Math.random() < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOW;

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}
}
