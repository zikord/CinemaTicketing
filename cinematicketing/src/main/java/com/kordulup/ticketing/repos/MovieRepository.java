package com.kordulup.ticketing.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kordulup.ticketing.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT DISTINCT m FROM Movie m JOIN FETCH m.projections p WHERE DATE(p.projectionDate) = DATE(:pDate)")
	List<Movie> findByProjectionsProjectionDate(@Param("pDate")LocalDate date);
	
	
	@Query("SELECT movie FROM Projection GROUP BY movie_id ORDER BY COUNT(*) DESC")
	List<Movie> somequery(Pageable pageable);
	
}
