package com.example.scsebuddy.requestsresults;

public class ForumPost {
    //    private String code;
//    private String title; //change
//    private String aus;
//    private int favorite;
//    private int year;

    private String datePublished;
    private String title;
    private String description;
    private String name;
    private String topicID;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getTopicID() {
        return topicID;
    }
}
