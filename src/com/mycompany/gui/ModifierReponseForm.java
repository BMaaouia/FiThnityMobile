/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
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
        setTitle("Modifier Reponse");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

      TextField reponse = new TextField(r.getReponse(), "Reponse", 20, TextField.ANY);
reponse.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red




        


        reponse.setSingleLineTextArea(true);
      
    

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        btnModifier.addActionListener(l -> {
            String errorMessage = validateFields(reponse);
            if (!errorMessage.isEmpty()) {
                Dialog.show("Erreur", errorMessage, "OK", null);
                return;
            }

       

            r.setReponse(reponse.getText());
     
            

            if (ServiceReponse.getInstance().modifierReponse(r)) {
                Dialog.show("Confirmation", "Reponse modifiée avec succès", "OK", null);
                new ListReponseForm(res).show();
            } else {
                Dialog.show("Erreur", "Erreur lors de la modification de la Reponse", "OK", null);
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListReponseForm(res).show();
        });

        Label l1 = new Label("Reponse");
 






    Container content = BoxLayout.encloseY(
            BorderLayout.center(reponse).
                    add(BorderLayout.WEST, l1)
         
           
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}

public String validateFields(TextField reponse) {
    StringBuilder errorMessage = new StringBuilder();

    if (reponse.getText().isEmpty()) {
        errorMessage.append("Le champ nom est obligatoire.\n");
    }
   
   

   
   
    return errorMessage.toString();
}

}
