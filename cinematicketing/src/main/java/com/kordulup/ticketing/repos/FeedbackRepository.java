package com.kordulup.ticketing.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kordulup.ticketing.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
