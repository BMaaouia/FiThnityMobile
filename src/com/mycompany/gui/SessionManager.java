/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String userFirstName ; 
    private static String userLastName ; 
    private static String email; 
    private static String passowrd ;
    private static String userImg;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getUserFirstName() {
        return pref.get("userFirstName",userFirstName);
    }

    public static void setUserFirstName(String userFirstName) {
         pref.set("userFirstName",userFirstName);
    }
    
    public static String getUserLastName() {
        return pref.get("userLastName",userLastName);
    }

    public static void setUserLastName(String userLastName) {
         pref.set("userLastName",userLastName);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getUserImg() {
        return pref.get("userImg",userImg);
    }

    public static void setUserImg(String userImg) {
         pref.set("userImg",userImg);
    }
    
    
    
    
    
    
}
