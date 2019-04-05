package com.kordulup.ticketing.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Movie extends AbstractEntity{

	private String title;
	private String description;
	private String genre;
	private Date dateOfRelease;
	private Integer duration;
	private Date dateOfFirstProjection;
	private String poster;
	@OneToMany(mappedBy="movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Projection> projections = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getDateOfFirstProjection() {
		return dateOfFirstProjection;
	}

	public void setDateOfFirstProjection(Date dateOfFirstProjection) {
		this.dateOfFirstProjection = dateOfFirstProjection;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public void setProjections(List<Projection> projections) {
		this.projections = projections;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", description=" + description + ", genre=" + genre + ", dateOfRelease="
				+ dateOfRelease + ", duration=" + duration + ", dateOfFirstProjection=" + dateOfFirstProjection
				+ ", poster=" + poster + "]";
	}

}
