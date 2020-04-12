public class User implements java.io.Serializable {
	
	protected String username = "initializetousernamethatwillnotbeused";   //initialize to a username that will not be used
	protected String password;
	protected String firstName;
	protected String lastName;
	
	User() {
	}
	
	public String getUsername() {
		return username;
	}
}
