/**
 * 
 */
package ws;

import javax.ws.rs.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.*;
import java.util.*;
import model.*;

/**
 * @author jorge
 *
 */
@Path("quiz")
public class QuizRest {
	
	private QuizModel quizModel = new QuizModel();
	
	@GET
	@Path("/findall_json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quiz> findAll(){
		return quizModel.getAll();
	}
}
