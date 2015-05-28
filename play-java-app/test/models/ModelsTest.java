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
        new Person("toto", "toto@gmail.com", "tommy","heunks","secret").save();
        Person bob = Person.find.where().eq("login", "toto").findUnique();
        assertNotNull(bob);
        assertEquals("tommy", bob.name);
    }
}