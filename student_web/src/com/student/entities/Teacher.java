package com.student.entities;
// Generated Jun 20, 2018 1:22:39 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Teacher generated by hbm2java
 */

public class Teacher implements java.io.Serializable {

	private int id;
	private String name;
	private String code;
	private Date createdAt;
	private Date updatedAt;
	private Set<Quiz> quizs = new HashSet<Quiz>(0);

	public Teacher() {
	}

	public Teacher(int id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Teacher(int id, String name, String code, Date createdAt, Date updatedAt, Set<Quiz> quizs) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.quizs = quizs;
	}

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	public Set<Quiz> getQuizs() {
		return this.quizs;
	}

	public void setQuizs(Set<Quiz> quizs) {
		this.quizs = quizs;
	}

}