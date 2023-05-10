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
public class Produit {
    private int id_produit,poids;
    private String nomProduit,description;

    public Produit(int id_produit, String nomProduit,int poids, String description) {
        this.id_produit = id_produit;
        this.nomProduit = nomProduit;
        this.poids = poids;
        this.description = description;
    }

    public Produit( String nomProduit,int poids, String description) {
        this.nomProduit = nomProduit;
        this.poids = poids;
        this.description = description;
    }

    public Produit() {
      
    }

    

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id_produit;
        hash = 37 * hash + this.poids;
        hash = 37 * hash + Objects.hashCode(this.nomProduit);
        hash = 37 * hash + Objects.hashCode(this.description);
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
        final Produit other = (Produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (this.poids != other.poids) {
            return false;
        }
        if (!Objects.equals(this.nomProduit, other.nomProduit)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "nomProduit=" + nomProduit + ", poids=" + poids + ", description=" + description + '}';
    }
    
    
}

