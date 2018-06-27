package com.student.config;

public enum  PageConfiguration {
	ApplicationName(1,"Student App");
	
	private final int _code;
	private final String _title;

	PageConfiguration(int code, String title) {
		this._code = code;
	    this._title = title;
	}

	public int get_code() { return this._code; }
	public String get_title() { return this._title; }
}
