package com.example.mentorme.api;

import com.example.mentorme.models.SignUpResponse;
import com.example.mentorme.models.LoginResponse;
import com.example.mentorme.models.User;
import com.example.mentorme.models.UserResponse;
import com.example.mentorme.models.SearchMentorResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("/api/mentee/register")
    Call<SignUpResponse> createMentee(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/mentee/login")
    Call<LoginResponse> menteeLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/mentor/register")
    Call<SignUpResponse> createMentor(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/mentor/login")
    Call<LoginResponse> mentorLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @HTTP(method = "GET", path = "/api/mentee/getallmentors", hasBody = true)
    Call <UserResponse> getMentorsList(
            @Header("authToken") String authToken,
            @Body String email
    );

    @HTTP(method = "GET", path = "/api/mentor/getallmentees", hasBody = true)
    Call <UserResponse> getMenteesList(
            @Header("authToken") String authToken,
            @Body String mentorId
    );

    @FormUrlEncoded
    @POST("/api/mentee/newmentor")
    Call<SearchMentorResponse> searchMentor(
            @Header("authToken") String authToken,
            @Field("menteeId") String menteeId,
            @Field("skill") String skill
    );
}
