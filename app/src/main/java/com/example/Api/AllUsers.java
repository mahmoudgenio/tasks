package com.example.Api;

public class AllUsers {

    String id;
    int userId;
    String userName;
    String password;

    public AllUsers(String id, int userId, String userName, String password) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public AllUsers() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
