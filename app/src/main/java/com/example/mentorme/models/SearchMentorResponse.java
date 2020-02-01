package com.example.mentorme.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMentorResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("mentorId")
    @Expose
    private String mentorId;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

}