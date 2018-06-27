package entities;
// Generated Jun 26, 2018 5:29:18 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RolesForm generated by hbm2java
 */
@Entity
@Table(name = "rolesForm")
public class RolesForm implements java.io.Serializable {

	private int id;
	private Form form;
	private Roles roles;
	private int users;
	private Date createdAt;
	private Date updatedAt;

	public RolesForm() {
	}

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

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form", nullable = false)
	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roles", nullable = false)
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Column(name = "users", nullable = false)
	public int getUsers() {
		return this.users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 23)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 23)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
