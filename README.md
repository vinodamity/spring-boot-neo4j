# spring-boot-neo4j
spring-boot-neo4j project is about to Spring Boot integration with Neo4j with CRUD operations

## Table of Contents
	1.[Prerequisites](#prerequisites)
	2.[Spring Boot Project Set Up](#spring-Boot-Project-Set-Up)
	3.[spring-boot-starter-parent dependency version](#spring-boot-starter-parent dependency version)
	4.[Other dependencies used for this project](#Other dependencies used for this project)
	5.[Neo4j Database Set Up](#Neo4j Database Set Up)
	6.[Prepare sample database queries for Org's Hierarchy](#Prepare sample database queries for Org's Hierarchy)
	7.[Introduction of Cypher Query Examples](#Introduction of Cypher Query Examples)
	8.[Rest resources details and description](#Rest resources details and description)
	9.[Spring Boot Implementation](#Spring Boot Implementation)
	
	
## Prerequisites
In addition to the software listed below you need the basic knowledge of its uses and Spring Boot framework
- Eclipse (or any Java IDE)
- Maven 
- Neo4j Desktop Setup 1.4.12
- Java 8 (tested) and we can use higher versions (not tested)
- Swagger


## Spring Boot Project Set Up
To create a Spring boot project, need to visit - https://start.spring.io/ and create the project skeleton with available options and dependencies. Once you download the generated project, unzip it and import from Eclipse.

## spring-boot-starter-parent dependency version
Using the version-2.6.3 as below

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.3</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

## Other dependencies used for this project
Below dependencies are required:

	<!-- Spring data neo4j dependency used for data access from Neo4j DB-->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-neo4j</artifactId>
	</dependency>
	
	<!-- Spring Rest support-->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	
	<!-- Spring dev tool support-->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
	</dependency>
	
	<!-- Spring testing support-->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
	
	<!-- Enable Swagger -->
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger2</artifactId>
		<version>2.6.1</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger-ui</artifactId>
		<version>2.6.1</version>
	</dependency>

## Neo4j Database Set Up
Visit the page - https://neo4j.com/download/ for download of Neo4j Desktop latest version.<br />

Follow the steps provided there for complete installation of Neo4j. After installation, open Neo4j to create an employee database. By default, this provided Movie database to play and fun with Cypher query.

## Prepare sample database queries for Org's Hierarchy
Follow the steps provided in https://neo4j.com/developer/neo4j-desktop/ ( Courtesy of - Neo4j web resources) to create a new project and database. By following these steps created a new database - "employee" with below sample queries<br />

CREATE (e1:Employee{name: "Ramesh Singh", empId: 4101, city: "Noida", gender: "female", skill: "Cloud & Data Engineering", designation: "Senior Director Of Engineering"}),<br />
(e2:Employee{name: "Vinod Dubey", empId: 4102, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation:"Senior Technical Architect"}),<br />
(e3:Employee{name: "Abhishek Tripathi", empId: 4103, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Senior Technical Manager"}),<br />
(e4:Employee{name: "Abhay Kumar", empId: 4104, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation:"Senior Manager Software Enginnering"}),<br />
(e5:Employee{name: "Nishi Dubey", empId: 4105, city: "Bengaluru", gender: "female", skill: "Cloud & Data Engineering", designation: "Senior Manager Software Enginnering"}),<br />
(e6:Employee{name: "Manoj Vashitha", empId: 4106, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Senior Manager Software Enginnering"}),<br />
(e7:Employee{name: "Sandeep Kumar", empId: 4107, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Lead Software Engineer"}),<br />
(e8:Employee{name: "Surendra Singh", empId: 4108, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Senior Lead Software Engineer"}),<br />
(e9:Employee{name: "Vinod Kumar Singh", empId: 4109, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Lead Software Engineer"}),<br />
(e10:Employee{name: "Vishal Kapoor", empId: 4110, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Senior Lead Software Engineer"}),<br />
(e11:Employee{name: "Sanjay Singh Verma", empId: 4111, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Lead Software Engineer"}),<br />
(e12:Employee{name: "Gaurav Kumar", empId: 4112, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Module Lead Software Engineer"}),<br />
(e13:Employee{name: "Rakesh Kumar", empId: 4113, city: "Noida", gender: "male", skill: "Cloud & Data Engineering", designation: "Module Lead Software Engineer"}),<br />
(e14:Employee{name: "Rohit Yadav", empId: 4114, city: "Noida", gender: "male", skill: "Enterprise Solutions", designation: "Software Engineer"}),<br />
(e15:Employee{name: "Vaibhav Kumar", empId: 4115, city: "Noida", gender: "male", skill: "Enterprise Solutions", designation: "Project Trainee"})<br />
MERGE (e2)-[:REPORT_TO]->(e1)<br />
MERGE (e3)-[:REPORT_TO]->(e1)<br />
MERGE (e4)-[:REPORT_TO]->(e1)<br />
MERGE (e5)-[:REPORT_TO]->(e1)<br />
MERGE (e6)-[:REPORT_TO]->(e1)<br />
MERGE (e7)-[:REPORT_TO]->(e6)<br />
MERGE (e8)-[:REPORT_TO]->(e6)<br />
MERGE (e9)-[:REPORT_TO]->(e6)<br />
MERGE (e10)-[:REPORT_TO]->(e6)<br />
MERGE (e11)-[:REPORT_TO]->(e6)<br />
MERGE (e12)-[:REPORT_TO]->(e9)<br />
MERGE (e13)-[:REPORT_TO]->(e9)<br />
MERGE (e14)-[:REPORT_TO]->(e9)<br />
MERGE (e15)-[:REPORT_TO]->(e9)<br />

RETURN e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15

## Introduction of Cypher Query Examples
After populating the data in database, we can validate the data by using Cypher query as below: <br />
	1. Graph traversal query to return all nodes from database with existing relationships.<br />
	   MATCH(e) <br />
	   RETURN e <br />
	2. Graph traversal query to dentify the direct reportees for a supervisor<br />
	   MATCH(e:Employee) -[r:REPORT_TO] -> (e1:Employee{name:'Manoj Vashitha'}) <br />
	   RETURN e<br />
	3. Graph traversal query to identify the indirect reportee<br />
	   MATCH(e:Employee)-[:REPORT_TO*2..]->(e1:Employee{name:'Manoj Vashitha'})<br />
	   RETURN e<br />
	4. Graph traversal query to get supervisor detail<br />
	   MATCH(e:Employee{name:'Manoj Vashitha'})<br />
	   RETURN e.empId,e.name,e.city, ...<br />
    5. Get the Hierarchy path between two employee<br />
       MATCH path=shortestPath((e1:Employee{name:'Vinod Kumar Singh'}) -[*]- (e2:Employee{name:'Ramesh Singh'})<br />
       RETURN path<br />
    6. Get the team size (including in direct reportees) of a supervisor<br />
       MATCH(e:Employee) -[:REPORT_TO*..] -> (e1:Employee{name:'Manoj Vashitha'})<br />
       RETURN COUNT(e) as team_size<br />
    7. Delete nodes from hierarchy (Indirect reportee of Vinod Kumar Singh)<br />
       MATCH(e:Employee) -[:REPORT_TO*1] -> (e1:Employee{name:'Vinod Kumar Singh'})<br />
       DETACH<br />
       DELETE e<br />
       
For more details refer to Neo4j website tutorials- <br />https://neo4j.com/docs/cypher-manual/current/clauses/create/#:~:text=To%20create%20a%20relationship%20between,create%20a%20relationship%20between%20them.&text=The%20created%20relationship%20is%20returned%20by%20the%20query.


## Rest resources details and description

EmployeeSearchController.java (Single controller class having different resources for different use cases)<br />

	/searchby/name/{name}     -  Search employee(s) by their names
	/list				           -  List all employess
	/searchby/empId/{empId}   -  Search employee by empiId
	/searchby/city/{city}     -  Search all employees by city name
	/searchby/gender/{gender} -  Search all employees by gender
	/add/new				        -  Add new employee(s) and relationship to an existing employee as supervisor
	/delete/employee		     -  Delete an employee and its associated relationship from heirarchy
	/add/relationship/for/reportees/to/supervisor  - Add a relationship for an existing employee to existing supervisor
	 
	 
## Spring Boot Implementation

To connect with Neo4j database, we must specify below properties in application.properties file<br />

	spring.neo4j.uri=bolt://localhost:7687/employee
	spring.data.neo4j.username=neo4j
	spring.data.neo4j.password=secret
	
Packages: <br />
		com.java.springboot.neo4j.application	-  Root package contains application spring boot main class <br />
		com.java.springboot.neo4j.application.api -  Contains controller classes<br />
		com.java.springboot.neo4j.application.data - Contains pojo or entity class<br />
		com.java.springboot.neo4j.application.service - Contains service classes<br />
		com.java.springboot.neo4j.application.repository - Contains JPA repository classes<br />
	
	