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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Offre;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceOffre {
    
    //singleton 
    public static ServiceOffre instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceOffre getInstance() {
        if(instance == null )
            instance = new ServiceOffre();
        return instance ;
    }
    
    
    
    public ServiceOffre() {
        req = new ConnectionRequest();
        
    }
    
  public void ajoutOffre(Offre offre) {
 
String url = Statics.BASE_URL + "/offre/new?metier=" + offre.getMetier() + "&secteur=" + offre.getSecteur() + "&ville=" + offre.getVille() + "&nombredeposte=" + offre.getNombredeposte() + "&salaire=" + offre.getsalaire();
    
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
     //   System.out.println("data == "+str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}


    
    
    //affichage
    
    public ArrayList<Offre>affichageOffres() {
        ArrayList<Offre> result = new ArrayList<>();
        
    String url = Statics.BASE_URL + "/offre/offres";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                  Map<String, Object> offresListJson = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) offresListJson.get("root");
            if (list != null) {
                System.out.println("List size: " + list.size());
                for (Map<String, Object> obj : list) {
                    System.out.println("Parsing object: " + obj.toString());
                    Offre re = new Offre();
                    float id = Float.parseFloat(obj.get("id").toString());
                    String metier = obj.get("metier").toString();
                  

                String secteur = obj.get("secteur").toString();
                String ville = obj.get("ville").toString();
                double nombredeposte = Double.parseDouble(obj.get("nombredeposte").toString());
                double salaire = Double.parseDouble(obj.get("salaire").toString());
//// Extraction et formatage de la date
               String dateString = obj.get("dateoffre").toString();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


                // Ajout des valeurs parsées à l'objet Offre
                re.setId((int) id);
                re.setMetier(metier);
                re.setSecteur(secteur);
                re.setVille(ville);
                re.setNombredeposte((int) nombredeposte);
                re.setsalaire((int) salaire);
                re.setDateoffre(dateString);
                result.add(re);
            }
        }
                    
                    
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    //Detail Offre bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Offre DetailRecalamation( int id , Offre offre) {
        
        String url = Statics.BASE_URL+"/detailOffre?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
             //   offre.setObjet(obj.get("obj").toString());
             //   offre.setDescription(obj.get("description").toString());
            //    offre.setEtat(Integer.parseInt(obj.get("etat").toString()));
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return offre;
        
        
    }
    
    
    //Delete 
    public boolean deleteOffre(int id ) {
    String url = Statics.BASE_URL + "/offre/delete?id=" + id;
        
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
    public boolean modifierOffre(Offre offre) {
String url = Statics.BASE_URL +"/offre/modifier?id="+offre.getId()+"&metier="+offre.getMetier()+"&secteur="+offre.getSecteur()+"&ville="+offre.getVille()+"&nombredeposte="+offre.getNombredeposte()+"&salaire="+offre.getsalaire();
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
    
    public ArrayList<Offre> getOffresByDate(String dateStr,ArrayList<Offre> offres) {
             
        try {
            ArrayList<Offre> offresByDate = new ArrayList<>();
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdfInput.parse(dateStr);
            String formattedDate = sdfOutput.format(date);
            for (Offre o : offres) {
                if (o.getDateoffre().equals(formattedDate)) {
                    offresByDate.add(o);
                }
            }
            if (!offresByDate.isEmpty()) {
                offres = offresByDate;
                //refreshListeOffre();
            }
            return offresByDate;
        } catch (java.text.ParseException ex) {
           // Logger.getLogger(ListeOffreForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offres;
 

 }
    
    

    
    
    
    
    
    
    
    
    
    
    
}