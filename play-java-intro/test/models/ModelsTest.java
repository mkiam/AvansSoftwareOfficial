package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    @Test
    public void createAndRetrieveUser() {
        new User("toto", "toto.example.com", "Tommy", "Heunks", "secret").save();
        User tommy = User.find.where().eq("pseudo", "toto").findUnique();
        assertNotNull(tommy);
        assertEquals("Tommy", tommy.name);
    }
    @Test
    public void tryAuthenticateUser() {
    	 new User("toto", "toto.example.com", "Tommy", "Heunks", "secret").save();
        
        assertNotNull(User.authenticate("toto", "secret"));
        assertNull(User.authenticate("toto", "badpassword"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }
}
