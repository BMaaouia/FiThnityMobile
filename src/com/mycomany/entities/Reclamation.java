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
    private String nom_client,sujet,etat;

  
  public Reclamation() {
    }

  
    public Reclamation(int id, String nom, String sujet, String etat) {
        this.id = id;
        this.nom_client = nom;
        this.sujet = sujet;
        this.etat = etat;
       
    }
    
      public Reclamation( String nom, String sujet, String etat) {
        this.nom_client = nom;
        this.sujet = sujet;
        this.etat = etat;
       
    }
      
      
         
         


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom_client;
    }

    public void setNom(String nom) {
        this.nom_client = nom;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


    
    
    
    
  
    
 

    
    
    
    
    
    
    
    
    
    }
