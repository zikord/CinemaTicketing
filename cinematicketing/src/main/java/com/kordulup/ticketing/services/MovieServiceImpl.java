package com.kordulup.ticketing.services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kordulup.ticketing.controller.MovieController;
import com.kordulup.ticketing.entities.Movie;
import com.kordulup.ticketing.repos.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	MovieRepository movieRepository;
		
	@Override
	public List<Movie> pageableTopThreeMovies(Integer page) {
		LOGGER.info("Inside pageableTopThreeMovies()");
		Pageable pageable = PageRequest.of(page, 3);
		List<Movie> topMovies = movieRepository.somequery(pageable);		
		return topMovies;
	}

	@Override
	public List<Movie> findMovieByDate(LocalDate date) {
		LOGGER.info("Inside findMovieByDate()" + date);
		List<Movie> movies = movieRepository.findByProjectionsProjectionDate(date);
		return movies;
	}

}
