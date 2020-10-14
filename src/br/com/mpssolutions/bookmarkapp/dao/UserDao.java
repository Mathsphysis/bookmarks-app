package br.com.mpssolutions.bookmarkapp.dao;

import java.util.List;

import br.com.mpssolutions.bookmarkapp.DataStore;
import br.com.mpssolutions.bookmarkapp.entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
