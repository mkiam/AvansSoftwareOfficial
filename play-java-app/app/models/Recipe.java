package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;


@Entity
public class Recipe extends Model {
	 @Id
	    public Long id;
	    public String name;
	    public Person assignedTo;
	    @ManyToMany(cascade = CascadeType.REMOVE)
	    public List<Ingredient> ingredients = new ArrayList<Ingredient>();
	    

	    public Recipe(String name, Person assignedTo) {
	        this.name = name;
	        this.assignedTo = assignedTo;
	    }

	    public static Model.Finder<String,Recipe> find = new Model.Finder(String.class, Recipe.class);

	    public static Recipe create(String name, String owner) {
	        Recipe recipe = new Recipe(name, Person.find.ref(owner));
	        recipe.save();
	        recipe.saveManyToManyAssociations("ingredients");
	        return recipe;
	    }

	    public static List<Recipe> findInvolving(String ingredient) {
	        return find.where()
	            .eq("ingredients.name", ingredient)
	            .findList();
	    }
	
	

}