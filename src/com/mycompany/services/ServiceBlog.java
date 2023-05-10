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
import com.mycomany.entities.Blog;
import com.mycomany.utils.Statics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.runtime.Debug.id;
/**
 *
 * @author user
 */
public class ServiceBlog {
    public static ServiceBlog instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
     public static ServiceBlog getInstance() {
        if(instance == null )
            instance = new ServiceBlog();
        return instance ;
    }
     
      public ServiceBlog() {
        req = new ConnectionRequest();
        
    }
    //ajout 
    public void ajoutBlog(Blog blog) {
        
        String url =Statics.BASE_URL+"/blog/ajout?titre_blog="+blog.getTitre_blog()+"&text_blog="+blog.getText_blog()+"&image_blog="+blog.getImage_blog(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
  
    
     //affichage
    
    public ArrayList<Blog>affichageReclamations() {
        ArrayList<Blog> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/blog/afficherjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Blog re = new Blog();
                        
                        //dima id fi codename one float 5outhouha
                        float idBlog = Float.parseFloat(obj.get("idBlog").toString());
                        
                        String titre_blog = obj.get("titre_blog").toString();
                        
                        String text_blog = obj.get("text_blog").toString();
                        String image_blog = obj.get("image_blog").toString();
                        
                        re. setId_blog((int)idBlog);
                        re.setTitre_blog(titre_blog);
                        re. setText_blog(text_blog);
                        re.setImage_blog(image_blog);
                        
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
      //Delete 
    public boolean deleteBlog(int id_blog ) {
        String url = Statics.BASE_URL +"/blog/DeleteM/"+id_blog;
        
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
