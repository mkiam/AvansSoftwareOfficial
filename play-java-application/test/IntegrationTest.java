import models.Article;

import org.junit.*;

import play.db.ebean.*;
import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Add Person");
            }
        });
    }
    @Test
    public void createAndRetrieveUser() {
        // Create a new user and save it
     Article ar=   new Article("test");
     ar.save();
        
      
        
        // Test 
        assertNotNull(ar);
        assertEquals("test", ar.title);
    }

}
