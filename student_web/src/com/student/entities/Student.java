package com.student.entities;

import java.util.Date;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="student")
public class Student {
	public Student(int id, String firstName, String lastName, String carne, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.carne = carne;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	private int id;
	private String firstName;
	private String lastName;
	private String carne;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public Student() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlElement
	public String getCarne() {
		return carne;
	}
	public void setCarne(String carne) {
		this.carne = carne;
	}

	@XmlElement
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@XmlElement
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
