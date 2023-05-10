/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Maaouia
 */

public class User {
    
    private int userId;
    private String userFirstName, userLastName, userEmail, userPassword, userImg;
    private int admin, isBanned, issubscribed;
    private Boolean isVerified ;

    public User() {
    }
    
    

    public User(int userId, String userFirstName, String userLastName, String userEmail, String userPassword, String userImg, int admin, int isBanned, int issubscribed, Boolean isVerified) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImg = userImg;
        this.admin = admin;
        this.isBanned = isBanned;
        this.issubscribed = issubscribed;
        this.isVerified = isVerified;
    }

    public User(String userFirstName, String userLastName, String userEmail, String userPassword, String userImg, int admin, int isBanned, int issubscribed, Boolean isVerified) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImg = userImg;
        this.admin = admin;
        this.isBanned = isBanned;
        this.issubscribed = issubscribed;
        this.isVerified = isVerified;
    }

    public User(String userFirstName, String userLastName, String userEmail, String userPassword, String userImg) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImg = userImg;
    }

    public User(int userId, String userFirstName, String userLastName, String userEmail) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
    }

    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstname() {
        return userFirstName;
    }

    public void setUserFirstname(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastname() {
        return userLastName;
    }

    public void setUserLastname(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public int getIssubscribed() {
        return issubscribed;
    }

    public void setIssubscribed(int issubscribed) {
        this.issubscribed = issubscribed;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" + "userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userImg=" + userImg + ", admin=" + admin + ", isBanned=" + isBanned + ", issubscribed=" + issubscribed + ", isVerified=" + isVerified + '}';
    }
    
    
    
}