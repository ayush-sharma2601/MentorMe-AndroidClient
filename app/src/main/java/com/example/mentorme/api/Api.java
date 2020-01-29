package com.example.mentorme.api;

import com.example.mentorme.models.DefaultResponse;
import com.example.mentorme.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("/api/mentee/register")
    Call<DefaultResponse> createMentee(
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
    Call<DefaultResponse> createMentor(
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
}
