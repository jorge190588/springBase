package com.student.entities;
import java.util.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="listStudents")
public class ListStudent {
	private List<Student> listStudents = new ArrayList<Student>();

	@XmlElement(name="student")
	public List<Student> getListStudents() {
		return listStudents;
	}

	public void setListStudents(List<Student> listStudents) {
		this.listStudents = listStudents;
	}
}