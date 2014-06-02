import org.junit.*;
import org.junit.Test;
import java.util.*;
import play.test.*;
import models.*;
import java.util.List;

public class BasicTest extends UnitTest {
    
    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

 @Test
public void createAndRetrieveUser() {
    // Create a new user and save it
    new User("bob@gmail.com", "pass", "Bob", "admin").save();
    
    // Retrieve the user with e-mail address bob@gmail.com
    User bob = User.find("byEmail", "bob@gmail.com").first();
    
    // Test 
    assertNotNull(bob);
    assertEquals("Bob", bob.name);
}

@Test
public void tryConnectAsUser() {
    // Create a new user and save it
    new User("bob@gmail.com", "pass", "Bob", "admin").save();
    
    // Test 
    assertNotNull(User.connect("bob@gmail.com", "pass"));
    assertNull(User.connect("bob@gmail.com", "badpassword"));
    assertNull(User.connect("tom@gmail.com", "secret"));
}



}
