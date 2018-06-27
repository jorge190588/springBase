package com.student.ws;

import java.util.ArrayList;
import java.util.List;

import com.student.entities.Element;
import com.student.entities.Entiti;
import com.student.tools.MaintenanceMethods;
import com.student.tools.RestResponse;

@SuppressWarnings("rawtypes")
public class ElementRestClient extends MaintenanceMethods{
	private EntitiRestClient entitiRestClient = new EntitiRestClient();
	
	public ElementRestClient(){
		super("element");
	}
	
	@SuppressWarnings("unchecked")
	public List<Element> getElementsByEntitiName(String entitiName){
		entitiRestClient.findBy("name",entitiName);
		RestResponse responseEntiti = new RestResponse(entitiRestClient.getResponse().get_data(),entitiRestClient.getResponse().get_error());
		List<Entiti> listEntiti = new ArrayList<Entiti>();		
		listEntiti = (List<Entiti>) responseEntiti.get_data();
		Entiti entiti = null;
		try{
			entiti = new Entiti(listEntiti.get(0));	
		}catch(Exception exception){
			System.out.println(exception);
		}
		
		this.findBy("entiti",entiti.getId());
		RestResponse response =(RestResponse) this.getResponse();;
		List<Element> elementListResponse = new ArrayList<Element>();
		elementListResponse= (List<Element>) response.get_data();
		List<Element> elementList = new ArrayList<Element>();
		for(Object obj: elementListResponse){
			elementList.add(new Element(obj));	
		}
		return elementList;
	}
}