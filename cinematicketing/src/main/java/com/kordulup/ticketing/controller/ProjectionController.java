package com.kordulup.ticketing.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.kordulup.ticketing.entities.Projection;
import com.kordulup.ticketing.repos.ProjectionRepository;

@RestController
@RequestMapping("/api/projection")
public class ProjectionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectionController.class);

	@Autowired
	ProjectionRepository projectionRepository;

	@GetMapping("/projections")
	public List<Projection> getAllProjections() {
		LOGGER.info("Inside getAllProjections()");

		List<Projection> projections = new ArrayList<>();
		projectionRepository.findAll().forEach(projections::add);
		return projections;
	}
	
	@GetMapping("/schedule")
	public List<Projection> getAllProjectionsByDate() {
		LOGGER.info("Inside getAllProjections()");

		List<Projection> projections = new ArrayList<>();
		projectionRepository.findAllByOrderByProjectionDateAsc().forEach(projections::add);
		return projections;
	}
	
	@PostMapping("/projection")
	public Projection saveProjection(@RequestBody Projection p) {
		LOGGER.info("Inside saveProjection(), saving projection: " + p);

		Projection projection = projectionRepository.save(p);
		return projection;
	}
	
	@DeleteMapping("/projection/{id}")
	public ResponseEntity<String> deleteProjection(@PathVariable("id") Long id){
		LOGGER.info("Inside deleteProjection(), deleting Projection with id: " + id);
		
		projectionRepository.deleteById(id);
		return new ResponseEntity<>("Projection has been deleted!", HttpStatus.OK);
	}
	
	@GetMapping("/projection/{id}")
    public Projection getProjectionById(@PathVariable("id") Long id) {
		LOGGER.info("Inside getProjectionById(), getting Projection with id: " + id);
		
		return projectionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No projection with id:" + id + ", please select another projection"));
	}
	
	@PutMapping("/projection/{id}")
	public ResponseEntity<Projection> updateProjection(@PathVariable("id") Long id, @RequestBody Projection p){
		LOGGER.info("Inside updateProjection(), updating projection with id: " + id);
		
		Optional<Projection> _projection = projectionRepository.findById(id);
		
		if(_projection.isPresent()) {
			Projection projection = _projection.get();
			projection.setMovie(p.getMovie());
			projection.setProjectionDate(p.getProjectionDate());
			projection.setHall(p.getHall());
			projection.setDimension(p.getDimension());
			
			LOGGER.info("Saving Projection: " + projection);
			return new ResponseEntity<>(projectionRepository.save(projection), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/projection/{title}")
	public List<Projection> findProjectionByMovie (@PathVariable("title") String title){
		LOGGER.info("Inside findProjectionByMovie(), finding by movie title: " + title);
		
		List<Projection> projections = projectionRepository.findByMovieTitle(title);
		
		return projections;
	}
}
