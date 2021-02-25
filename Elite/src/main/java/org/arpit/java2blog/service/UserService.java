package org.arpit.java2blog.service;

import java.util.List;

import org.arpit.java2blog.model.User;

public interface UserService {

	public List<User> getAllUsers();

	public User addUser(User user);

	public User getUserByMobilenumberAndPassword(String mobileNumber, String password);

	public void forgetPassword(User user);

	

}
