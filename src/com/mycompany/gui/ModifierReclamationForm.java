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
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ChatGPT
 */
public class ModifierReclamationForm extends BaseForm {

    private Form current;
 // use two network threads instead of one
    public ModifierReclamationForm(Resources res, Reclamation r) {
        super("Modifier Reclamation", BoxLayout.y());
        //Appel affichage methode

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

      TextField nom = new TextField(r.getNom(), "Nom", 20, TextField.ANY);
nom.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red

TextField prenom = new TextField(r.getPrenom(), "Prénom", 20, TextField.ANY);
prenom.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green

TextField email = new TextField(r.getEmail(), "Email", 20, TextField.EMAILADDR);
email.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue

TextField numtel = new TextField(String.valueOf(r.getNumtel()), "Numéro de téléphone", 20, TextField.NUMERIC);
numtel.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to magenta

TextField message = new TextField(r.getMessage(), "Message", 20, TextField.ANY);
message.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to yellow

        

ComboBox<String> typer = new ComboBox<>("Problèmes achat en ligne", "Problèmes de navigation sur le site", "Problèmes de qualité de produits ou de services", "Problèmes de confidentialité et de sécurité des données");        typer.setSelectedItem(r.getTyper());

        nom.setSingleLineTextArea(true);
        prenom.setSingleLineTextArea(true);
        email.setSingleLineTextArea(true);
        numtel.setSingleLineTextArea(true);
        message.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        btnModifier.addActionListener(l -> {
            String errorMessage = validateFields(nom, prenom, email, numtel, message);
            if (!errorMessage.isEmpty()) {
                Dialog.show("Erreur", errorMessage, "OK", null);
                return;
            }

            int selectedIndex = typer.getSelectedIndex();
            String domaine = selectedIndex >= 0 ? typer.getModel().getItemAt(selectedIndex) : "";

            r.setNom(nom.getText());
            r.setPrenom(prenom.getText());
            r.setEmail(email.getText());
            r.setNumtel(Integer.parseInt(numtel.getText()));
            r.setMessage(message.getText());
            r.setDate(format.format(new Date()));
            r.setTyper(domaine);

            if (ServiceReclamation.getInstance().modifierReclamation(r)) {
                Dialog.show("Confirmation", "Réclamation modifiée avec succès", "OK", null);
                new ListReclamationForm(res).show();
            } else {
                Dialog.show("Erreur", "Erreur lors de la modification de la réclamation", "OK", null);
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListReclamationForm(res).show();
        });

        Label l1 = new Label("Nom");
        Label l2 = new Label("Prénom");
        Label l3 = new Label("Email");
        Label l4 = new Label("Telephone");
         Label l5 = new Label("Reclamation");
          Label l6 = new Label("Type de reclamation");






    Container content = BoxLayout.encloseY(
            BorderLayout.center(nom).
                    add(BorderLayout.WEST, l1),
            BorderLayout.center(prenom).
                    add(BorderLayout.WEST, l2),
            BorderLayout.center(email).
                    add(BorderLayout.WEST, l3),
            BorderLayout.center(numtel).
                    add(BorderLayout.WEST, l4),
            BorderLayout.center(typer).
                    add(BorderLayout.WEST, l5),
            BorderLayout.center(message).
                    add(BorderLayout.WEST, l6)
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}

public String validateFields(TextField nom, TextField prenom, TextField email, TextField numtel, TextField message) {
    StringBuilder errorMessage = new StringBuilder();

    if (nom.getText().isEmpty()) {
        errorMessage.append("Le champ nom est obligatoire.\n");
    }
    if (prenom.getText().isEmpty()) {
        errorMessage.append("Le champ prénom est obligatoire.\n");
    }
    if (email.getText().isEmpty()) {
        errorMessage.append("Le champ email est obligatoire.\n");
   
    }
    if (!email.getText().contains("@") || !email.getText().contains(".")) {
       
                errorMessage.append( "Veuillez entrer un email valide");

        }
    if (numtel.getText().isEmpty()) {
        errorMessage.append("Le champ numéro de téléphone est obligatoire.\n");
    }else {
        String numtelStr = numtel.getText();
       if (numtelStr.length() != 8 ) {
    errorMessage.append("Le numéro de téléphone doit contenir exactement 8 chiffres.\n");
}

    }
    if (message.getText().isEmpty()) {
        errorMessage.append("Le champ message est obligatoire.\n");
    }
   
    return errorMessage.toString();
}


}

