package com.example.scsebuddy.requestsresults;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitInterface {

    //Everything about Account
    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("/changePW")
    Call<Void> executeChangePW(@Body HashMap<String, String> map);

    //Everything about Courses
    @POST("/allCourses")
    Call<CoursesResult> executeAllCourses(@Body HashMap<String, String> map);

    @POST("/allCourseReview")
    Call<CourseReviewResult> executeAllCourseReview(@Body HashMap<String, String> map);

    @POST("/courseFav")
    Call<Void> executeCourseFav(@Body HashMap<String, String> map);

    @POST("/coursePost")
    Call<Void> executeCoursePost(@Body HashMap<String, String> map);

    //Everything About Forum
    @GET("/allTopics")
    Call<TopicsResult> executeAllTopics();

    @POST("/forumPost")
    Call<Void> executeForumPost(@Body HashMap<String, String> map);




    //TODO FOR KRIS
    @POST("/searchCourses")
    Call<Course> executeSearchCourses(@Body HashMap<String, String> map);

    @POST("/searchPost")
    Call<Course> executeSearchPost(@Body HashMap<String, String> map);

    //GET for Both Course and Forum!!

}
