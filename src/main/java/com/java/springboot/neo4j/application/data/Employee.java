package com.java.springboot.neo4j.application.data;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Employee {
	@Property
	private String name;

	@Id
	@Property
	private Long empId;

	@Property
	private String city;

	@Property
	private String gender;

	@Property
	private String skill;

	@Property
	private String designation;
	
	public Employee(){}

	public Employee(String name, Long empId, String city, String gender, String skill, String designation) {
		super();
		this.name = name;
		this.empId = empId;
		this.city = city;
		this.gender = gender;
		this.skill = skill;
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", empId=" + empId + ", city=" + city + ", gender=" + gender + ", skill="
				+ skill + ", designation=" + designation + "]";
	}

}
