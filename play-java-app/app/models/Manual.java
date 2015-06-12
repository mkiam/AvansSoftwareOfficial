package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;


@Entity
public class Manual extends Model {
	@Id
	public Long id;
	public String text;
	//public Long quantity;
	@ManyToOne
    public Recipe recipe;

	public Manual(){

	}
	public Manual(Recipe recipe, String text){
		this.text= text;
		this.recipe = recipe;

	}
	public static Finder<Long, Manual> find = new Finder<Long,Manual>(
			Long.class, Manual.class
			);

	 public static List<Manual> findTodoInvolving(Long recipeAssignedTo) {
	        return  find.where().eq("recipe",recipeAssignedTo).findList();
	    }

	   


}
