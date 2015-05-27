package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person extends Model {
	@Id
	public Long id;
	public String login;
	public String email;
	public String name;
	public String surname;
	public String password;  
	public Person(String login, String email, String name, String surname, String password) {
		this.login=login;
		this.surname = surname;
		this.email = email;
		this.name = name;
		this.password = password;
	}
	public static Finder<String,Person> find = new Finder<String,Person>(
			String.class, Person.class
			);
	 public static Person authenticate(String login, String password) {
	        return find.where().eq("login",login)
	            .eq("password", password).findUnique();
	    }

	


}