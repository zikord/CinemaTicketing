package com.kordulup.ticketing.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Movie extends AbstractEntity{

	@JsonView(View.Summary.class)
	private String title;
	@JsonView(View.Summary.class)
	private String description;
	@JsonView(View.Summary.class)
	private String genre;
	@JsonView(View.Summary.class)
	private Date dateOfRelease;
	@JsonView(View.Summary.class)
	private Integer duration;
	@JsonView(View.Summary.class)
	private Date dateOfFirstProjection;
	@JsonView(View.Summary.class)
	private String poster;
	@OneToMany(mappedBy="movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@OrderBy("projectionTime")
	private List<Projection> projections;

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
