/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author andol
 */
public class Demande {
    
    private int id;

    private String cin;

    private String competences;

    private String cv;

    private String lettremotivation;

    private String cartegrise;

    private String offre;

    public Demande() {
    }

    public Demande(String cin, String competences, String cv, String lettremotivation, String cartegrise, String offre) {
        this.cin = cin;
        this.competences = competences;
        this.cv = cv;
        this.lettremotivation = lettremotivation;
        this.cartegrise = cartegrise;
        this.offre = offre;
    }

    
    
    
    
    
    public Demande(int id, String cin, String competences, String cv, String lettremotivation, String cartegrise, String offre) {
        this.id = id;
        this.cin = cin;
        this.competences = competences;
        this.cv = cv;
        this.lettremotivation = lettremotivation;
        this.cartegrise = cartegrise;
        this.offre = offre;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String  cv) {
        this.cv = cv;
    }

    public String getLettremotivation() {
        return lettremotivation;
    }

    public void setLettremotivation(String lettremotivation) {
        this.lettremotivation = lettremotivation;
    }

    public String getCartegrise() {
        return cartegrise;
    }

    public void setCartegrise(String cartegrise) {
        this.cartegrise = cartegrise;
    }

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }


}
    