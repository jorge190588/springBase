package tools;

public enum MethodName {
	create(100,"create"),
	
	find(200,"find"),
	findAll(201,"findall"),
	findByName(202,"findByName"),
	
	update(300,"update"),
	
	delete(400,"delete/{0}");
	
	
	private final int _code;
	private final String _name;

	MethodName(int code, String name) {
		this._code = code;
	    this._name = name;
	}

	public int get_code() { return this._code; }
	public String get_name() { return this._name; }
}
