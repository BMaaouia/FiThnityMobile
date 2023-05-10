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
import com.mycomany.entities.Reponse;
import com.mycomany.utils.Statics;
import static com.mycompany.services.ServiceReclamation.resultOk;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class ServiceReponse {
    
      //singleton 
    public static ServiceReponse instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReponse getInstance() {
        if(instance == null )
            instance = new ServiceReponse();
        return instance ;
    }
    
    
    
    public ServiceReponse() {
        req = new ConnectionRequest();
        
    }
    
    
     //affichage
    
    public ArrayList<Reponse>affichageReponse() {
        ArrayList<Reponse> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reponse/afficherepmobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) 
                    {
                                                                        System.out.println("obj   : "+obj);

                        Reclamation re = new Reclamation();
                         Reponse rep = new Reponse();
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                       // System.out.println("id :: "+idReponse);
                        String reponse = obj.get("reponse").toString();
                       
                 
               

                        

                        rep.setId((int)id);
                        rep.setReponse(reponse);
                        //dateeeeeeeeeee
                         String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                       Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        rep.setDate(dateString);
                     
                       
                       // Create Reclamation object and assign idreclamation_id
                 //   re.setId((int) idReclamation);
                 
                    //    rep.setIdreclamation_id(re);
                         
            
                        //insert data into ArrayList result
                        result.add(rep);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    
    
    
  public void ajoutReponse(Reponse reponse) {
 Reclamation reclamation=new Reclamation();
    String url = Statics.BASE_URL+"/reponse/addreponsemobilee?reponse="+reponse.getReponse()+"&date="+reponse.getDate();

    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == "+str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    
    
    
        
    //Update 
    public boolean modifierReponse(Reponse reponse) {
String url = Statics.BASE_URL +"/reponse/modifierrepmobile/"+reponse.getId()+"?reponse="+reponse.getReponse();
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
    public boolean deleteReponse(int id ) {
        String url = Statics.BASE_URL +"/deleterepmobilee/"+(int)id;
        
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
