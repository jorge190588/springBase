package com.student.entities;
// Generated Jun 26, 2018 4:22:06 PM by Hibernate Tools 5.1.0.Alpha1

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * RolesForm generated by hbm2java
 */
public class RolesForm implements java.io.Serializable {
	DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
	private int id;
	private Form form;
	private Roles roles;
	private int users;
	private Date createdAt;
	private Date updatedAt;

	public RolesForm() {}

	public RolesForm(int id, Form form, Roles roles, int users) {
		this.id = id;
		this.form = form;
		this.roles = roles;
		this.users = users;
	}

	public RolesForm(int id, Form form, Roles roles, int users, Date createdAt, Date updatedAt) {
		this.id = id;
		this.form = form;
		this.roles = roles;
		this.users = users;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public int getUsers() {
		return this.users;
	}

	public void setUsers(int users) {
		this.users = users;
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
}
