package controllers;

import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import models.*;


@Security.Authenticated(Secured.class)
public class Recipes extends Controller {
	

	
	
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello() {
	Recipe recipe = Form.form(Recipe.class).bindFromRequest().get();
	    return ok(Json.toJson(recipe));
	
	}


}