package com.student.ws;

import tools.*;
import java.util.*;

import org.codehaus.jackson.map.ObjectMapper;

import com.student.entities.*;
import com.sun.corba.se.impl.orbutil.ObjectWriter;
import com.sun.jersey.api.client.UniformInterfaceException;

public class StudentRestClient extends MaintenanceMethods{
	MaintenanceMethods maintenance;
	public StudentRestClient(){
		super("student");
	}
	
	public Student findByIdStudent(Integer id) throws UniformInterfaceException{
		setParams(new Object [] {id});
		find();
		Student student = new Student();
		student = (Student) getResponse().get_data();
		return student;
	}
	
	public ListStudent findAllStudent() throws UniformInterfaceException{
		setParams(new Object [] {});
		findAll();
		List<Student> list = new ArrayList<Student>();		
		list = (List<Student>) super.getResponse().get_data();
		ListStudent data = new ListStudent();
		data.setListStudents(list);
		return data;
	}
	
	public DataResponse findPageStudent(long id) throws UniformInterfaceException{
		setParams(new Object [] {});
		super.page(id);
		RestResponse response = super.getResponse();
		DataResponse dataResponse = new DataResponse(response.get_data());
		return dataResponse;
	}
	
	
	public Student removeStudent(Integer id) throws UniformInterfaceException{
		setParams(new Object [] {id});
		find();
		Student student = new Student();
		student = (Student) getResponse().get_data();
		return student;
	}
	
	public Student createStudent(Student student) throws UniformInterfaceException{
		ObjectMapper mapper = new ObjectMapper();
		try{
			String params= mapper.writeValueAsString(student);
			setParams(params.toString());
			create();
			return (Student) getResponse().get_data();
		}catch(Exception ex){
			return null;
		}
		
		
		
	}
	
}
