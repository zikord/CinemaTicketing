package com.kordulup.ticketing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kordulup.ticketing.entities.Feedback;
import com.kordulup.ticketing.repos.FeedbackRepository;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class FeedbackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@PostMapping("feedback/")
	public String postFeedback(@RequestBody Feedback feedback) {
		LOGGER.info("Inside postFeedback(), saving feedback: " + feedback);
		feedbackRepository.save(feedback);
		return "Thank you for your feedback!!";
	}
	
}
