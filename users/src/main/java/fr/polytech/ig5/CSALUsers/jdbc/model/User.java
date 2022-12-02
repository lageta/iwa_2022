package fr.polytech.ig5.CSALUsers.jdbc.model;

public class User {
	private int userId;
	private int resumeId;
    private String email_address;
    private String password;

	private String firstname;

	private String lastname;

	/* USER ID */

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/* RESUME ID */

	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	/* USERNAME */

    public String getEmail() {
		return email_address;
	}

	public void setEmail(String email_address) {
		this.email_address = email_address;
	}

	/* PASSWORD */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString(){
		return "{userId="+userId+ ", email_address="+email_address+",password="+password+"}";
	}
}
