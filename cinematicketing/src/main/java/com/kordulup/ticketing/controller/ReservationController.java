package com.kordulup.ticketing.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kordulup.ticketing.entities.Reservation;
import com.kordulup.ticketing.entities.ReservationSeats;
import com.kordulup.ticketing.repos.CustomerRepository;
import com.kordulup.ticketing.repos.ReservationRepository;
import com.kordulup.ticketing.services.ReservationService;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ReservationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/reservation/seats/{id}")
	public List<ReservationSeats> getSeats(@PathVariable Long id){
		LOGGER.info("Inside getSeats(), find by reservation id: " + id);		
		List<ReservationSeats> seats = reservationRepository.findByProjectionId(id);		
		return seats;				
	}
	
	@GetMapping("/reservation/{id}")
	public Optional<Reservation> getById(@PathVariable Long id){
		LOGGER.info("Inside getById(), find by reservation id: " + id);		
		Optional<Reservation> reservation = reservationRepository.findById(id);		
		return reservation;				
	}
	
	
	@PostMapping("/reservation/")
	public ResponseEntity<String> saveReservation(@RequestBody Reservation reservation) {
		LOGGER.info("Inside saveReservation()" + reservation);
		Reservation res = reservationService.bookProjection(reservation);
		String email = res.getCustomer().getEmail();
		return new ResponseEntity<>("Reservation completed successfully, we sent you a ticket itinerary to " + email + ".", HttpStatus.ACCEPTED);
	}
}
