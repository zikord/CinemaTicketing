package com.kordulup.ticketing.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kordulup.ticketing.entities.Projection;

public interface ProjectionRepository extends JpaRepository<Projection, Long> {

	List<Projection> findAllByOrderByProjectionDateAsc();
	
	List<Projection> findByMovieTitle(String title);
}
