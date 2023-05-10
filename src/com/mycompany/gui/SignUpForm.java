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

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ext.filechooser.FileChooserNative;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.utils.Statics;
import com.mycompany.services.ServiceUser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Vector;




/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    String namePic;
    String path;
    String imageF="";
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField firstname = new TextField("", "Firstname", 20, TextField.ANY);
        TextField lastname = new TextField("", "Lastname", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField image = new TextField("","entrer Image!");
        Label limg=new Label("");
        //TextField tfImageName = new TextField("", "Image name");
        Button btnUpload = new Button("Upload");
        btnUpload.addActionListener((ActionEvent e1) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog( ".jpg, .jpeg,.png, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                   

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);;
                            limg.setIcon(logo);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                          
                                namePic = saveFileToDevice(file, ext);
                                imageF=namePic;
                                System.out.println("hadha howa"+namePic);
                           

                            revalidate();

                        
                    }
                    }
                        });
            }
                });
        
         
        
        
        
        
        firstname.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("SignUp");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(firstname),
                createLineSeparator(),
                new FloatingHint(lastname),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                btnUpload,
                createLineSeparator(),
                limg
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
            if(validateInputs(firstname,lastname,email,password,confirmPassword,namePic)==true){
                ServiceUser.getInstance().signup(firstname, lastname, password, email, confirmPassword,namePic, res);
                Dialog.show("Success","account is saved","OK",null);
                new SignInForm(res).show();
            }
        });
    }

    private String saveFileToDevice(String file, String ext) {
        URI uri;
        
        try {
            uri = new URI(file);
            String path = uri.getPath();
            int index = file.lastIndexOf("/");
            file = file.substring(index + 1);
            return file;
        } catch (URISyntaxException ex) {
            
        }
        return "hh";
    }
    
    
    
       public boolean validateInputs(TextField firstname_text,TextField lastname_text ,TextField email_text ,TextField password_text ,TextField confirm_password_text ,String namePic) {
    
        if (firstname_text.getText().isEmpty()) {
            Dialog.show("Erreur","Veuillez remplir votre prenom!","OK",null);
            return false;
        }
        
        

//        if (!firstname_text.getText().matches("[a-zA-Z]+")) {
//            Dialog.show("Erreur","Veuillez saisir correctement votre prenom!","OK",null);
//            return false;
//        }

        if (lastname_text.getText().isEmpty()) {
            Dialog.show("Erreur","Veuillez remplir votre nom!","OK",null);
            return false;
        }
//
//        if (!lastname_text.getText().matches("[a-zA-Z]+")) {
//            Dialog.show("Erreur","Veuillez saisir correctement votre nom!","OK",null);
//            return false;
//        }

        if (email_text.getText().isEmpty()) {
            Dialog.show("Erreur","Veuillez remplir votre email!","OK",null);
            return false;
        }
        
        

//        if (!email_text.getText().matches("[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}")) {
//           Dialog.show("Erreur","Veuillez saisir correctement votre email!","OK",null);
//            return false;
//        }

//        if(Us.verif_email(email_text.getText())==false){
//            
//            return false;
//
//        }

        if (password_text.getText().isEmpty()) {
            Dialog.show("Erreur","Veuillez remplir votre mot de passe!","OK",null);
            return false;
        }

//        if (!password_text.getText().matches("^(?=.*[0-9]).{8,}$")) {
//            Dialog.show("Erreur","Veuillez saisir correctement votre mot de passe!","OK",null);
//            return false;
//        }

        if (confirm_password_text.getText().isEmpty()) {
            Dialog.show("Erreur","Veuillez resaisir votre mot de passe!","OK",null);
            return false;
        }

        if (!password_text.getText().equals(confirm_password_text.getText())) {
            Dialog.show("Erreur","Veuillez resaisir correctement votre mot de passe!","OK",null);
            return false;
        }
        
        if(namePic==null){
            Dialog.show("Erreur","Veuillez choisir votre Profile Image!","OK",null);
            return false;
        }

        return true;
       }
    
    
}
