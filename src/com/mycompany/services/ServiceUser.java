/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.User;
import com.mycomany.utils.Statics;

import com.mycompany.gui.ListUsersForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class ServiceUser {
    
    
  //singleton 
    public static ServiceUser instance = null ;
    
    public static boolean resultOk = true;
    String json;
    private Resources theme;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance ;
    }
    
    
    
    public ServiceUser() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField firstname, TextField lastname, TextField password,TextField email,TextField confirmPassword ,String path, Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/user/signup?firstName="+firstname.getText().toString()+"&lastName="+lastname.getText().toString()+"&email="+email.getText().toString()+
                "&password="+password.getText().toString()+"&userImg="+path;
                
        
        req.setUrl(url);
       
        //Control saisi
        if(firstname.getText().equals(" ") && lastname.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
   
    
    
    //SignIn
    
    public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/login?email="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("userId").toString());
                
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("userPassword").toString());
                SessionManager.setUserFirstName(user.get("userFirstname").toString());
                SessionManager.setUserLastName(user.get("userLastname").toString());
                SessionManager.setEmail(user.get("userEmail").toString());
                
                //photo 
                
                if(user.get("userImg") != null)
                    
                    SessionManager.setUserImg(user.get("userImg").toString());
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                   // new AjoutReclamationForm(rs).show();
                    if(user.get("admin").toString().equals("1.0")){
                        new ListUsersForm(rs).show();
                    }
                    else{
                        //System.out.println(user.get("admin").toString()) ;
                       new ProfileForm(rs).show();
                    }
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

  //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
    public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"/deleteU/"+(int)id;
        
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
    
    public static void EditUser(int id, String UserFirstName,String UserLastName, String Email) {
        String url = Statics.BASE_URL+"/edit/user?id="+id+"&firstname="+UserFirstName+"&lastname="+UserLastName+"&email="+Email;
        MultipartRequest req = new MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id",String.valueOf(SessionManager.getId()));
        req.addArgument("firstname",UserFirstName);
        req.addArgument("lastname",UserLastName);
        req.addArgument("email",Email);
        System.out.println(Email);
        req.addResponseListener((response)-> {
            
            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
            //if(s.equals("success")){}
            //else {
                //Dialog.show("Erreur","Echec de modification", "Ok", null);
            //}
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    //affichage
    
    public ArrayList<User>affichageUsers() {
        ArrayList<User> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/userss";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapUsers.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        User u = new User();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("userId").toString());
                        
                        String firstname = obj.get("userFirstname").toString();
                        
                        String lastname = obj.get("userLastname").toString();
                        String email = obj.get("userEmail").toString();
                        
                        u.setUserId((int)id);
                        u.setUserFirstname(firstname);
                        u.setUserLastname(lastname);
                        u.setUserEmail(email);
                        
                        
                        if(!obj.get("userFirstname").toString().equals("admin")){
                        
                        //insert data into ArrayList result
                        result.add(u);
                       
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

}
