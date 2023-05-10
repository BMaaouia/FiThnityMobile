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
import com.mycomany.entities.Demande;
import com.mycomany.utils.Statics;
import static com.mycompany.services.ServiceOffre.resultOk;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class ServiceDemande {
    
      //singleton 
    public static ServiceDemande instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceDemande getInstance() {
        if(instance == null )
            instance = new ServiceDemande();
        return instance ;
    }
    
    
    
    public ServiceDemande() {
        req = new ConnectionRequest();
        
    }
    
    
     //affichage
    
    public ArrayList<Demande>affichageDemande() {
        ArrayList<Demande> result = new ArrayList<>();
        
    String url = Statics.BASE_URL + "/demande/demande/mobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String, Object> demandesListJson = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) demandesListJson.get("root");
            if (list != null) {
                System.out.println("List size: " + list.size());
                for (Map<String, Object> obj : list) {
                    System.out.println("Parsing object: " + obj.toString());
                    Demande de = new Demande();
                    float id = Float.parseFloat(obj.get("id").toString());
                    String cin = obj.get("cin").toString();
                  
String competences = obj.get("competences") != null ? obj.get("competences").toString() : "";
String cv = obj.get("cv") != null ? obj.get("cv").toString() : "";
String lettremotivation = obj.get("lettremotivation") != null ? obj.get("lettremotivation").toString() : "";
String cartegrise = obj.get("cartegrise") != null ? obj.get("cartegrise").toString() : "";




             
                de.setId((int) id);
                de.setCin(cin);
                de.setCompetences(competences);
                de.setCv(cv);
                de.setLettremotivation(lettremotivation);
                de.setCartegrise(cartegrise);
               result.add(de);
            
                      
            
                        //insert data into ArrayList result
                      
                       
                    
                }}
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    
    
    
  public void ajoutDemande(Demande demande) {
 Reclamation reclamation=new Reclamation();
String url = Statics.BASE_URL + "/demande/new?cin=" + demande.getCin()+ "&competences=" + demande.getCompetences()+ "&cv=" + demande.getCv()+ "&lettremotivation=" + demande.getLettremotivation()+ "&cartegrise=" + demande.getCartegrise();

    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == "+str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    
    
    
        
//    //Update 
//    public boolean modifierReponse(Demande demande) {
//String url = Statics.BASE_URL +"/demande/modifierrepmobile/"+demande.getId()+"?demande="+demande.getReponse();
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
//                req.removeResponseListener(this);
//            }
//        });
//        
//    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//    return resultOk;
//        
//    }
    
    
    
       
    //Delete 
    public boolean deleteDemande(int id ) {
    String url = Statics.BASE_URL + "/demande/del/"+id;
        
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
    
    
    
}
