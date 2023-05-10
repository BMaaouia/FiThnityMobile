/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
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
import com.mycomany.entities.Produit;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceProduit;
import com.mycompany.services.ServiceReclamation;
import java.text.SimpleDateFormat;

/**
 *
 * @author DELL
 */
public class ModifierProduitForm extends BaseForm {

    private Form current;

    public ModifierProduitForm(Resources res, Produit r) {
        super("Modifier Produit", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Produit");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

      TextField nom = new TextField(r.getNomProduit(), "Nom de Produit", 20, TextField.ANY);
nom.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red

TextField poids = new TextField(String.valueOf(r.getPoids()), "Poids", 20, TextField.ANY);
poids.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green


TextField description = new TextField(r.getDescription(), "Description", 20, TextField.ANY);
description.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue


        


        nom.setSingleLineTextArea(true);
        poids.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
    

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

       btnModifier.addActionListener(l -> {
    String errorMessage = validateFields(nom, poids, description);
    if (!errorMessage.isEmpty()) {
        Dialog.show("Erreur", errorMessage, "OK", null);
        return;
    }

    r.setNomProduit(nom.getText());
    r.setPoids(Integer.parseInt(poids.getText()));
    r.setDescription(description.getText());

    if (ServiceProduit.getInstance().modifierProduit(r)) {
        Dialog.show("Confirmation", "Produit modifié avec succès", "OK", null);
        new ListProduitForm(res).show();
    } else {
        Dialog.show("Confirmation", "Produit modifié avec succès", "OK", null);
        new ListProduitForm(res).show();
    }
});


        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListProduitForm(res).show();
        });

        Label l1 = new Label("Nom");
        Label l2 = new Label("Poids");
        Label l3 = new Label("Description");
    






    Container content = BoxLayout.encloseY(
            BorderLayout.center(nom).
                    add(BorderLayout.WEST, l1),
            BorderLayout.center(poids).
                    add(BorderLayout.WEST, l2),
            BorderLayout.center(description).
                    add(BorderLayout.WEST, l3)
           
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}

public String validateFields(TextField nom, TextField poids, TextField description) {
    StringBuilder errorMessage = new StringBuilder();

    if (nom.getText().isEmpty()) {
        errorMessage.append("Le champ nom est obligatoire.\n");
    }
    if (poids.getText().isEmpty()) {
        errorMessage.append("Le champ poids est obligatoire.\n");
    }
    if (description.getText().isEmpty()) {
        errorMessage.append("Le champ description est obligatoire.\n");
   
    }
   

   
   
    return errorMessage.toString();
}


}

