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
import com.mycomany.entities.Produit;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ServiceProduit {
     public static ServiceProduit instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceProduit getInstance() {
        if(instance == null )
            instance = new ServiceProduit();
        return instance ;
    }
    
    
    
    public ServiceProduit() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutProduit(Produit produit) {
        
        String url =Statics.BASE_URL+"/addProduitmobile?nomProduit="+produit.getNomProduit()+"&poids="+produit.getPoids()+"&description="+produit.getDescription(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Produit>affichageProduit() {
        ArrayList<Produit> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/pmobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapProduits.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Produit re = new Produit();
                        
                        //dima id fi codename one float 5outhouha
                        float id_produit = Float.parseFloat(obj.get("id_produit").toString());
                        
                        String nomProduit = obj.get("nomProduit").toString();
                        float poids =  Float.parseFloat(obj.get("poids").toString());

                        String description = obj.get("description").toString();
                        
                        
                        re.setId_produit((int)id_produit);
                        re.setNomProduit(nomProduit);
                        re.setPoids((int)poids);
                        re.setDescription(description);
                        
                        
//                        //Date 
//                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
//                        
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                        re.setDate(dateString);
                        
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
    
    
//    
//    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
//    
//    public Produit DetailRecalamation( int id , Reclamation reclamation) {
//        
//        String url = Statics.BASE_URL+"/detailReclamation?"+id;
//        req.setUrl(url);
//        
//        String str  = new String(req.getResponseData());
//        req.addResponseListener(((evt) -> {
//        
//            JSONParser jsonp = new JSONParser();
//            try {
//                
//                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//                
//                reclamation.setObjet(obj.get("obj").toString());
//                reclamation.setDescription(obj.get("description").toString());
//                reclamation.setEtat(Integer.parseInt(obj.get("etat").toString()));
//                
//            }catch(IOException ex) {
//                System.out.println("error related to sql :( "+ex.getMessage());
//            }
//            
//            
//            System.out.println("data === "+str);
//            
//            
//            
//        }));
//        
//              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//              return reclamation;
//        
//        
//    }
    
    
    //Delete 
    public boolean deleteProduit(int id_produit) {
String url = Statics.BASE_URL +"/removeProduit/" +id_produit;
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                String response = new String(req.getResponseData(), "UTF-8");
                if (response.equals("OK")) {
                    resultOk = true;
                } else {
                    resultOk = false;
                }
            } catch (UnsupportedEncodingException ex) {
                resultOk = false;
            }
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
}

    
    
    
    //Update 
    public boolean modifierProduit(Produit produit) {
        String url = Statics.BASE_URL + "/modifProduitmobile/" + produit.getId_produit()
            + "?nomProduit=" + produit.getNomProduit() 
            + "&poids=" + produit.getPoids() 
            + "&description=" + produit.getDescription();

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
    

}
