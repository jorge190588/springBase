package entities;
// Generated Jun 22, 2018 2:03:49 PM by Hibernate Tools 5.1.0.Alpha1

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
 * QuizDetail generated by hbm2java
 */
@Entity
@Table(name = "quizDetail")
public class QuizDetail implements java.io.Serializable {

	private int id;
	private Quiz quiz;
	private Student student;
	private Integer point;
	private Date createdAt;
	private Date updatedAt;

	public QuizDetail() {
	}

	public QuizDetail(int id, Quiz quiz, Student student) {
		this.id = id;
		this.quiz = quiz;
		this.student = student;
	}

	public QuizDetail(int id, Quiz quiz, Student student, Integer point, Date createdAt, Date updatedAt) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz", nullable = false)
	public Quiz getQuiz() {
		return this.quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student", nullable = false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
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
