import model.*;

import java.util.Date;
import java.util.*;
import antlr.collections.List;
import entities.*;

public class demo {
	public static void main(String[] args)
	{
		StudentModel studentModel = new StudentModel();
		for(Student student: studentModel.getAll()){
			System.out.println("ID:"+student.getId());
		}
		
		Student newStudent = new Student();
		
		try{
			newStudent.setCarne("123123");
			newStudent.setFirstName("first name");
			newStudent.setLastName("last name");
			Date dateoperation = new java.sql.Date(new java.util.Date().getTime());
			newStudent.setCreatedAt(dateoperation);
			newStudent.setUpdatedAt(dateoperation);
			studentModel.add(newStudent);
			
		}catch(Exception ex){
			System.out.println("exception to add student:" + ex);
		}
		
		
		java.util.List<Student> studentList =studentModel.getAll();
		System.out.println("end to read studends " + studentList.size());
		
		
	}
}
