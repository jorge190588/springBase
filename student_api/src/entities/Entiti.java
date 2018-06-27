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
 * Entiti generated by hbm2java
 */
@Entity
@Table(name = "entiti")
public class Entiti implements java.io.Serializable {

	private int id;
	private String name;
	private Date createdAt;
	private Date updatedAt;
	private Set<Element> elements = new HashSet<Element>(0);

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

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entiti")
	public Set<Element> getElements() {
		return this.elements;
	}

	public void setElements(Set<Element> elements) {
		this.elements = elements;
	}

}
