package com.kordulup.ticketing.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kordulup.ticketing.entities.Feedback;
import com.kordulup.ticketing.entities.User;
import com.kordulup.ticketing.repos.FeedbackRepository;
import com.kordulup.ticketing.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class FeedbackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("feedback/")
	public ResponseEntity<String> postFeedback(Principal user, @RequestBody Feedback feedback){
		LOGGER.info("Inside postFeedback(), saving feedback: " + feedback);
		User u = userRepository.findByUsername(user.getName()).orElse(null);
		feedback.setUser(u);
		feedbackRepository.save(feedback);
		return new ResponseEntity<>(u.getUsername(), HttpStatus.ACCEPTED);
	}
	
}
