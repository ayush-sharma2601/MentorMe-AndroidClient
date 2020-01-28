package com.example.mentorme.models;

public class LoginResponse {

    private String success,msg,userId,authToken;

    public LoginResponse(String success, String msg, String userId, String authToken) {
        this.success = success;
        this.msg = msg;
        this.userId = userId;
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
