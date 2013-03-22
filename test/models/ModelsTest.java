package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.libs.Yaml;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;

public class ModelsTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void createAndRetrieveUser() {
		new User("bob@gmail.com", "Bob", "secret").save();
		final User bob = User.finder.where().eq("email", "bob@gmail.com").findUnique();
		assertNotNull(bob);
		assertEquals("Bob", bob.getName());
	}
	
    @Test
    public void tryAuthenticateUser() {
        new User("bob@gmail.com", "Bob", "secret").save();
        
        assertNotNull(User.authenticate("bob@gmail.com", "secret"));
        assertNull(User.authenticate("bob@gmail.com", "badpassword"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }
    
    @Test
    public void findProjectsInvolving() {
        new User("bob@gmail.com", "Bob", "secret").save();
        new User("jane@gmail.com", "Jane", "secret").save();

        Project.create("Play 2", "play", "bob@gmail.com");
        Project.create("Play 1", "play", "jane@gmail.com");

        final List<Project> results = Project.findInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Play 2", results.get(0).getName());
    }
    
    @Test
    public void findTodoTasksInvolving() {
        final User bob = new User("bob@gmail.com", "Bob", "secret");
        bob.save();

        final Project project = Project.create("Play 2", "play", "bob@gmail.com");
        final Task t1 = new Task();
        t1.setTitle("Write tutorial");
        t1.setAssignedTo(bob);
        t1.setDone(true);
        t1.save();

        final Task t2 = new Task();
        t2.setTitle("Release next version");
        t2.setProject(project);
        t2.save();

        final List<Task> results = Task.findTodoInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Release next version", results.get(0).getTitle());
    }
    
	@Test
	@SuppressWarnings("rawtypes")
    public void fullTest() {
    	
        Ebean.save((List) Yaml.load("test-data.yml"));

        // Count things
        assertEquals(3, User.finder.findRowCount());
        assertEquals(7, Project.finder.findRowCount());
        assertEquals(5, Task.finder.findRowCount());

        // Try to authenticate as users
        assertNotNull(User.authenticate("bob@example.com", "secret"));
        assertNotNull(User.authenticate("jane@example.com", "secret"));
        assertNull(User.authenticate("jeff@example.com", "badpassword"));
        assertNull(User.authenticate("tom@example.com", "secret"));

        // Find all Bob's projects
        List<Project> bobsProjects = Project.findInvolving("bob@example.com");
        assertEquals(5, bobsProjects.size());

        // Find all Bob's todo tasks
        List<Task> bobsTasks = Task.findTodoInvolving("bob@example.com");
        assertEquals(4, bobsTasks.size());
    }
}
