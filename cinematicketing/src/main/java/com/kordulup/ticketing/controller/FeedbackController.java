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
import com.kordulup.ticketing.services.FeedbackService;

@RestController
@RequestMapping("/api")
public class FeedbackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping("feedback/")
	public ResponseEntity<String> postFeedback(Principal user, @RequestBody Feedback feedback){
		LOGGER.info("Inside postFeedback(), saving feedback: " + feedback);
		Feedback savedFeedback = feedbackService.saveFeedback(user, feedback);
		return new ResponseEntity<>(savedFeedback.getUser().getUsername(), HttpStatus.ACCEPTED);
	}
	
}
