package com.kordulup.ticketing.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kordulup.ticketing.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT DISTINCT(movie) FROM Projection p WHERE DATE(p.projectionDate) = DATE(:pDate)")
	List<Movie> findByProjectionsProjectionDate(@Param("pDate")LocalDate pDate);
	
	//@Query(nativeQuery = true, value = "SELECT movie, COUNT(*) FROM Projection GROUP BY movie LIMIT 3")
	List<Movie> findTop3DistinctByOrderByProjections();
	
}
