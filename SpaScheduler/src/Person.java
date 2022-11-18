public abstract class Person{
	protected String name;
	protected String type;
	
	public Person(String n, String t) {
		name = n;
		type = t;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	String getName() {
		return name;
	}
	
	public abstract String getType();
	
}