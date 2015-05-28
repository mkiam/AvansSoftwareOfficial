package controller;

import models.Person;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.*;

import play.mvc.*;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class LoginTest extends WithApplication {
	
	

	
	    @Before
	    public void setUp() {
	        start(fakeApplication(inMemoryDatabase()));
	        Person toto = new Person("toto", "toto@gmail.com", "tommy","heunks","secret");
			toto.save();
	    }
	

	@Test
	public void authenticateSuccess() {
	    Result result = callAction(
	        controllers.routes.ref.Application.authenticate(),
	        fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
	            "login", "toto",
	            "password", "secret"))
	    );
	    assertEquals(303, status(result));
	    assertEquals("toto", session(result).get("login"));
	}
	
	@Test
	public void authenticateFailure() {
	    Result result = callAction(
	        controllers.routes.ref.Application.authenticate(),
	        fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
	        		  "login", "toto",
	  	            "password", "badpassword"))
	    );
	    assertEquals(400, status(result));
	    assertNull(session(result).get("login"));
	}
   


}