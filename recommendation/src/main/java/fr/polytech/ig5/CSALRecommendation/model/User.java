package fr.polytech.ig5.CSALRecommendation.model;

public class User {
    private int userId;
    private int resumeId;
    private String username;
    private String password;
    private boolean enabled;
    private String role;
    private int zone;
    private String mail;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* PASSWORD */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* ENABLED */

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /* ROLE */

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /* ZONE */

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    /* MAIL */

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString(){
        return "{userId="+userId+ ", username="+username+",password="+password+",enabled="+enabled+"}";
    }
}
