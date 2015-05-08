package models;



import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class User extends Model {

    @Id
    public String pseudo;
    public String name;
    public String surname;
    public String email;
    public String password;
    
    public User(String pseudo,String email, String name,String surname, String password) {
      this.email = email;
      this.name = name;
      this.password = password;
      this.pseudo = pseudo;
      this.surname = surname;
    }

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    ); 
    public static User authenticate(String pseudo, String password) {
        return find.where().eq("pseudo", pseudo)
            .eq("password", password).findUnique();
    }
}