package entities;
// Generated Jun 26, 2018 9:30:40 AM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Teacher teacher;
	private String name;
	private String code;
	private Date createdAt;
	private Date updatedAt;
	private Set<QuizDetail> quizDetails = new HashSet<QuizDetail>(0);

	public Quiz() {
	}

	public Quiz(int id, Teacher teacher, String name, String code) {
		this.id = id;
		this.teacher = teacher;
		this.name = name;
		this.code = code;
	}

	public Quiz(int id, Teacher teacher, String name, String code, Date createdAt, Date updatedAt,
			Set<QuizDetail> quizDetails) {
		this.id = id;
		this.teacher = teacher;
		this.name = name;
		this.code = code;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.quizDetails = quizDetails;
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
	@JoinColumn(name = "teacher", nullable = false)
	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
	@Column(name = "updated_at", length = 23)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz")
	public Set<QuizDetail> getQuizDetails() {
		return this.quizDetails;
	}

	public void setQuizDetails(Set<QuizDetail> quizDetails) {
		this.quizDetails = quizDetails;
	}

}
