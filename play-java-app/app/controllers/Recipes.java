package controllers;

import java.util.ArrayList;
import java.util.List;

import controllers.Application.Login;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.index;
import static play.data.Form.*;
import models.*;


@Security.Authenticated(Secured.class)
public class Recipes extends Controller {
	public static Result add() {
		DynamicForm form = form().bindFromRequest();
	    String first ="";
	    if(form.data().get("fichier[]") != null)
	    first = form.data().get("fichier[]").toString();
		//Recipe recipe = Form.form(Recipe.class).bindFromRequest().get();
       // recipe.assignedTo= new Person(form().bindFromRequest().get("assignedTo"));
       // recipe.name=form().bindFromRequest().get("name");
       // recipe.ingredients=form().bindFromRequest().get("fichier[]");
        
       // recipe.save();
       // recipe.saveManyToManyAssociations("ingredients");

	  /*  Recipe newRecipe = Recipe.create(
	    		form().bindFromRequest().get("name"),
	        form().bindFromRequest().get("assignedTo"),
	       form().bindFromRequest().get("fichier[]")
	    );*/
	    return Recipes.ok(first+"is cooretly saved");
	}
	public static Result rename(Long recipe) {
	    
	        return ok(
	            Recipe.rename(
	                recipe,
	                form().bindFromRequest().get("name")
	            )
	        );
	    	}
	public static Result delete(Long recipe) {
	        Recipe.find.ref(recipe).delete();
	        return ok();
	   
	}
	public static Result recipes(){
		  // List<Recipe> recipe= new ArrayList<Recipe>();
		 Recipe r=  new Recipe("pizza",new Person("toto"),"Tomatoes, cheese, oignions, salt");
	       return ok(r.ingredients+"\n"+r.name);
	   }


}