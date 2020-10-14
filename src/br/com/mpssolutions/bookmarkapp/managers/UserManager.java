package br.com.mpssolutions.bookmarkapp.managers;

import java.util.List;

import br.com.mpssolutions.bookmarkapp.dao.UserDao;
import br.com.mpssolutions.bookmarkapp.entities.User;

/*
 * Singleton pattern class, ensures singleton instantiation by assigning a static field of UserManager 
 * class and instantiating it using a private constructor
 */
public class UserManager {
	
	// As soon as the class loads, the single object will be instantiated.
	private static UserManager instance = new UserManager(); 
	
	private static UserDao dao = new UserDao();
	
	private UserManager() {
	}

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(long id, String email, String password, String firstName, String lastName,  int gender,
			String userType) {
		User user= new User();
		user.setId(id);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setGender(gender);
		user.setLastName(lastName);
		user.setUserType(userType);
		user.setLastName(lastName);
		
		return user;
	}
	
	public List<User> getUsers() {
		return dao.getUsers();
	}
}
