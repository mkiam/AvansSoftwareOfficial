package controllers;
import java.sql.SQLException;
import java.util.List;

import play.mvc.*;
import views.html.*;
import models.Person;
import models.Recipe;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;


public class Application extends Controller {


	public static Result sayHello() {
		List<Person>li=Person.find.findList();
		return ok (Json.toJson(li));


	}
	public static Result sayHelloUser(Long user) {
		Person p =Person.find.byId(user);
		return ok (Json.toJson(p));


	}
	public static Result allRecipes() {
		List<Recipe>li=Recipe.find.findList();
		return ok (Json.toJson(li));


	}
	
	public static Result findRecipeByUser(String user) {
	List <Recipe> r = Recipe.find.where().eq("assignedTo", user).findList();
		return ok (Json.toJson(r));
	}
	public static Result findRecipeByID(Long id) {
		Recipe r = Recipe.find.byId(id);
		return ok (Json.toJson(r));


	}
	
	

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

	public static Result addRecipe(){

		final DynamicForm form = Form.form().bindFromRequest();
		final String name = form.get("recipe");
		final String manual = form.get("manual");
		

		Recipe recipe= new Recipe(name, session("login"), manual);
		
		
		recipe.save();

		return ok(Json.toJson("the recipe"+ " "+recipe.name+" "+"is correctly saved"));


	}

	public static Result searchRecipes(){
		List<Recipe>li=Recipe.find.findList();
		return ok (Json.toJson(li));

	}
	public static Result myRecipes(){
		return ok(Json.toJson(Recipe.find.where().eq("assignedTo", session("login")).findList()));

	}





}



