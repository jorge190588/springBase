import model.*;

import entities.*;

public class demo {
	public static void main(String[] args)
	{
		StudentModel studentModel = new StudentModel();
		/*for(Student student: studentModel.getAll()){
			System.out.println("ID:"+student.getId());
		}*/
		int studentSize =studentModel.getAll().size();
		System.out.println("end to read studends " + studentSize);
	}
}
