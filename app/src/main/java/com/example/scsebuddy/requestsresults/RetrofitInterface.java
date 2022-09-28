package com.example.scsebuddy.requestsresults;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("/allCourses")
    Call<CoursesResult> executeAllCourses(@Body HashMap<String, String> map);

    @POST("/searchCourses")
    Call<Course> executeSearchCourses(@Body HashMap<String, String> map);

    @GET("/allTopics")
    Call<TopicsResult> executeAllTopics();

    @POST("/courseFav")
    Call<Void> executeCourseFav(@Body HashMap<String, String> map);

    @POST("/coursePost")
    Call<Void> executeCoursePost(@Body HashMap<String, String> map);

}
