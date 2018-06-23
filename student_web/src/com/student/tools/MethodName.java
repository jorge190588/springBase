package com.student.tools;

public enum MethodName {
	create(100,"create"),
	
	findby(200,"findby"),
	findAll(201,"findall"),
	
	update(300,"update"),
	
	delete(400,"delete/{0}"),
	
	page(500,"page"),;
	
	
	private final int _code;
	private final String _name;

	MethodName(int code, String name) {
		this._code = code;
	    this._name = name;
	}

	public int get_code() { return this._code; }
	public String get_name() { return this._name; }
}
