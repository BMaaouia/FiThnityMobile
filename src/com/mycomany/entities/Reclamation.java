/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Lenovo
 */
public class Reclamation {
    //nemchio taw nchofo entity fi symfony
    
    private int id;
    private String nom,prenom,typer,message,email;
    private String date;
  private int numtel;
  
  public Reclamation() {
    }

  
    public Reclamation(int id, String nom, String prenom, String email, int numtel, String message, String date, String typer) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.typer = typer;
        this.message = message;
        this.email = email;
        this.date = date;
        this.numtel = numtel;
    }
    
      public Reclamation( String nom, String prenom, String email, int numtel, String message, String date, String typer) {
   
        this.nom = nom;
        this.prenom = prenom;
        this.typer = typer;
        this.message = message;
        this.email = email;
        this.date = date;
        this.numtel = numtel;
    }
      
         public Reclamation( String nom, String prenom, String email, int numtel, String message, String typer) {
   
        this.nom = nom;
        this.prenom = prenom;
        this.typer = typer;
        this.message = message;
        this.email = email;
        this.numtel = numtel;
    }
         
         
            public Reclamation(int id, String nom, String prenom, String email, int numtel, String message,  String typer) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.typer = typer;
        this.message = message;
        this.email = email;
 
        this.numtel = numtel;
    }
         
         
         


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTyper() {
        return typer;
    }

    public void setTyper(String typer) {
        this.typer = typer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "nom=" + nom + ", prenom=" + prenom + ", typer=" + typer + ", message=" + message + ", email=" + email + ", date=" + date + ", numtel=" + numtel + '}';
    }
    
    
    
    
  
    
 

    
    
    
    
    
    
    
    
    
    }
