package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.Form;
import play.db.ebean.Model;


@Entity
public class Recipe extends Model {
	 @Id
	    public Long id;
	    public String name;
	    @ManyToOne
	    public Person assignedTo;
	   @ManyToMany(cascade = CascadeType.REMOVE)
	    public String ingredients;
	    
public Recipe(){
	
}
	    public Recipe(String name, Person assignedTo,String ingredients) {
	    	this.ingredients= ingredients;
	        this.name = name;
	        this.assignedTo = assignedTo;
	    }

	    public static Model.Finder<Long,Recipe> find = new Model.Finder(Long.class, Recipe.class);

	  /*  public static Recipe create(String name, String owner,String ingredients) {
	    	Recipe recipe = Form.form(Recipe.class).bindFromRequest().get();
	        //Recipe recipe = new Recipe(name, Person.find.ref(owner));
	        //recipe.ingredients= ingredients;
	        recipe.save();
	        recipe.saveManyToManyAssociations("ingredients");
	        
	    		Ingredient in= new Ingredient(ingredients);
	    		in.create(in, recipe, ingredients,Person.find.byId(owner));
	    		
	    
	        return recipe;
	    }*/

	    public static List<Recipe> findInvolving(String ingredient) {
	        return find.where()
	            .eq("ingredients.name", ingredient)
	            .findList();
	    }
	    public static List<Recipe> findInvolving2(String person) {
	        return find.where()
	            .eq("assignedTo", person)
	            .findList();
	    }


	    public static String rename(Long recipeId, String newName) {
	        Recipe recipe = find.ref(recipeId);
	       recipe.name = newName;
	       recipe.update();
	        return newName;
	    }
		public static List<Recipe> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

	    
	
	

}
