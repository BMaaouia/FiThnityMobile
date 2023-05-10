/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycomany.entities.Reponse;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceReponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class ModifierReponseForm extends BaseForm {
    
    
    private Form current;

    public ModifierReponseForm(Resources res, Reponse r) {
        super("Modifier Reponse", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

 

TextField email = new TextField(r.getEmailuser(), "emailUser", 20, TextField.EMAILADDR);
email.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue



TextField message = new TextField(r.getMessager(), "messager", 20, TextField.ANY);
message.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to yellow

        

        email.setSingleLineTextArea(true);
        message.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        btnModifier.addActionListener(l -> {
            String errorMessage = validateFields(email,  message);
            if (!errorMessage.isEmpty()) {
                Dialog.show("Erreur", errorMessage, "OK", null);
                return;
            }

       
         
            r.setEmailuser(email.getText());
         
            r.setMessager(message.getText());
            r.setDatereponse(format.format(new Date()));
     

            if (ServiceReponse.getInstance().modifierReponse(r)) {
                Dialog.show("Confirmation", "Réponse modifiée avec succès", "OK", null);
                new ListReponseForm(res).show();
            } else {
                Dialog.show("Erreur", "Erreur lors de la modification de la reponse", "OK", null);
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListReponseForm(res).show();
        });

  
        Label l3 = new Label("Email");

         Label l5 = new Label("Reponse");
 






    Container content = BoxLayout.encloseY(
     
            BorderLayout.center(email).
                    add(BorderLayout.WEST, l3),
        
            
            BorderLayout.center(message).
                    add(BorderLayout.WEST, l5)
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}

public String validateFields( TextField email, TextField message) {
    StringBuilder errorMessage = new StringBuilder();

   
    if (email.getText().isEmpty()) {
        errorMessage.append("Le champ email est obligatoire.\n");
   
    }
    if (!email.getText().contains("@") || !email.getText().contains(".")) {
       
                errorMessage.append( "Veuillez entrer un email valide");

        }
    

    
    if (message.getText().isEmpty()) {
        errorMessage.append("Le champ Réponse est obligatoire.\n");
    }
   
    return errorMessage.toString();
}

    
    
    
    
}
