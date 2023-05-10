/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author user
 */
public class Comment {
     private int id_comment;
    private String nom_prenom, text_comment;
    
     public Comment()
     {
         
     }
    public Comment(int id_comment, String nom_prenom, String text_comment) {
        this.id_comment = id_comment;
        this.nom_prenom = nom_prenom;
        this.text_comment = text_comment;
    }

    public Comment(String nom_prenom, String text_comment) {
        this.nom_prenom = nom_prenom;
        this.text_comment = text_comment;
    }

    public int getId_comment() {
        return id_comment;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public String getText_comment() {
        return text_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public void setText_comment(String text_comment) {
        this.text_comment = text_comment;
    }

    @Override
    public String toString() {
        return "Comment{" + "id_comment=" + id_comment + ", nom_prenom=" + nom_prenom + ", text_comment=" + text_comment + '}';
    }
    
    
}
