package com.student.entities;
// Generated Jun 20, 2018 1:22:39 PM by Hibernate Tools 5.1.0.Alpha1

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
 

/**
 * Entiti generated by hbm2java
 */
public class Entiti implements java.io.Serializable {
	DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
	private int id;
	private String name;
	private Date createdAt;
	private Date updatedAt;
	private Set<Element> elements = new HashSet<Element>(0);

	@SuppressWarnings("rawtypes")
	public Entiti(Object object) throws ParseException {
		LinkedHashMap objectMap = (LinkedHashMap)object;
		int _id = Integer.parseInt(objectMap.get("id").toString().trim());
		this.id =_id;
		this.name = (String) objectMap.get("name");
		if (objectMap.get("createdAt")==null){
			this.createdAt=null;	
		}else{
			this.createdAt= (Date) format.parse(objectMap.get("createdAt").toString());
		}
		
		if (objectMap.get("updatedAt")==null){
			this.updatedAt=null;	
		}else{
			this.updatedAt= (Date) format.parse(objectMap.get("createdAt").toString());
		}
		
	}
	
	public Entiti() {
	}

	public Entiti(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Entiti(int id, String name, Date createdAt, Date updatedAt, Set<Element> elements) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.elements = elements;
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


	public Set<Element> getElements() {
		return this.elements;
	}

	public void setElements(Set<Element> elements) {
		this.elements = elements;
	}

}
