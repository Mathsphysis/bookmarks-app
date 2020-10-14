package br.com.mpssolutions.bookmarkapp;

import java.util.List;

import br.com.mpssolutions.bookmarkapp.constants.KidFriendlyStatus;
import br.com.mpssolutions.bookmarkapp.constants.UserType;
import br.com.mpssolutions.bookmarkapp.controllers.BookmarkController;
import br.com.mpssolutions.bookmarkapp.entities.Bookmark;
import br.com.mpssolutions.bookmarkapp.entities.User;
import br.com.mpssolutions.bookmarkapp.partner.Shareable;

public class View {

	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println('\n' + user.getEmail() + " is browsing items.");

		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking
			
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
					}
				

				
				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					
					// Mark as kid-friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOW)) {
						String kidFriendlyStatus = getKidFriendlyDecision();
						if(kidFriendlyStatus != KidFriendlyStatus.UNKNOW) {
							BookmarkController.getInstance().setKidFriendlyStatus(kidFriendlyStatus, bookmark, user);
						}
					}
					
					// Sharing
					
					if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable) {
						boolean isShared = getShareDecision();
						if(isShared) {
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
		}
	}

	// TODO: Below methods simulate user input. After IO, we take input via console.
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;
	}

	private static String getKidFriendlyDecision() {
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: (Math.random() >= 0.4 && Math.random() < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOW;

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}
}
