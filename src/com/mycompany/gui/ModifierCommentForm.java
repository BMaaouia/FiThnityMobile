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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Blog;
import com.mycomany.entities.Comment;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceBlog;
import com.mycompany.services.ServiceComment;

/**
 *
 * @author Lenovo
 */
public class ModifierCommentForm extends BaseForm {
    
    Form current;
    public ModifierCommentForm(Resources res , Comment c, Blog b) {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        TextField nom  = new TextField(c.getNom_prenom() , "nom_prenom" , 20 , TextField.ANY);
        TextField texte = new TextField(c.getText_comment() , "text_comment" , 20 , TextField.ANY);
              
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        
        
        
        
        
        nom.setUIID("NewsTopLine");
        texte.setUIID("NewsTopLine");
      
        
        nom.setSingleLineTextArea(true);
        texte.setSingleLineTextArea(true);
      
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           c.setNom_prenom(nom.getText());
           c.setText_comment(texte.getText());
        
           
                 
       
       //appel fonction modfier reclamation men service
       
       if(ServiceComment.getInstance().modifierComment(c)) { // if true
           new ListCommentForm(res, b).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCommentForm(res, b).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(texte),
                createLineSeparator(),
              
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
