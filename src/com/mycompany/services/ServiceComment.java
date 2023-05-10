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
import com.mycomany.entities.Comment;
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
public class ServiceComment {
     public static ServiceComment instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
     public static ServiceComment getInstance() {
        if(instance == null )
            instance = new ServiceComment();
        return instance ;
    }
     
      public ServiceComment() {
        req = new ConnectionRequest();
        
    }
      
      
     //ajout 
    public void ajoutComment(Comment comment, Blog blog) {
        
        String url =Statics.BASE_URL+"/comment/ajoutC/"+blog.getId_blog()+"?nom_prenom="+comment.getNom_prenom()+"&text_comment="+comment.getText_comment();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    //affichage
    
    public ArrayList<Comment>affichageComment(int id) {
        ArrayList<Comment> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/comment/afficherjson/"+id;
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
                        Comment re = new Comment();
                        
                        //dima id fi codename one float 5outhouha
                        float idComment = Float.parseFloat(obj.get("idComment").toString());
                        
                        String nom_prenom = obj.get("nomPrenom").toString();
                        
                        String text_comment = obj.get("textComment").toString();
                       
                        
                        re. setId_comment((int)idComment);
                        re.setNom_prenom(nom_prenom);
                        re. setText_comment(text_comment);
                      
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
    public boolean deleteComment(int id_comment ) {
        String url = Statics.BASE_URL +"/comment/DeleteCM/"+id_comment;
        
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
    public boolean modifierComment(Comment c) {
        String url = Statics.BASE_URL +"/comment/updateCommentJSONmooo/"+c.getId_comment()+"?nom_prenom="+c.getNom_prenom()+"&text_comment="+c.getText_comment();
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
