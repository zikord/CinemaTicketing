package com.kordulup.ticketing.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Projection extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MOVIE_ID")
	@JsonBackReference
	private Movie movie;
	private LocalDate projectionDate;
	private LocalTime projectionTime;
	@OneToOne
	private Hall hall;
	private String dimension;
	@OneToMany(mappedBy="projection", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value="projection")
	List<Reservation> reservation;
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDate getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(LocalDate projectionDate) {
		this.projectionDate = projectionDate;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	public LocalTime getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(LocalTime projectionTime) {
		this.projectionTime = projectionTime;
	}

	@Override
	public String toString() {
		return "Projection [projectionDate=" + projectionDate + ", projectionTime="
				+ projectionTime + ", hall=" + hall + ", dimension=" + dimension + ", reservation=" + reservation + "]";
	}

	

}
