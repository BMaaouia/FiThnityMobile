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
import com.mycomany.entities.Vehicule;
import com.mycomany.utils.Statics;
import static com.mycompany.services.ServiceEmployee.resultOk;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class ServiceVehicule {
    
      //singleton 
    public static ServiceVehicule instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceVehicule getInstance() {
        if(instance == null )
            instance = new ServiceVehicule();
        return instance ;
    }
    
    
    
    public ServiceVehicule() {
        req = new ConnectionRequest();
        
    }
    
    
     //affichage
    
    public ArrayList<Vehicule>affichageVehicule() {
        ArrayList<Vehicule> result = new ArrayList<>();
        
        String url = Statics.BASE_URL + "/vehiculemobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapVehicules = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapVehicules.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) 
                    {
                                                                        System.out.println("obj   : "+obj);

                        
                         Vehicule v = new Vehicule();
                        //dima id fi codename one float 5outhouha
                        float idV = Float.parseFloat(obj.get("idV").toString());
               //     String modele = obj.get("modele").toString();
                      String modele = obj.get("modele") != null ? obj.get("modele").toString() : "";
                        String immatriculation = obj.get("immatriculation") != null ? obj.get("immatriculation").toString() : "";
                        String categorie = obj.get("categorie") != null ? obj.get("categorie").toString() : "";
                        String etat = obj.get("etat") != null ? obj.get("etat").toString() : "";

                        String image_vehicule = obj.get("imageVehicule") != null ? obj.get("imageVehicule").toString() : "";






                        v.setidV((int) idV);
                    v.setModele(modele);
                        v.setImmatriculation(immatriculation);
                        v.setCategorie(categorie);
                       v.setEtat(true);
                        v.setImageVehicule(image_vehicule);
                     
                      
                         
            
                        //insert data into ArrayList result
                        result.add(v);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    
    
    
  public void ajoutVehicule(Vehicule vehicule) {
 
    //  String url = Statics.BASE_URL + "/new?&immatriculation=" + vehicule.getImmatriculation() + "&categorie=" + vehicule.getCategorie()   + "&imageVehicule=" + vehicule.getImageVehicule() ;
      String url = Statics.BASE_URL + "/newmobile?modele=" + vehicule.getModele() +"&immatriculation=" + vehicule.getImmatriculation() + "&categorie=" + vehicule.getCategorie()+ "&etat=" + vehicule.getEtat()   + "&imageVehicule=" + vehicule.getImageVehicule() ;

    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == "+str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    
    
    
        
    //Update 
    public boolean modifierVehicule(Vehicule vehicule) {
    String url = Statics.BASE_URL + "/modifvehicule/" + vehicule.getidV() + "?modele=" + vehicule.getModele() + "&immatriculation=" + vehicule.getImmatriculation() + "&categorie=" + vehicule.getCategorie() + "&etat=" + vehicule.getEtat()  + "&imageVehicule=" + vehicule.getImageVehicule() ;
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
    
    
    
       
    //Delete 
    public boolean deleteVehicule(int idV ) {
            String url = Statics.BASE_URL +"/delete/"+idV;
        
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
