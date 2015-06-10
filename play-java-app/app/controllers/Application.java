package controllers;
import java.sql.SQLException;
import java.util.List;

import play.db.*;
import play.*;
import play.mvc.*;
import views.html.*;
import models.Person;
import models.Recipe;
import play.data.Form;
import play.libs.F;
import play.libs.F.Function;
import play.libs.Json;


public class Application extends Controller {

    public static Result index() {
    	
    	return ok(index.render(null));
    }
   
  
     
    
    public static Result addPerson() throws SQLException {
    	
    	Person person = Form.form(Person.class).bindFromRequest().get();
    	person.save();
    	 session().clear();
         session("login", person.login);
    	
    	 return redirect(
                 routes.Application.menu()
    	            );
    }

   
    // -- Authentication

    public static class Login {

        public String login;
        public String password;
        
        public String validate() {
            if (Person.authenticate(login, password) == null) {
              return "Invalid user or password";
            }
            return null;
        }
        
    }
    /**
     * Handle login form submission.
     * @throws SQLException 
     */
   
   
    @Security.Authenticated(Secured.class)
    public static Result menu() {
        return ok(menu.render());
    }
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.index()
        );
    }
    @Security.Authenticated(Secured.class)
    public static Result add() {
        return ok(add.render());
    }
   
   
   
	

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        
        if (loginForm.hasErrors()) {
            return badRequest(index.render(loginForm));
        } else {
        	 
            session().clear();
            session("login", loginForm.get().login);
            return redirect(
               routes.Application.menu()
            );
        }
    }
    public static Result addRecipe() {
    	Recipe recipe = Form.form(Recipe.class).bindFromRequest().get(); 
    	recipe.save();
		return TODO;
    }
    
    }



