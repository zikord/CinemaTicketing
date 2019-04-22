package com.kordulup.ticketing.services;

import java.security.Principal;

import com.kordulup.ticketing.entities.Feedback;

public interface FeedbackService {

	public Feedback saveFeedback(Principal user, Feedback feedback);
	
}
