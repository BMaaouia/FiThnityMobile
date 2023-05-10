/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author andol
 */
public class Offre {
    
    
    public int id;
    private String metier;
    private String secteur;
    private String ville;
    private int nombredeposte;
    private int salaire; 
    private String dateoffre;

    
    
    
    
    public Offre(String metier, String secteur, String ville, int nombredeposte, int salaire, String dateoffre) {
        this.metier = metier;
        this.secteur = secteur;
        this.ville = ville;
        this.nombredeposte = nombredeposte;
        this.salaire = salaire;
        this.dateoffre = dateoffre;
    }

    
    
    
    
    
    public Offre(int id, String metier, String secteur, String ville, int nombredeposte, int salaire, String dateoffre) {
        this.id = id;
        this.metier = metier;
        this.secteur = secteur;
        this.ville = ville;
        this.nombredeposte = nombredeposte;
        this.salaire = salaire;
        this.dateoffre = dateoffre;
    }

    public Offre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNombredeposte() {
        return nombredeposte;
    }

    public void setNombredeposte(int nombredeposte) {
        this.nombredeposte = nombredeposte;
    }

    public int getsalaire() {
        return salaire;
    }

    public void setsalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getDateoffre() {
        return dateoffre;
    }

    public void setDateoffre(String dateoffre) {
        this.dateoffre = dateoffre;
    }
    
    
    
}
