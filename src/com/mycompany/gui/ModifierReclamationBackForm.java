///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.gui;
//
//import com.codename1.ui.Button;
//import com.codename1.ui.Container;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.Form;
//import com.codename1.ui.Label;
//import com.codename1.ui.TextField;
//import com.codename1.ui.Toolbar;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.util.Resources;
//import com.mycomany.entities.Reclamation;
//import com.mycompany.services.ServiceOffre;
//import java.text.SimpleDateFormat;
//
///**
// *
// * @author MSI
// */
//public class ModifierReclamationBackForm extends BaseForm {
//    
//    private Form current;
//
//    public ModifierReclamationBackForm(Resources res, Reclamation r) {
//        super("Modifier Reclamation", BoxLayout.y());
//
//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        setTitle("Modifier Reclamation");
//        getContentPane().setScrollVisible(false);
//
//        super.addSideMenu(res);
//
//      TextField nom = new TextField(r.getNom(), "Nom", 20, TextField.ANY);
//nom.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red
//
//TextField sujet = new TextField(r.getSujet(), "Sujet", 20, TextField.ANY);
//sujet.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green
//
//TextField etat = new TextField(r.getEtat(), "Etat", 20, TextField.ANY);
//etat.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue
//
//
//        
//
//
//        nom.setSingleLineTextArea(true);
//        sujet.setSingleLineTextArea(true);
//        etat.setSingleLineTextArea(true);
//    
//
//        Button btnModifier = new Button("Modifier");
//        btnModifier.setUIID("Button");
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//        btnModifier.addActionListener(l -> {
//            String errorMessage = validateFields(nom, sujet, etat);
//            if (!errorMessage.isEmpty()) {
//                Dialog.show("Erreur", errorMessage, "OK", null);
//                return;
//            }
//
//       
//
//            r.setNom(nom.getText());
//            r.setSujet(sujet.getText());
//            r.setEtat(etat.getText());
//            
//
//            if (ServiceOffre.getInstance().modifierReclamation(r)) {
//                Dialog.show("Confirmation", "Réclamation modifiée avec succès", "OK", null);
//                new ListReclamationBackForm(res).show();
//            } else {
//                Dialog.show("Erreur", "Erreur lors de la modification de la réclamation", "OK", null);
//            }
//        });
//
//        Button btnAnnuler = new Button("Annuler");
//        btnAnnuler.addActionListener(e -> {
//            new ListReclamationBackForm(res).show();
//        });
//
//        Label l1 = new Label("Nom");
//        Label l2 = new Label("sujet");
//        Label l3 = new Label("etat");
//     
//
//
//
//
//
//
//    Container content = BoxLayout.encloseY(
//            BorderLayout.center(nom).
//                    add(BorderLayout.WEST, l1),
//            BorderLayout.center(sujet).
//                    add(BorderLayout.WEST, l2),
//            BorderLayout.center(etat).
//                    add(BorderLayout.WEST, l3)
//           
//    );
//
//    content.setScrollableY(true);
//    add(content);
//
//    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
//}
//
//public String validateFields(TextField nom, TextField sujet, TextField etat) {
//    StringBuilder errorMessage = new StringBuilder();
//
//    if (nom.getText().isEmpty()) {
//        errorMessage.append("Le champ nom est obligatoire.\n");
//    }
//    if (sujet.getText().isEmpty()) {
//        errorMessage.append("Le champ prénom est obligatoire.\n");
//    }
//    if (etat.getText().isEmpty()) {
//        errorMessage.append("Le champ etat est obligatoire.\n");
//   
//    }
//   
//
//   
//   
//    return errorMessage.toString();
//}
//
//}
