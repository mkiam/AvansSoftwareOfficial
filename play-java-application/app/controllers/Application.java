package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import models.Article;
import models.Person;
import play.data.Form;

import java.util.List;

import play.db.ebean.Model;
import static play.libs.Json.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

   
    public static Result getArticle() {
    	List<Article> articles= new Model.Finder(String.class, Article.class).all();
    	return ok(toJson(articles));
    }
    public static Result addArticle() {
    	Article article= Form.form(Article.class).bindFromRequest().get();
    	article.save();
    	return redirect(routes.Application.index());
    }
}
