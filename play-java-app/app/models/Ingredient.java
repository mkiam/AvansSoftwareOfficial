package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



import play.db.ebean.Model;


@Entity                                    
public class Ingredient extends Model {
	@Id
	public Long id;
	public String name;
	public int quantity;
	@ManyToMany
    public Person assignedTo;
	  @ManyToMany
	    public Recipe recipe;
	public Ingredient(String name, int quantity) {
	this.name = name;
	this.quantity = quantity;
	}
	 public static Model.Finder<String,Ingredient> find = new Model.Finder(String.class, Ingredient.class);

	   

	    public static Ingredient create(Ingredient ingredient, String recipe, String name, int quantity) {
	        ingredient.recipe = Recipe.find.ref(recipe);
	        ingredient.name = name;
	        ingredient.quantity = quantity;
	        ingredient.save();
	        return ingredient;
	    }
}