package fr.polytech.ig5.CSALUsers.jdbc.model;

public class Resume {
    private int resumeId;
    private int user_Id;
    private String title;
    private String description;

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Resume{" +
                "resumeId=" + resumeId +
                ", user_Id=" + user_Id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
