package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
@Entity
public class User extends Model {

	private static final long serialVersionUID = -1720605113681316586L;

	public static Finder<String, User> finder = new Finder<String, User>(String.class, User.class);
	
    @Id
    private String email;
    private String name;
    private String password;
    
    /**
     * @param email
     * @param name
     * @param password
     */
    public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
     }
    
    public static User authenticate(String email, String password) {
    	return finder.where().eq("email", email).eq("password", password).findUnique();
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
