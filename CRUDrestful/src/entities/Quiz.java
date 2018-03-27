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
 * Quiz generated by hbm2java
 */
@Entity
@Table(name = "quiz")
public class Quiz implements java.io.Serializable {

	private int id;
	private String name;
	private String code;
	private int teacher;
	private Date createdAt;
	private Date updatedAd;

	public Quiz() {
	}

	public Quiz(int id, String name, String code, int teacher) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.teacher = teacher;
	}

	public Quiz(int id, String name, String code, int teacher, Date createdAt, Date updatedAd) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.teacher = teacher;
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

	@Column(name = "teacher", nullable = false)
	public int getTeacher() {
		return this.teacher;
	}

	public void setTeacher(int teacher) {
		this.teacher = teacher;
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
