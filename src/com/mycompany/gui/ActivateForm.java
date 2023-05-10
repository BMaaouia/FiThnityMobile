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

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUser;
import java.util.Date;
//import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ActivateForm extends BaseForm {

    TextField email;
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("oublier.png"), "LogoLabel"),
                        new Label("Forgot Password", "LogoLabel")
                )
        );
        
        
        
         email = new TextField("","saisir votre email",20,TextField.ANY);
        email.setSingleLineTextArea(false);
        
        Button valider = new Button("Valider");
        Button signIn = new Button("Retour se connecter?");
        signIn.addActionListener( e-> previous.showBack());//yarja3 lel window ely9ablha
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
        
                new FloatingHint(email),
                createLineSeparator(),
                valider,
                signIn
        );
        
        content.setScrollableY(true);
        
        add(BorderLayout.CENTER,content);
        
        valider.requestFocus();
        
        valider.addActionListener(e -> {
            
            InfiniteProgress ip = new InfiniteProgress();
            
            final Dialog ipDialog =  ip.showInfiniteBlocking();
            
            //houni bch nzido API SEND MAIL autrement bch n3ayto lel function ta3o mais 9bal njibo image oublier.png
            
            //sendMail(res);
            ServiceUser.getInstance().getPasswordByEmail(email.getText(), res);
            ipDialog.dispose();
            Dialog.show("Mot de passe","Nous avons envoyé le mot de passe a votre e-mail. Veuillez vérifier votre boite de réception",new Command("OK"));
            new SignInForm(res).show();
            refreshTheme();
            
        });
        
        
        
    }
    
    //sendMail
    
//    public void sendMail(Resources res) {
//String to = email.getText();
//        String from = "brmaaouia@gmail.com";
//        String host = "smtp.gmail.com";
//        String port = "587";
//        String username = "brmaaouia@gmail.com";
//        String password = "zufsoqnjziwwpwrn";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        // Get the default Session object.
//        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("Reset your password");
//
//            // Generate a random verification code and save it in a database or cache
//            String verificationCode = "55";
//            
//            // Store the verification code in a variable accessible by the verification code check method
//            String[] verificationCodeHolder = new String[1];
//            verificationCodeHolder[0] = verificationCode;
//
//            // Set the message content
//            message.setText("Here's your verification code: " + verificationCode);
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Verification email sent successfully.");
//
//
//
//            } catch (MessagingException mex) {
//            mex.printStackTrace();
//            }
//
//
//    }
    
}
