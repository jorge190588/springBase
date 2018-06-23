package com.student.entities;
import java.util.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="listEntitis")

public class ListEntiti {
	private List<Entiti> listEntitis = new ArrayList<Entiti>();

	@XmlElement(name="student")
	public List<Entiti> getListEntitis() {
		return listEntitis;
	}

	public void setListEntitis(List<Entiti> listEntitis) {
		this.listEntitis = listEntitis;
	}
}