package com.kordulup.ticketing.controller;

import java.time.LocalDate;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.kordulup.ticketing.entities.Movie;
import com.kordulup.ticketing.entities.View;
import com.kordulup.ticketing.services.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	MovieService movieService;	
	
	@GetMapping("/date/{date}")
	public List<Movie> findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		LOGGER.info("Inside findByDate(): " + date);		
		List<Movie> movies = movieService.findMovieByDate(date);
		return movies;
	}
	
	@JsonView(View.Summary.class)
	@GetMapping("/top/{page}")
	public List<Movie> topMoviesList(@PathVariable("page") Integer page){
		LOGGER.info("Inside topMoviesList()");
		return movieService.pageableTopThreeMovies(page);
		
	}
}
