package rs5253;
import java.io.Serializable;
import java.util.ArrayList;
class User implements Serializable{
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	
	protected String getUsername() {
		return username;
	}
	protected String getPassword() {
		return password;
	}
	protected String getFirstName() {
		return firstName;
	}
	protected String getLastName() {
		return lastName;
	}
	protected void setUsername(String username) {
		this.username = username;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//Override this method
	public static void viewAll(ArrayList<Course> list){
		System.out.println("This will be overrided!");
	}
	
}
