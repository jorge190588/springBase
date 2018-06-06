package ws;

import javax.ws.rs.core.Application;
import error.CustomException;
import error.ErrorFormat;
import java.util.Set;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	private void addRestResourceClasses(Set<Class<?>> resources) {
		try{
			resources.add(ws.StudentRest.class);
			resources.add(ws.QuizRest.class);
			resources.add(ws.TeacherRest.class);
		}catch(Exception exception){
			throw new ExceptionInInitializerError(exception); 
		}			
	}
	
}
