package Assignment4;

import java.sql.Date;

public class User {
	
	public User(){
		super();
	}
	public User(String username,String password,String firstName,String lastName,String email,Date dateOfBirth){
		super();
		this.username=username;
		this.firstName=firstName;
		this.password=password;
		this.lastName=lastName;
		this.email=email;
		this.dateOfBirth=dateOfBirth;
	}
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Date dateOfBirth;

	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setFirstName(String firstname){
		this.firstName=firstname;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
	public Date getDateOfBirth(){
		return this.dateOfBirth;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

}
