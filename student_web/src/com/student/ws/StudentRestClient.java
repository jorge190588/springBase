package com.student.ws;

import tools.*;
import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;
import com.student.entities.*;
import com.sun.jersey.api.client.UniformInterfaceException;

public class StudentRestClient extends MaintenanceMethods{
	MaintenanceMethods maintenance;
	public StudentRestClient(){
		super("student");
	}
	
	public Student findById(Integer id) throws UniformInterfaceException{
		setParams(new Object [] {id});
		find();
		Student student = new Student();
		student = (Student) getResponse().get_data();
		return student;
	}
	
	public ListStudent findAll() throws UniformInterfaceException{
		setParams(new Object [] {});
		find_All();
		List<Student> list = new ArrayList<Student>();		
		list = (List<Student>) super.getResponse().get_data();
		ListStudent data = new ListStudent();
		data.setListStudents(list);
		return data;
	}
	
	public Student remove(Integer id) throws UniformInterfaceException{
		setParams(new Object [] {id});
		find();
		Student student = new Student();
		student = (Student) getResponse().get_data();
		return student;
		
	}
	
}
