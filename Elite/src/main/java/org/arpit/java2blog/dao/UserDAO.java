package org.arpit.java2blog.dao;

import java.util.List;

import org.arpit.java2blog.model.User;

public interface UserDAO {

	public List<User> getAllUsers();

	public User getUser(int id);

	public User addUser(User user);

	public User getUserByMobilenumberAndPassword(String mobileNumber, String password);

	public User getUserByMobilenumberAndPassword(String mobileNumber);

	public void forgetPassword(User user);

}
