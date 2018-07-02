package com.student.entities;
// Generated Jun 26, 2018 4:22:06 PM by Hibernate Tools 5.1.0.Alpha1

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;
 

/**
 * Form generated by hbm2java
 */
public class Form implements java.io.Serializable {
	DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);

	private int id;
	private Module module;
	private String name;
	private String title;
	private String url;
	private int users;
	private String icon;
	private Integer orderElement;
	private Date createdAt;
	private Date updatedAt;
	private Set<RolesForm> rolesForms = new HashSet<RolesForm>(0);

	public Form() {
	}

	public Form(int id, Module module, String name, String title, String url, int users) {
		this.id = id;
		this.module = module;
		this.name = name;
		this.title = title;
		this.url = url;
		this.users = users;
	}

	public Form(int id, Module module, String name, String title, String url, int users, String icon,
			Integer orderElement, Date createdAt, Date updatedAt, Set<RolesForm> rolesForms) {
		this.id = id;
		this.module = module;
		this.name = name;
		this.title = title;
		this.url = url;
		this.users = users;
		this.icon = icon;
		this.orderElement = orderElement;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.rolesForms = rolesForms;
	}

	@SuppressWarnings("rawtypes")
	public Form(Object object) throws ParseException {
		LinkedHashMap objectMap = (LinkedHashMap)object;
		this.id =(int) objectMap.get("id");
		this.name = (String) objectMap.get("name");
		this.title = (String) objectMap.get("title");
		this.url = (String) objectMap.get("url");
		try{
			this.module = new Module(objectMap.get("module"));	
		}catch(Exception exception){
			System.out.println("Error to convert module in form class");
		}
		
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Module getModule() {
		return this.module;
	}

	
	
	public void setModule(Module module) {
		module.setForms(null);
		this.module = module;
	}
	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUsers() {
		return this.users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderElement() {
		return this.orderElement;
	}

	public void setOrderElement(Integer orderElement) {
		this.orderElement = orderElement;
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

	public Set<RolesForm> getRolesForms() {
		return this.rolesForms;
	}

	public void setRolesForms(Set<RolesForm> rolesForms) {
		this.rolesForms = rolesForms;
	}

 
	
}
