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
import tools.CustomException;

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
	public List<Quiz> findAll() throws CustomException{
		try{
			return quizModel.getAll();	
		}catch(CustomException ex){
			return null;
		}
		
	}
}
