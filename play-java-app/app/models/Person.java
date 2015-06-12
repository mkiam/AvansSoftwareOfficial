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
	public Person(String name) {
		this.name= name;
		// TODO Auto-generated constructor stub
	}
	public static Finder<Long,Person> find = new Finder<Long,Person>(
			Long.class, Person.class
			);
	public static Person authenticate(String login, String password) {
		return find.where().eq("login",login)
				.eq("password", password).findUnique();
	}




}