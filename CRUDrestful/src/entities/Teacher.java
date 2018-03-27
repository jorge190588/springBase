package entities;
// Generated Mar 23, 2018 1:47:43 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Teacher generated by hbm2java
 */
@Entity
@Table(name = "teacher")
public class Teacher implements java.io.Serializable {

	private int id;
	private String name;
	private String code;
	private Date createdAt;
	private Date updatedAd;

	public Teacher() {
	}

	public Teacher(int id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Teacher(int id, String name, String code, Date createdAt, Date updatedAd) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.createdAt = createdAt;
		this.updatedAd = updatedAd;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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
	@Column(name = "updated_ad", length = 23)
	public Date getUpdatedAd() {
		return this.updatedAd;
	}

	public void setUpdatedAd(Date updatedAd) {
		this.updatedAd = updatedAd;
	}

}
