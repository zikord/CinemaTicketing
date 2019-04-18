package com.kordulup.ticketing.services;

import java.time.LocalDate;
import java.util.List;

import com.kordulup.ticketing.entities.Movie;

public interface MovieService {
	
	public List<Movie> pageableTopThreeMovies(Integer page);
	
	public List<Movie> findMovieByDate(LocalDate date);

}
