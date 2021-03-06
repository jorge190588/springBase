package com.student.entities;
// Generated Jun 26, 2018 4:22:06 PM by Hibernate Tools 5.1.0.Alpha1

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;

/**
 * Roles generated by hbm2java
 */

public class Roles implements java.io.Serializable {
	DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
	private int id;
	private String name;
	private Boolean isEnabled;
	private Date createdAt;
	private Date updatedAt;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<RolesForm> rolesForms = new HashSet<RolesForm>(0);

	public Roles() {}
	
	public Roles(Object object){
			LinkedHashMap objectMap = (LinkedHashMap)object;
			this.id = (int) objectMap.get("id");
			this.name = (String) objectMap.get("name");
			this.isEnabled = (Boolean) objectMap.get("isEnabled");
			
			if (objectMap.get("createdAt")==null){
				this.createdAt=null;	
			}else{
				try{
					this.createdAt= (Date) format.parse(objectMap.get("createdAt").toString());	
				}catch(Exception exception){
					System.out.println("Error to convert createdAt in users.");
				}
			}
			
			if (objectMap.get("updatedAt")==null){
				this.updatedAt=null;	
			}else{
				try{
					this.updatedAt= (Date) format.parse(objectMap.get("createdAt").toString());
				}catch(Exception exception){
					System.out.println("Error to convert updatedAt in users");
				}
			}
	}

	public Roles(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Roles(int id, String name, Boolean isEnabled, Date createdAt, Date updatedAt, Set<Users> userses,
			Set<RolesForm> rolesForms) {
		this.id = id;
		this.name = name;
		this.isEnabled = isEnabled;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userses = userses;
		this.rolesForms = rolesForms;
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

	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
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

	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	public Set<RolesForm> getRolesForms() {
		return this.rolesForms;
	}

	public void setRolesForms(Set<RolesForm> rolesForms) {
		this.rolesForms = rolesForms;
	}
}
