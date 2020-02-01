package com.example.mentorme.models;

public class RecommendedSubjectsModel {
    private String subjectName;
    private String subjectDescription;
    private int key;

    public RecommendedSubjectsModel(String subjectName, String subjectDescription, int key) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.key = key;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
