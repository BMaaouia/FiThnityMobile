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
import com.mycomany.entities.Reservation;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceReservation;
import java.text.SimpleDateFormat;

/**
 *
 * @author DELL
 */
public class ModifierReservationForm extends BaseForm {

    private Form current;

    public ModifierReservationForm(Resources res, Reservation r) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});

      TextField reference = new TextField(r.getReference(), "Reference", 20, TextField.ANY);
reference.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red


TextField prix = new TextField(String.valueOf(r.getPrix()), "Prix", 20, TextField.ANY);
prix.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green


TextField poids = new TextField(String.valueOf(r.getPoids()), "Poids", 20, TextField.ANY);
poids.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green


TextField idP = new TextField(String.valueOf(r.getId_produit()), "Poids", 20, TextField.ANY);
idP.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green

TextField vd = new TextField(r.getVilleDepart(), "ville depart", 20, TextField.ANY);
vd.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green

TextField va = new TextField(r.getVilleArrive(), "ville arrive", 20, TextField.ANY);
va.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue


        


        reference.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);
        poids.setSingleLineTextArea(true);
        idP.setSingleLineTextArea(true);
        vd.setSingleLineTextArea(true);
        va.setSingleLineTextArea(true);
    

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        btnModifier.addActionListener(l -> {
            String errorMessage = validateFields(reference, prix, poids,idP,vd,va);
            if (!errorMessage.isEmpty()) {
                Dialog.show("Erreur", errorMessage, "OK", null);
                return;
            }

       

            r.setReference(reference.getText());
            r.setPrix(Integer.parseInt(prix.getText()));
            r.setPoids(Integer.parseInt(poids.getText()));
            r.setId_produit(Integer.parseInt(idP.getText()));
            r.setVilleDepart(vd.getText());
            r.setVilleArrive(va.getText());
            

            if (ServiceReservation.getInstance().modifier(r)) {
                Dialog.show("Confirmation", "Reservation modifiée avec succès", "OK", null);
                new ListReservationForm(res).show();
            } else {
                Dialog.show("Confirmation", "Reservation modifiée avec succès", "OK", null);
                                new ListReservationForm(res).show();

            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListReservationForm(res).show();
        });

        Label l1 = new Label("Reference");
        Label l2 = new Label("Prix");
        Label l3 = new Label("Poids");
        Label l4 = new Label("IdP");
        Label l5 = new Label("ville Depart");
        Label l6 = new Label("ville Arrive");
    






    Container content = BoxLayout.encloseY(
            BorderLayout.center(reference).
                    add(BorderLayout.WEST, l1),
            BorderLayout.center(prix).
                    add(BorderLayout.WEST, l2),
            BorderLayout.center(poids).
                    add(BorderLayout.WEST, l3),
            BorderLayout.center(idP).
                    add(BorderLayout.WEST, l4),
                    BorderLayout.center(vd).
                    add(BorderLayout.WEST, l5),
                            BorderLayout.center(va).
                    add(BorderLayout.WEST, l6)
           
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}

public String validateFields(TextField reference, TextField prix, TextField poids, TextField id_produit1, TextField villeDepart, TextField villeArrive) {
    StringBuilder errorMessage = new StringBuilder();

    if (reference.getText().isEmpty()) {
        errorMessage.append("Le champ nom est obligatoire.\n");
    }
    if (prix.getText().isEmpty()) {
        errorMessage.append("Le champ sujet est obligatoire.\n");
    }
    if (poids.getText().isEmpty()) {
        errorMessage.append("Le champ etat est obligatoire.\n");
   
    }
     if (poids.getText().isEmpty()) {
        errorMessage.append("Le champ etat est obligatoire.\n");
   
    }
      if (villeDepart.getText().isEmpty()) {
        errorMessage.append("Le champ etat est obligatoire.\n");
   
    }
       if (villeArrive.getText().isEmpty()) {
        errorMessage.append("Le champ etat est obligatoire.\n");
   
    }
       if (id_produit1.getText().isEmpty()) {
        errorMessage.append("Le champ etat est obligatoire.\n");
   
    }
      
    return errorMessage.toString();
}


}

