package com.kordulup.ticketing.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Feedback extends AbstractEntity {

	private String feedbackText;
	private Integer rating;
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	@JsonBackReference
	private User user;

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackText=" + feedbackText + ", rating=" + rating + "]";
	}
	
	
}
