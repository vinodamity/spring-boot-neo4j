package com.impetus.bigdata.rtb.application.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.impetus.bigdata.rtb.application.data.Employee;

@Repository
public interface EmployeeRepository extends Neo4jRepository<Employee, Long> {

	//@Query("MATCH (e:Employee) WHERE e.name CONTAINS $name RETURN e")
	List<Employee> findByName(String name);
	
	//@Query("MATCH (e:Employee) RETURN e")
    List<Employee> findAll();
	
	/*@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Employee> findSearchResultsByEmpId(@Param("name") String name);
	
	@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Employee> findSearchResultsForDirectReportee(@Param("name") String name);
	
	@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Employee> findSearchResultsForAllHierarchy(@Param("name") String name);
	
	@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Employee> findSearchResultsByImmediateSupervisor(@Param("name") String name);
	
	@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Employee> findSearchResultsToTopHeierarchy(@Param("name") String name);*/
	
	/*@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Employee> findSearchResults(@Param("name") String name);
	
	@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Employee> findSearchResults(@Param("name") String name);*/
}