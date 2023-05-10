/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class Vehicule {
    
    private int idV;
    private String modele;
    private String immatriculation;
    private String categorie;
    private boolean etat;
    private String image_vehicule;
    
   public Vehicule() {
    }
         public Vehicule( int idV,String modele, String immatriculation, String categorie, boolean etat,String image_vehicule) {
        this.idV = idV;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.categorie = categorie;
        this.etat = etat; 
         this.image_vehicule = image_vehicule; 
      
    }
      public Vehicule( String modele, String immatriculation, String categorie, boolean etat,String image_vehicule) {
        
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.categorie = categorie;
        this.etat = etat;
         this.image_vehicule = image_vehicule; 
       
    }
      
//    public Vehicule(int id, String modele, String immatriculation, String categorie, boolean etat, String image) {
//        this.id = id;
//        this.modele = modele;
//        this.immatriculation = immatriculation;
//        this.categorie = categorie;
//        this.etat = etat;
//        this.image = image;
//    }

    public int getidV() {
        return idV;
    }

    public void setidV(int idV) {
        this.idV = idV;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

   public String getImageVehicule() {
        return image_vehicule;
    }

    public void setImageVehicule(String image_vehicule) {
        this.image_vehicule = image_vehicule;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.idV;
        hash = 47 * hash + Objects.hashCode(this.modele);
        hash = 47 * hash + Objects.hashCode(this.immatriculation);
        hash = 47 * hash + Objects.hashCode(this.categorie);
        hash = 47 * hash + Objects.hashCode(this.etat);
        hash = 47 * hash + Objects.hashCode(this.image_vehicule);
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
        final Vehicule other = (Vehicule) obj;
        if (this.idV != other.idV) {
            return false;
        }
        if (!Objects.equals(this.modele, other.modele)) {
            return false;
        }
        if (!Objects.equals(this.immatriculation, other.immatriculation)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return  " || modele=" + modele + " || immatriculation=" + immatriculation + " || categorie=" + categorie + " || etat=" + etat  ;
    }

 
}

    

