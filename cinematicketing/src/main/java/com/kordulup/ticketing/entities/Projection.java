package com.kordulup.ticketing.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

	@ManyToOne
	@JoinColumn(name="MOVIE_ID", nullable=false)
	@JsonBackReference
	private Movie movie;
	private LocalDateTime projectionDate;
	@OneToOne
	private Hall hall;
	private String dimension;
	@OneToMany(mappedBy="projection", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference(value="projection")
	List<Reservation> reservation = new ArrayList<>();
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(LocalDateTime projectionDate) {
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

	@Override
	public String toString() {
		return "Projection [movie=" + movie + ", projectionDate=" + projectionDate + ", hall=" + hall + ", dimension="
				+ dimension + "]";
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	

}
