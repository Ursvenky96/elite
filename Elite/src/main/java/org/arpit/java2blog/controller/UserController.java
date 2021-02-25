package org.arpit.java2blog.controller;

import java.util.List;

import org.arpit.java2blog.exception.EliteException;
import org.arpit.java2blog.model.User;
import org.arpit.java2blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getUsers() {

		List<User> listOfUsers = userService.getAllUsers();
		return listOfUsers;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		try {
			userService.addUser(user);
		} catch (EliteException e) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("ErrorText", e.getMessage());
			return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>("Sucess", HttpStatus.ACCEPTED);

	}

	@RequestMapping("/getUserByMobilenumberAndPassword ")
	public ResponseEntity<?> getUserByMobilenumberAndPassword(@RequestParam("mobileNumber") String mobileNumber,
			@RequestParam("password") String password) {
		User user = null;
		try {
			user = userService.getUserByMobilenumberAndPassword(mobileNumber, password);

		} catch (EliteException e) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("ErrorText", e.getMessage());
			return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ResponseEntity<>(user.toString(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<?> forgetPassword(@RequestBody User user) {
		try {
			 userService.forgetPassword(user);
		} catch (EliteException e) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("ErrorText", e.getMessage());
			return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("ErrorText", e.getMessage());
			return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Sucess", HttpStatus.ACCEPTED);

	}

}
