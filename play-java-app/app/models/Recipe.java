package models;


import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Recipe  extends Model {
	@Id
	public Long id;
	
	public String name;
	public String assignedTo;
	
	public String manual;
	
  
	public Recipe( String name, String assignedTo, String manual) {
		this.manual= manual;
		this.name = name;
		this.assignedTo = assignedTo;
	}
	public Recipe() {
		
	
	}
	
	public static Finder<Long,Recipe> find = new Finder<Long, Recipe>(
			long.class, Recipe.class
			);
	
	


}