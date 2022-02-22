package com.impetus.bigdata.rtb.application.data;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;


@Node
public class Employee {
	@Property
	private String name;

	@Id @Property
	private Long empid;
	
	@Property
	private String city;
	
	@Property
	private String gender;
	
	@Property
	private String skill;
	
	@Property
	private String designation;

	public Employee(String name, Long empid, String city, String gender, String skill, String designation) {
		super();
		this.name = name;
		this.empid = empid;
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

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
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
	
}
