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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Offre;
import com.mycompany.services.ServiceOffre;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ChatGPT
 */
public class ModifierOffreForm extends BackForm {

    private Form current;

    public ModifierOffreForm(Resources res, Offre o) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});

//         ComboBox<String> metier = new ComboBox<>("Chauffeur-livreur", "Agent administratif", "Gestionnaire des vehicules","Agent de service client");
//         metier.setUIID("metier");
//        addStringValue("metier",metier);
//        ComboBox<String> ville = new ComboBox<>("Tunis", "Sfax ", "Ettadhamen","Bizerte","Gabès","Ariana","Mohamedia","Gafsa");
//        ville.setUIID("ville");
//        addStringValue("ville",ville);
//        TextField secteur = new TextField("", "entrer secteur!!");
//        secteur.setUIID("secteur");
//        addStringValue("secteur",secteur);
//        
//          TextField nombredeposte = new TextField("", "entrer nombredeposte!!");
//        nombredeposte.setUIID("nombredeposte");
//        addStringValue("nombredeposte",nombredeposte);
//         TextField salaire = new TextField("", "entrer salaire!!");
//        salaire.setUIID("salaire");
//        addStringValue("salaire",salaire);
        
      TextField metier = new TextField(o.getMetier(), "Metier", 20, TextField.ANY);
metier.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red

TextField ville = new TextField(o.getVille(), "ville", 20, TextField.ANY);
ville.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green

TextField secteur = new TextField(o.getSecteur(), "secteur", 20, TextField.ANY);
secteur.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue
TextField nombredeposte = new TextField(String.valueOf(o.getNombredeposte()), "nombredeposte", 20, TextField.ANY);
nombredeposte.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green

TextField salaire = new TextField(String.valueOf(o.getsalaire()), "salaire", 20, TextField.ANY);
salaire.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue


        


        metier.setSingleLineTextArea(true);
        ville.setSingleLineTextArea(true);
        secteur.setSingleLineTextArea(true);
            nombredeposte.setSingleLineTextArea(true);
        salaire.setSingleLineTextArea(true);


        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        btnModifier.addActionListener(l -> {
//            S//tring errorMessage = validateFields(metier, sujet, etat);
//            if (!errorMessage.isEmpty()) {
//                Dialog.show("Erreur", errorMessage, "OK", null);
//                return;
//            }

       

            
            
            o.setMetier(metier.getText());
    o.setSecteur(secteur.getText());
    o.setVille(ville.getText());
    o.setNombredeposte(Integer.parseInt(nombredeposte.getText()));
    o.setsalaire(Integer.parseInt(salaire.getText()));

            if (ServiceOffre.getInstance().modifierOffre(o)) {
                Dialog.show("Confirmation", "Réclamation modifiée avec succès", "OK", null);
                new ListOffreForm(res).show();
            } else {
                Dialog.show("Erreur", "Erreur lors de la modification de la réclamation", "OK", null);
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListOffreForm(res).show();
        });

        Label l1 = new Label("Metier");
        Label l2 = new Label("secteur");
        Label l3 = new Label("Ville");
         Label l4 = new Label("Nombre de poste");
        Label l5 = new Label("Salaire");
    






    Container content = BoxLayout.encloseY(
            BorderLayout.center(metier).
                    add(BorderLayout.WEST, l1),
            BorderLayout.center(secteur).
                    add(BorderLayout.WEST, l2),
            BorderLayout.center(ville).
                    add(BorderLayout.WEST, l3),
            
            BorderLayout.center(nombredeposte).
                    add(BorderLayout.WEST, l4),
               BorderLayout.center(salaire).
                    add(BorderLayout.WEST, l5)
            
           
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}

public String validateFields(TextField nom, TextField sujet, TextField etat) {
    StringBuilder errorMessage = new StringBuilder();

    if (nom.getText().isEmpty()) {
        errorMessage.append("Le champ nom est obligatoire.\n");
    }
    if (sujet.getText().isEmpty()) {
        errorMessage.append("Le champ prénom est obligatoire.\n");
    }
    if (etat.getText().isEmpty()) {
        errorMessage.append("Le champ etat est obligatoire.\n");
   
    }
   

   
   
    return errorMessage.toString();
}


}

