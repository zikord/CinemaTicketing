package com.kordulup.ticketing.services;



import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kordulup.ticketing.entities.Projection;
import com.kordulup.ticketing.entities.Reservation;
import com.kordulup.ticketing.entities.ReservationSeats;
import com.kordulup.ticketing.repos.CustomerRepository;
import com.kordulup.ticketing.repos.ProjectionRepository;
import com.kordulup.ticketing.repos.ReservationRepository;
import com.kordulup.ticketing.util.EmailUtil;
import com.kordulup.ticketing.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.kordulup.ticketing.itinerary.dirpath}")
	private String ITINERARY_DIR;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProjectionRepository projectionRepository;
	
	@Autowired
	private PDFGenerator pdfGenerator;

	@Autowired
	private EmailUtil emailUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	public Reservation bookProjection(Reservation reservation) {
		LOGGER.info("Inside bookProjection()");
		
		Projection projection = projectionRepository.findById(reservation.getProjection().getId()).orElseThrow(() -> new EntityNotFoundException());
		
		reservation.setProjection(projection);

		customerRepository.save(reservation.getCustomer());
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		LOGGER.info("Generating the itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emailing the itinerary");
		emailUtil.sendItinerary(savedReservation.getCustomer().getEmail(), filePath);

		return reservation;
	}

	@Override
	public Reservation findReservationById(Long id) {
		Reservation reservation = reservationRepository.findById(id).orElse(null);
		return reservation;
	}

	@Override
	public List<ReservationSeats> findByProjectionId(Long id) {
		List<ReservationSeats> reservations = reservationRepository.findByProjectionId(id);
		return reservations;
	}
	
	

}
