package com.kordulup.ticketing.services;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kordulup.ticketing.entities.Feedback;
import com.kordulup.ticketing.entities.User;
import com.kordulup.ticketing.repos.FeedbackRepository;
import com.kordulup.ticketing.repos.UserRepository;

public class FeedbackServiceImpl implements FeedbackService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);


	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public Feedback saveFeedback(Principal user, Feedback feedback) {
		LOGGER.info("Inside saveFeedback(), saving feedback: " + feedback + user);
		User u = userRepository.findByUsername(user.getName()).orElse(null);
		feedback.setUser(u);
		Feedback savedFeedback = feedbackRepository.save(feedback);
		return savedFeedback;
	}
}
