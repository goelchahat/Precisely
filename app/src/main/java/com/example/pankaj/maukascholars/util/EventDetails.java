package com.example.pankaj.maukascholars.util;

/**
 * Project Name: 	<Visual Perception For The Visually Impaired>
 * Author List: 		Pankaj Baranwal
 * Filename: 		<>
 * Functions: 		<>
 * Global Variables:	<>
 */
public class EventDetails {
    private String title = "Rupee marks the biggest gain in 4 years against US";
    private String description = "The Indian rupee cheered the Moody's rating upgrade posted the biggest single-day gain in last lorem ipsum lorem ipsumlorem ipsumlorem ipsum";
    private String name = "The Financial Express";
    private String deadline = "Deadline: Dec 1 2017";
    private String image = "R.mipmap.upnetwork";
    private String icon = "R.mipmap.upnetwork";
    private String link = "http://www.google.com";

    public EventDetails(){

    }

    public EventDetails(String title, String description, String name, String deadline, String image, String icon, String link){
        this.title = title;
        this.deadline = deadline;
        this.description = description;
        this.name = name;
        this.deadline = deadline;
        this.image = image;
        this.icon = icon;
        this.link = link;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
