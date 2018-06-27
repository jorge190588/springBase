package entities;
// Generated Jun 26, 2018 5:31:58 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Module generated by hbm2java
 */
@Entity
@Table(name = "module")
public class Module implements java.io.Serializable {

	private int id;
	private int name;
	private int users;
	private Date createdAt;
	private Date updatedAt;
	private Set<Form> forms = new HashSet<Form>(0);

	public Module() {
	}

	public Module(int id, int name, int users) {
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public Module(int id, int name, int users, Date createdAt, Date updatedAt, Set<Form> forms) {
		this.id = id;
		this.name = name;
		this.users = users;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.forms = forms;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public int getName() {
		return this.name;
	}

	public void setName(int name) {
		this.name = name;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	public Set<Form> getForms() {
		return this.forms;
	}

	public void setForms(Set<Form> forms) {
		this.forms = forms;
	}

}
