package com.kordulup.ticketing.entities;

public interface ReservationSeats {

	int getSeatNumber();

	ProjectionSummary getProjection();

	interface ProjectionSummary {
		Long getId();
	}
}
