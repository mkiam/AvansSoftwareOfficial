package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.*;

import java.util.List;

import play.db.ebean.Model;
import static play.libs.Json.*;

public class Application extends Controller {
	
	
   public static Result login() {
	   return ok(
	            login.render(form(Login.class))
	        );
	}
   public static Result index() {
	   return ok("Hello world");
   }
   public static Result authenticate() {
	   
	   Form<Login> loginForm = form(Login.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        return badRequest(login.render(loginForm));
	    } else {
	        session().clear();
	        session("pseudo", loginForm.get().pseudo);
	        return redirect(
	            routes.Application.index()
	        );
	    }
	}
  
   public static class Login {
	   
	    public String pseudo;
	    public String password;
	    public String validate() {
		    if (User.authenticate(pseudo, password) == null) {
		      return "Invalid user or password";
		    }
		    return null;
		}

	}

  
  
  
}
