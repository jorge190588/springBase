package entities;
// Generated Mar 27, 2018 5:12:38 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * QuizDetail generated by hbm2java
 */
@Entity
@Table(name = "quizDetail")
public class QuizDetail implements java.io.Serializable {

	private int id;
	private int quiz;
	private String student;
	private Integer point;
	private Date createdAt;
	private Date updatedAt;

	public QuizDetail() {
	}

	public QuizDetail(int id, int quiz, String student) {
		this.id = id;
		this.quiz = quiz;
		this.student = student;
	}

	public QuizDetail(int id, int quiz, String student, Integer point, Date createdAt, Date updatedAt) {
		this.id = id;
		this.quiz = quiz;
		this.student = student;
		this.point = point;
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

	@Column(name = "quiz", nullable = false)
	public int getQuiz() {
		return this.quiz;
	}

	public void setQuiz(int quiz) {
		this.quiz = quiz;
	}

	@Column(name = "student", nullable = false, length = 50)
	public String getStudent() {
		return this.student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	@Column(name = "point")
	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
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