package models;



import javax.persistence.*;

import play.db.ebean.Model;
 
@Entity
public class Article extends Model {
	@Id
	public int id;
    public String title;
 
   
    
    public Article(String title) {
        this.title= title;
  
    }
    public Article(){
    	
    }
 
}
