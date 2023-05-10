/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Reclamation;
import com.mycomany.entities.Reservation;
import com.mycomany.utils.Statics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ServiceReservation {
    public static ServiceReservation instance = null ;
         public ArrayList<Reservation> reservation;


        private ConnectionRequest req;
 
    
    public static boolean resultOk = true;

    
        
        
        
        public static ServiceReservation getInstance(){
    
    
        if(instance == null )
            instance = new ServiceReservation();
        return instance ;
    }

        
        
        
        public  ServiceReservation(){
        req = new ConnectionRequest();

        }
        
        public void ajoutReservation(Reservation reservation){
String url = Statics.BASE_URL + "/mobiiiiiiiileadd/?reference="+ reservation.getReference() +"&prix=" + reservation.getPrix() +"&poids=" + reservation.getPoids() +"&id_produit=" + reservation.getId_produit() +"&ville_depart=" + reservation.getVilleDepart() +"&ville_arrive=" + reservation.getVilleArrive();
            
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
        }
        
      


           public boolean deleteReservation(int id_r ) {
            String url = Statics.BASE_URL +"/removeReservation/"+id_r;

            req.setUrl(url);

            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {

                        req.removeResponseCodeListener(this);
                }
            });

            NetworkManager.getInstance().addToQueueAndWait(req);
            return  resultOk;

        }

 //Update 
          
        public boolean modifier(Reservation reservation) {
       
//String url = Statics.BASE_URL + "/modifReservation/" + reservation.getId_r()
//        + "?reference=" + reservation.getReference()
//        +"&prix=" + reservation.getPrix() +
//    "&poids=" + reservation.getPoids() +
//    
//    "&id_produit=" + reservation.getId_produit() +
//    "&ville_depart=" + reservation.getVilleDepart() +
//    "&ville_arrive=" + reservation.getVilleArrive();

String url = Statics.BASE_URL + "/modifReservation/" + reservation.getId_r()+"?reference"+reservation.getReference()+"&prix=" + reservation.getPrix() +"&poids=" + reservation.getPoids() +"&id_produit=" + reservation.getId_produit() +"&ville_depart=" + reservation.getVilleDepart() +"&ville_arrive=" + reservation.getVilleArrive();


           req.setUrl(url);

            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                    req.removeResponseListener(this);
                }
            });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

        }
       
         //affichage
    
    public ArrayList<Reservation>affichageReservation() {
        ArrayList<Reservation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/r";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReservations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReservations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reservation re = new Reservation();
                        
                        //dima id fi codename one float 5outhouha
                        float id_r = Float.parseFloat(obj.get("id_r").toString());
                        
                        String reference = obj.get("reference").toString();
                        float prix =  Float.parseFloat(obj.get("prix").toString());

                        float poids =  Float.parseFloat(obj.get("poids").toString());

                        String villeDepart = obj.get("villeDepart").toString();
                        String villeArrive = obj.get("villeArrive").toString();
                        
                        float id_produit = Float.parseFloat(obj.get("id_produit").toString());
                        
                        re.setId_r((int)id_r);
                        re.setReference(reference);
                        re.setPrix((int)prix);
                        re.setPoids((int)poids);
                        re.setVilleDepart(villeDepart);
                        re.setVilleArrive(villeArrive);
                                                re.setDateReser("");
                        
                        re.setId_produit((int)id_produit);
        
    
        
      //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
        
    
                        
    
}
                
