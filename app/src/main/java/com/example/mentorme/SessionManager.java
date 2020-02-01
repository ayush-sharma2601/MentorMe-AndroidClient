package com.example.mentorme;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private String roleSelected, userId, authToken,email;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MentorMe", 0);
        editor = sharedPreferences.edit();
    }

    public String getUserId() {
        return sharedPreferences.getString("userId", null);
    }

    public void setUserId(String userId) {
        editor.putString("userId", userId);
        editor.commit();
    }

    public void setLogInStatus(Boolean status) {
        editor.putBoolean("loginStatus", status);
        editor.commit();

    }

    public boolean getLogInStatus() {
        return sharedPreferences.getBoolean("loginStatus", false);
    }

    public String getRoleSelected() {
        return sharedPreferences.getString("roleSelected", null);
    }

    public void setRoleSelected(String roleSelected) {
        editor.putString("roleSelected", roleSelected);
        editor.commit();
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public String getAuthToken() {
        return sharedPreferences.getString("authToken", null);
    }

    public void setAuthToken(String authToken) {
        editor.putString("authToken", authToken);
        editor.commit();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
