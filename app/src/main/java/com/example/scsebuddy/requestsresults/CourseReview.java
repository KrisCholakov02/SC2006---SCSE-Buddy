package com.example.scsebuddy.requestsresults;

import java.util.ArrayList;

public class CourseReview {
//    private String code;
//    private String title; //change
//    private String aus;
//    private int favorite;
//    private int year;
    private int grade;
    private String description;
    private String datePublished;
    private String courseCode;
    private String name;
    private String email;
    private ArrayList<String> tags;

    public String getEmail() { return  email;}

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
