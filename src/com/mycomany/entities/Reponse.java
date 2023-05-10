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

   
      private int idReponse,idReclamation;

   
    private String messager,emailuser;
    private String datereponse;
 private Reclamation idreclamation_id;
  
  
   public Reponse() {
    }

    public Reponse(int idReponse, String messager, String emailuser, String datereponse, Reclamation idreclamation_id) {
        this.idReponse = idReponse;
        this.messager = messager;
        this.emailuser = emailuser;
        this.datereponse = datereponse;
        this.idreclamation_id = idreclamation_id;
    }
    

    public Reponse(String messager, String emailuser, String datereponse, Reclamation idreclamation_id) {
        this.messager = messager;
        this.emailuser = emailuser;
        this.datereponse = datereponse;
        this.idreclamation_id = idreclamation_id;
    }

    public Reponse(String emailuser,String messager, Reclamation idreclamation_id) {
        this.messager = messager;
        this.emailuser = emailuser;
        this.idreclamation_id = idreclamation_id;
    }
    
     public Reponse(String emailuser,String messager) {
        this.messager = messager;
        this.emailuser = emailuser;
    }
    
     public Reponse(String email, String msg, int idreclamation_id) {
        this.messager = messager;
        this.emailuser = emailuser;
        this.idReclamation = idreclamation_id;
    }


    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }

    public String getEmailuser() {
        return emailuser;
    }

    public void setEmailuser(String emailuser) {
        this.emailuser = emailuser;
    }

    public String getDatereponse() {
        return datereponse;
    }

    public void setDatereponse(String datereponse) {
        this.datereponse = datereponse;
    }

    public Reclamation getIdreclamation_id() {
        return idreclamation_id;
    }

    public void setIdreclamation_id(Reclamation idreclamation_id) {
        this.idreclamation_id = idreclamation_id;
    }

    @Override
    public String toString() {
        return "Reponse{" + "idReponse=" + idReponse + ", idReclamation=" + idReclamation + ", messager=" + messager + ", emailuser=" + emailuser + ", datereponse=" + datereponse + ", idreclamation_id=" + idreclamation_id + '}';
    }
   
   
}



