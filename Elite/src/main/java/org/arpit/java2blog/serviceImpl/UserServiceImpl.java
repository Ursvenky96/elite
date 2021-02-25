package org.arpit.java2blog.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.arpit.java2blog.dao.UserDAO;
import org.arpit.java2blog.exception.EliteException;
import org.arpit.java2blog.exception.ErrorCode;
import org.arpit.java2blog.model.User;
import org.arpit.java2blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired

	UserDAO userDao;

	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Transactional
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Transactional
	public User addUser(User user) {
		User userDetails = userDao.getUserByMobilenumberAndPassword(user.getMobileNumber());
		if(userDetails!=null) {
			throw new EliteException("user Already Registered",new ErrorCode(404));
		}
		return userDao.addUser(user);
	}

	@Transactional
	public User getUserByMobilenumberAndPassword(String mobileNumber, String password) {
		// TODO Auto-generated method stub
		User userDetails = userDao.getUserByMobilenumberAndPassword(mobileNumber, password);
		if(userDetails==null) {
			throw new EliteException("incorrect password or user Not Registered",new ErrorCode(404));
		}
		return userDetails;
	}

	@Transactional
	public void forgetPassword(User user) {
		User userDetails = userDao.getUserByMobilenumberAndPassword(user.getMobileNumber());
		if(userDetails==null) {
			throw new EliteException("Please Register User using mobileNumber",new ErrorCode(404));
		}
		userDetails.setPassword(user.getPassword());
		userDao.forgetPassword(userDetails);
		
		
	}

}
