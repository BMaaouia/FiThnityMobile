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
import com.mycomany.entities.Employee;
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
public class ServiceEmployee {
    
    //singleton 
    public static ServiceEmployee instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceEmployee getInstance() {
        if(instance == null )
            instance = new ServiceEmployee();
        return instance ;
    }
    
    
    
    public ServiceEmployee() {
        req = new ConnectionRequest();
        
    }
    
  public void ajoutEmployee(Employee employee) {
 
String url = Statics.BASE_URL + "/addEmployee/mobile?firstname_employee=" + employee.getFirstname_employee() + "&lastname_employee=" + employee.getLastname_employee() + "&email_employee=" + employee.getEmail_employee() + "&address_employee=" + employee.getAddress_employee() + "&id_v=" + employee.getId_vehicule();
    
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
     //   System.out.println("data == "+str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}


    
    
    //affichage
    
    public ArrayList<Employee>affichageEmployees() {
        ArrayList<Employee> result = new ArrayList<>();
        
    String url = Statics.BASE_URL + "/employee/mobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapEmployees = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapEmployees.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Employee e = new Employee();
                        
                        //dima id fi codename one float 5outhouha
                        float id_employee = Float.parseFloat(obj.get("id_employee").toString());
                    String firstname_employee = obj.get("firstname_employee").toString();
                    
                  

                String lastname_employee = obj.get("lastname_employee").toString();
                String email_employee = obj.get("email_employee").toString();
                String address_employee = obj.get("address_employee").toString();

                e.setId_employee((int) id_employee);
                e.setFirstname_employee(firstname_employee);
                e.setLastname_employee(lastname_employee);
                e.setEmail_employee(email_employee);
                e.setAddress_employee(address_employee);
                     
    
                        //insert data into ArrayList result
                        result.add(e);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    //Detail Employee bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Employee DetailRecalamation( int id , Employee employee) {
        
        String url = Statics.BASE_URL+"/detailEmployee?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
             //   employee.setObjet(obj.get("obj").toString());
             //   employee.setDescription(obj.get("description").toString());
            //    employee.setEtat(Integer.parseInt(obj.get("etat").toString()));
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return employee;
        
        
    }
    
    
    //Delete 
    public boolean deleteEmployee(int id_employee ) {
            String url = Statics.BASE_URL +"/removeEmployee/"+id_employee;
        
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
    public boolean modifierEmployee(Employee employee) {
    String url = Statics.BASE_URL + "/modifEmployeemobile/" + employee.getId_employee() + "?firstname_employee=" + employee.getFirstname_employee() + "&lastname_employee=" + employee.getLastname_employee() + "&email_employee=" + employee.getEmail_employee() + "&address_employee=" + employee.getAddress_employee() ;
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