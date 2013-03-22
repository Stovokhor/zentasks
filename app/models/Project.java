package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;
@Entity
public class Project extends Model {

	public static Finder<Long, Project> finder = new Finder<Long, Project>(Long.class, Project.class);
	
	private static final long serialVersionUID = 4225515181992827769L;
	
    @Id
    private Long id;
    
    private String name;
    
    private String folder;
    
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<User> members = new ArrayList<User>();

    /**
     * @param name
     * @param folder
     * @param owner
     */
    public Project(String name, String folder, User owner) {
        this.name = name;
        this.folder = folder;
        this.members.add(owner);
    }
    
    public static Project create(String name, String folder, String owner) {
    	final Project project = new Project(name, folder, User.finder.ref(owner));
    	project.save();
    	project.saveManyToManyAssociations("members");
    	return project;
    }
    
    public static List<Project> findInvolving(String user) {
    	return finder.where().eq("members.email", user).findList();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
}
