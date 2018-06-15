package forms;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="element")
public class Element {
	private int id;
	private String idElement;
	private String label;
	private String type;
	private String required;
	private String pattern;
	private String patternMessage;
	
	public Element (int _id,String _idElement,String _label, String _type,Boolean required, String _pattern,String _patternMessage){
		this.setId(_id);
		this.setIdElement(_idElement);
		this.label=_label;
		this.type=_type;
		
		if (required)	this.setRequired("required");
		else	this.setRequired("");
		
		this.pattern=_pattern;
		this.patternMessage=_patternMessage;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	 

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPatternMessage() {
		return patternMessage;
	}

	public void setPatternMessage(String patternMessage) {
		this.patternMessage = patternMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdElement() {
		return idElement;
	}

	public void setIdElement(String idElement) {
		this.idElement = idElement;
	}
}
