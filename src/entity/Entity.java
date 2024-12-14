package entity;

public abstract class Entity {

	private String name;
	private String password;
	
	public Entity(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Liskov Substituion Principle
	
	public void introduction() {
		System.out.println("Hi There!");
	}
	
}
