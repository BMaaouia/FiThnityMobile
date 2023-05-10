/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.User;
import com.mycomany.entities.User;
import com.mycomany.entities.User;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maaouia
 */
public class ListUsersForm extends BaseForm {
    private MultiList userList;
    private ArrayList<Map<String, Object>> userItems;

    public ListUsersForm(Resources res) {
        //Appel affichage methode
        ArrayList<User>list = ServiceUser.getInstance().affichageUsers();
        
        for(User u : list ) {
             String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,u,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
    }

    public void setUserList(ArrayList<User> users) {
        userItems.clear();
        for (User user : users) {
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("Line1", user.getUserFirstname());
            userMap.put("Line2", user.getUserEmail());
            userItems.add(userMap);
        }
        userList.setModel(new DefaultListModel<Map<String, Object>>(userItems));
    }
    
    private void addButton(Image img,User u , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label firstnameTxt = new Label("FirstName : "+u.getUserFirstname(),"NewsTopLine2");
        Label lastnameTxt = new Label("LastName : "+u.getUserLastname(),"NewsTopLine2");
        Label emailTxt = new Label("Email : "+u.getUserEmail(),"NewsTopLine2" );
        
        createLineSeparator();
        
       
       
        
        
        
       
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(firstnameTxt),
                BoxLayout.encloseX(lastnameTxt),
                BoxLayout.encloseX(emailTxt)));
             
        
        
        
        add(cnt);
    }
}

    

