package com.kordulup.ticketing.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kordulup.ticketing.entities.Movie;
import com.kordulup.ticketing.repos.MovieRepository;

@RestController
@RequestMapping("/api")
public class MovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	MovieRepository movieRepository;

	@GetMapping("/movies")
	public List<Movie> getAllMovies() {
		LOGGER.info("Inside getAllMovies()");

		List<Movie> movies = new ArrayList<>();
		movieRepository.findAll().forEach(movies::add);
		return movies;
	}

	@PostMapping("/movie")
	public Movie saveMovie(@RequestBody Movie m) {
		LOGGER.info("Inside saveMovie(), saving movie:" + m);

		Movie movie = movieRepository.save(m);
		return movie;
	}

	@DeleteMapping("movie/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable("id") Long id) {
		LOGGER.info("Inside deleteMovie(), deleting movie with id:" + id);

		movieRepository.deleteById(id);
		return new ResponseEntity<>("Movie has been deleted!", HttpStatus.OK);
	}
	
	@GetMapping("movie/{id}")
	public Movie getMovieById(@PathVariable("id") Long id) {
		LOGGER.info("Inside getMovieById(), getting movie with id:" + id);
		
		return movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Movie with id: " + id + ", please try another id"));
	}
	
	
	@PutMapping("/movie/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie m) {
		LOGGER.info("Inside updateMovie(), updating movie with id:" + id);

		Optional<Movie> movieData = movieRepository.findById(id);

		if (movieData.isPresent()) {
			Movie movie = movieData.get();
			movie.setTitle(m.getTitle());
			movie.setDescription(m.getDescription());
			movie.setGenre(m.getGenre());
			movie.setDateOfRelease(m.getDateOfRelease());
			movie.setDuration(m.getDuration());
			movie.setDateOfFirstProjection(m.getDateOfFirstProjection());
			movie.setPoster(m.getPoster());
			
			LOGGER.info("Saving Movie: " + movie);
			
			return new ResponseEntity<>(movieRepository.save(movie), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/movies/date/{date}")
	public List<Movie> findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		LOGGER.info("Inside findByDate(): " + date);
		
		List<Movie> movies = movieRepository.findByProjectionsProjectionDate(date);
		LOGGER.info("Inside findByDate(): " + movies);
		return movies;
	}
}
