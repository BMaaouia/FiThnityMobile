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
import com.mycomany.entities.Vehicule;
import com.mycompany.services.ServiceEmployee;
import com.mycompany.services.ServiceVehicule;
import java.text.SimpleDateFormat;

/**
 *
 * @author MSI
 */
public class ModifierVehiculeForm extends BaseForm {
     private Form current;

    public ModifierVehiculeForm(Resources res, Vehicule v) {
        super("Modifier Vehicule", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Vehicule");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        TextField modele = new TextField(v.getModele(), "Modele", 20, TextField.ANY);
        modele.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red

        TextField immatriculation = new TextField(v.getImmatriculation(), "Immatriculation", 20, TextField.ANY);
        immatriculation.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green

        TextField categrie = new TextField(v.getCategorie(), "Categrie", 20, TextField.ANY);
        categrie.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue

        TextField etat = new TextField(String.valueOf(v.getEtat()), "Etat", 20, TextField.ANY);
        etat.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue

        TextField image = new TextField(v.getImageVehicule(), "Image", 20, TextField.ANY);
        image.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue



        


        modele.setSingleLineTextArea(true);
        immatriculation.setSingleLineTextArea(true);
        categrie.setSingleLineTextArea(true);
        etat.setSingleLineTextArea(true);
        image.setSingleLineTextArea(true);
        
    

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        btnModifier.addActionListener(l -> {
           
       

            v.setModele(modele.getText());
            v.setImmatriculation(immatriculation.getText());
            v.setCategorie(categrie.getText());
            v.setEtat(Boolean.parseBoolean(etat.getText()));
            v.setImageVehicule(image.getText());
     
            

            if (ServiceVehicule.getInstance().modifierVehicule(v)) {
                Dialog.show("Confirmation", "Vehicule modifiée avec succès", "OK", null);
                new ListVehiculeForm(res).show();
            } else {
                Dialog.show("Confirmation", "Vehicule modifiée avec succès", "OK", null);
                new ListVehiculeForm(res).show();
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListVehiculeForm(res).show();
        });

        Label l1 = new Label("modele");
        Label l2 = new Label("immatriculation");
        Label l3 = new Label("categrie");
        Label l4 = new Label("etat");
        Label l5 = new Label("image");






    Container content = BoxLayout.encloseY(
            BorderLayout.center(modele).
                    add(BorderLayout.WEST, l1),
            BorderLayout.center(immatriculation).
                    add(BorderLayout.WEST, l2),
            BorderLayout.center(categrie).
                    add(BorderLayout.WEST, l3),
            BorderLayout.center(etat).
                    add(BorderLayout.WEST, l4),
            BorderLayout.center(image).
                    add(BorderLayout.WEST, l5)
         
           
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}

public String validateFields(TextField vehicule) {
    StringBuilder errorMessage = new StringBuilder();

    if (vehicule.getText().isEmpty()) {
        errorMessage.append("Le champ nom est obligatoire.\n");
    }
   
   

   
   
    return errorMessage.toString();
}

}
