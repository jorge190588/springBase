/**
 * 
 */
package tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jorge
 *
 */
public class DateTools<T> {
	private SimpleDateFormat _dateSpanishFormat = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss");
	
	public Date get_CurrentDate(){
		return new Date();
	}
	
	@SuppressWarnings("unchecked")
	public T get_spanishFormat(Date date){
		if (date==null){
			return null;
		}
		return (T) (_dateSpanishFormat.format(date));
	}
	
}
