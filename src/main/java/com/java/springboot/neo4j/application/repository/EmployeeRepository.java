package com.java.springboot.neo4j.application.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.springboot.neo4j.application.data.Employee;

/**
 * Employee Hierarchy repository with having CRUD operations
 * 
 * @author VinodSingh
 *
 */
@Repository
public interface EmployeeRepository extends Neo4jRepository<Employee, Long> {

	/**
	 * Find employee(s) by name This work for partial name search along with
	 * ignore cases
	 * 
	 * @param name
	 * @return
	 */
	List<Employee> findByNameContainsIgnoreCase(String name);

	/**
	 * Find all employee list from org's hierarchy
	 */
	List<Employee> findAll();

	/**
	 * Find an employee by empId
	 * 
	 * @param empId
	 * @return
	 */
	Employee findByEmpId(Long empId);

	/**
	 * Find employee(s) by location(city)
	 * 
	 * @param city
	 * @return
	 */
	List<Employee> findByCityContainsIgnoreCase(String city);

	/**
	 * Find employee(s) by gender
	 * 
	 * @param gender
	 * @return
	 */
	List<Employee> findByGenderIgnoreCase(String gender);

	/**
	 * Save an employee along with details
	 */
	@SuppressWarnings("unchecked")
	Employee save(Employee employee);

	/**
	 * Create relationship for an existing employee to another existing employee
	 * 
	 * @param empId
	 *            new employee
	 * @param toEmpId
	 *            supervisor
	 * @return
	 */
	@Query("" + "MATCH (e1:Employee) WITH e1 " + "MATCH (e2:Employee) where e1.empId = $empId AND e2.empId = $toEmpId "
			+ "CREATE (e1)-[r:REPORT_TO]->(e2) return type(r)")
	String createRelationship(@Param("empId") Long empId, @Param("toEmpId") Long toEmpId);

	/**
	 * Delete the employee and its relationship
	 * 
	 * @param empId
	 */
	@Query("" + "MATCH (e:Employee {empId:$empId}) " + "DETACH " + "DELETE e")
	void deleteEmployeeWithAllRelationship(@Param("empId") Long empId);

	
	/**
	 * Get the Hierarchy path between two employee
	 * 
	 * @param fromEmpId
	 * @param toEmpId
	 * @return 
	 */
	@Query("" + "MATCH path=shortestPath((e1:Employee{empId:$fromEmpId}) -[*]- (e2:Employee{empId:$toEmpId})) RETURN collect(path)")
	List<Employee> getHierarchyPathBtwEmployee(@Param("fromEmpId") Long fromEmpId, @Param("toEmpId") Long toEmpId);

	/**
	 * Graph traversal query to identify the direct reportees for a supervisor
	 * 
	 * @param empId
	 * @return
	 */
	@Query("" + "MATCH(e:Employee) -[r:REPORT_TO] -> (e1:Employee{empId:$empId}) RETURN e")
	List<Employee> getDirectReporteeOfAnEmpId(@Param("empId") Long empId);

	/**
	 * Graph traversal query to identify the indirect reportee
	 * 
	 * @param empId
	 * @return
	 */
	@Query("" + "MATCH(e:Employee)-[:REPORT_TO*2..]->(e1:Employee{empId:$empId}) RETURN e")
	List<Employee> getIndirectReporteeOfAnEmpId(@Param("empId") Long empId);

	/**
	 * Update the relationship of an employee
	 * 
	 * @param empId
	 * @param existing_supervisorid
	 * @param new_supervisorid
	 * @return
	 */
	@Query("" + "MATCH(e1)-[r1:REPORT_TO]->(e2), (e3) WHERE e1.empId = $empId AND e2.empId = $existing_supervisorid and e3.empId=$new_supervisorid CREATE (e1)-[r2:REPORT_TO]->(e3) SET r2=r1 DELETE r1")
	String updateHierarchyOfEmployee(@Param("empId") Long empId, @Param("existing_supervisorid") Long existing_supervisorid, @Param("new_supervisorid") Long new_supervisorid);
	
	
}