package com.kordulup.ticketing.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kordulup.ticketing.entities.Reservation;
import com.kordulup.ticketing.entities.ReservationSeats;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query
	List<ReservationSeats> findByProjectionId(Long projectionId);
	
}
