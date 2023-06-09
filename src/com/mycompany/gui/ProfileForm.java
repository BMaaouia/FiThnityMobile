/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.SessionManager;
import com.mycompany.services.ServiceUser;
import java.io.IOException;

/**
 * The user profile form
 *
 * @author Shai Almog
 */


public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
       Image back = res.getImage("profile-background.jpg");
        //Image img = res.getImage(SessionManager.getUserImg());
        
        String imageName = "file:///C:/xampp/htdocs/GestionUser/GestionUser/public/uploads/user_images/" + SessionManager.getUserImg();
        Image img = null;
        if (imageName != null) {
            try {
                img = Image.createImage(imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(back);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        
        
        Button Modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        
        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        Label profilePic = new Label(img);
        profilePic.setUIID("PictureWhiteBackgrond");
        int picSize = Display.getInstance().getDisplayHeight() / 8; // adjust the size as needed
        profilePic.setPreferredW(picSize);
        profilePic.setPreferredH(picSize);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(1,
                            FlowLayout.encloseCenter(
                                profilePic
                            )
                    )
                )
        ));

        
        String fn = SessionManager.getUserFirstName();
        TextField firstname = new TextField(fn);
        firstname.setUIID("TextFieldBlack");
        addStringValue("FirstName",firstname);
        
        String ln = SessionManager.getUserLastName();
        TextField lastname = new TextField(ln);
        lastname.setUIID("TextFieldBlack");
        addStringValue("LastName",lastname);

        String e = SessionManager.getEmail();
        TextField email = new TextField(e, "E-Mail", 20, TextField.EMAILADDR);
        email.setEditable(false);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        String p = SessionManager.getPassowrd();
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        Supprimer.setUIID("Update");
        Modifier.setUIID("Edit");
        addStringValue("                                ",Supprimer);
        addStringValue("                                ",Modifier);
        
        Modifier.addActionListener((ActionEvent edit)->{
            InfiniteProgress ip = new InfiniteProgress();
            //final Dialog ipDlg = ip.showInifinieteBlooking();
            ServiceUser.EditUser(SessionManager.getId(),firstname.getText(), lastname.getText(), password.getText(), email.getText());
            SessionManager.setUserFirstName(firstname.getText());
            SessionManager.setUserLastName(lastname.getText());
            SessionManager.setPassowrd(password.getText());
            SessionManager.setEmail(email.getText());
            Dialog.show("Succes","Modifications des coordonnees avec succes","OK",null);
           // ipDlg.dispose();
            refreshTheme();

        });

            Supprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if(dig.show("Suppression","Vous voulez supprimer Votre Compte ?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                     }

                    if(ServiceUser.getInstance().deleteUser(SessionManager.getId())) {
                        new SignInForm(res).show();
                    }

            });

        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
