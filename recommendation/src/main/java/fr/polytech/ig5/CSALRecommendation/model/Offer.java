package fr.polytech.ig5.CSALRecommendation.model;

import java.util.Date;

public class Offer {
    private int offerId;
    private int userId;
    private String address;
    private String title;
    private String description;
    private Date startingDate;
    private Date dateEnd;
    private int nbjobs;
    private int salary;

    public Offer(int userId, String address, String title, String description, Date startingDate, Date dateEnd, int nbjobs, int salary) {
        this.userId = userId;
        this.address = address;
        this.title = title;
        this.description = description;
        this.startingDate = startingDate;
        this.dateEnd = dateEnd;
        this.nbjobs = nbjobs;
        this.salary = salary;
    }

    public Offer() {
        this.userId = -1;
        this.address = "";
        this.title = "";
        this.description = "";
        this.startingDate = new Date("01/01/1999");
        this.dateEnd = new Date("01/01/1999");
        this.nbjobs = -1;
        this.salary = -1;
    }


    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getNbjobs() {
        return nbjobs;
    }

    public void setNbjobs(int nbjobs) {
        this.nbjobs = nbjobs;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


}
