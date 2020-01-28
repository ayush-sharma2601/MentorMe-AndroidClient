package com.example.mentorme.models;

public class DefaultResponse {

    private String success;
    private String msg;
    private String userId,authToken;

    public DefaultResponse(String success, String msg, String userId, String authToken) {
        this.success = success;
        this.msg = msg;
        this.userId = userId;
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
