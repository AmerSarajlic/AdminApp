package com.testproject.dao;

import com.testproject.data.User;

public interface UserDAO {

	public void createUser(User user);
	public void deleteUser(int id);
	public User findUser(String userName);
	public void updateUserPassword(int id);
	public void displayAllUsers();
	
}
