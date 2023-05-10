/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author MSI
 */
public class Reponse {

   
      private int id;
    private String reponse;
    private String date;

  
  
   public Reponse() {
    }

    public Reponse(int id, String reponse) {
        this.id = id;
        this.reponse = reponse;
      
    }

  public Reponse( String reponse) {
      
        this.reponse = reponse;
      
    }
  
   public Reponse( String reponse,String date) {
      
        this.reponse = reponse;
         this.date = date;
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
    
      public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}



