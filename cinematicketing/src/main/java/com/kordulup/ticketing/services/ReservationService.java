package com.kordulup.ticketing.services;

import java.util.List;

import com.kordulup.ticketing.entities.Reservation;
import com.kordulup.ticketing.entities.ReservationSeats;

public interface ReservationService {

	public Reservation bookProjection(Reservation reservation);
	
	public Reservation findReservationById(Long id);
	
	public List<ReservationSeats> findByProjectionId(Long id);
}
