/**
 * 
 */
package ws;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author jorge
 *
 */
@Path("demo")
public class DemoRest {
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(){
		return "hello";
	}
}
