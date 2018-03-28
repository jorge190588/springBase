package ws;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

	public Set<Class<?>> getClasses(){
		System.out.println("Run set in applicationConfig");
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
		 
	}
	
	private void addRestResourceClasses(Set<Class<?>> resources){
		resources.add(ws.DemoRest.class);
	}
	
}
