package com.example.Api;

public class AllUsers {

    String $id;
    int UserID;
    String UserName;
    String Password;

    public AllUsers(String id, int userId, String userName, String password) {
        this.$id = id;
        this.UserID = userId;
        this.UserName = userName;
        this.Password = password;
    }



    public void set$id(String $id) {
        this.$id = $id;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String get$id() {
        return $id;
    }

    public int getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }
}
