package com.kordulup.ticketing.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kordulup.ticketing.controller.UserController;
import com.kordulup.ticketing.entities.User;
import com.kordulup.ticketing.repos.UserRepository;

@RestController
@RequestMapping("/userApi")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;


	@GetMapping("/users")
	public List<User> getAllUsers() {
		LOGGER.info("Inside getAllUsers()");
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
		LOGGER.info("Inside deleteUser(), deleting user with id: " + id);
		userRepository.deleteById(id);
		return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
	}


}