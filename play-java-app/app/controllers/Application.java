package controllers;
import play.db.*;
import play.*;
import play.mvc.*;
import views.html.*;
import models.Person;
import play.data.Form;
import static play.data.Form.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.text.html.FormView;

import play.db.ebean.Model;
import static play.libs.Json.*;

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
    }



