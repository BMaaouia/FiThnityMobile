/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Reservation {
     private int id_r;
    private String reference;
    private float prix,poids;
    private String dateReser;
    private String villeDepart,villeArrive;
    private int id_produit;

    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getDateReser() {
        return dateReser;
    }

    public void setDateReser(String dateReser) {
        this.dateReser = dateReser;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Reservation() {
    }

    public Reservation(int id_r, String reference, float prix, float poids, String dateReser, String villeDepart, String villeArrive, int id_produit) {
        this.id_r = id_r;
        this.reference = reference;
        this.prix = prix;
        this.poids = poids;
        this.dateReser = dateReser;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.id_produit = id_produit;
    }

    public Reservation(String reference, float prix, float poids, String dateReser, String villeDepart, String villeArrive, int id_produit) {
        this.reference = reference;
        this.prix = prix;
        this.poids = poids;
        this.dateReser = dateReser;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.id_produit = id_produit;
    }

    public Reservation(String reference, float prix, float poids, int id_produit, String villeDepart, String villeArrive) {
        this.reference = reference;
        this.prix = prix;
        this.poids = poids;
        this.id_produit = id_produit;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_r;
        hash = 97 * hash + Objects.hashCode(this.reference);
        hash = 97 * hash + Float.floatToIntBits(this.prix);
        hash = 97 * hash + Float.floatToIntBits(this.poids);
        hash = 97 * hash + Objects.hashCode(this.dateReser);
        hash = 97 * hash + Objects.hashCode(this.villeDepart);
        hash = 97 * hash + Objects.hashCode(this.villeArrive);
        hash = 97 * hash + this.id_produit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id_r != other.id_r) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (Float.floatToIntBits(this.poids) != Float.floatToIntBits(other.poids)) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        if (!Objects.equals(this.villeDepart, other.villeDepart)) {
            return false;
        }
        if (!Objects.equals(this.villeArrive, other.villeArrive)) {
            return false;
        }
        if (!Objects.equals(this.dateReser, other.dateReser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_r=" + id_r + ", reference=" + reference + ", prix=" + prix + ", poids=" + poids + ", dateReser=" + dateReser + ", villeDepart=" + villeDepart + ", villeArrive=" + villeArrive + ", id_produit=" + id_produit + '}';
    }
    
}
