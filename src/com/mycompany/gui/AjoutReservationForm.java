/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycomany.entities.Reservation;
import com.mycompany.services.ServiceProduit;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceReservation;
import java.text.SimpleDateFormat;

/**
 *
 * @author DELL
 */
public class AjoutReservationForm extends BaseForm {
    
    
    Form current;
    public AjoutReservationForm(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reservation");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Reservation", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Reserver", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Mes Reservation", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
         ListReservationForm a = new ListReservationForm(res);
           a.show();
                   mesListes.setSelected(true);

            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, liste),
                FlowLayout.encloseBottom(arrow)
        ));

        liste.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
        //    updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      
        TextField reference = new TextField("", "entrer Nom De Produit!!");
        reference.setUIID("reference");
        addStringValue("reference",reference);
        
       TextField prix = new TextField("", "entrer prix!!");
        prix.setUIID("prix");
        addStringValue("prix",prix);
        
        TextField poids = new TextField("", "entrer Poids!!");
        poids.setUIID("poids");
        addStringValue("poids",poids);
        
         TextField id_produit = new TextField("", "entrer id_produit!!");
        id_produit.setUIID("id_produit");
        addStringValue("id_produit",id_produit);
        
        TextField villeDepart = new TextField("", "entrer villeDepart!!");
        villeDepart.setUIID("villeDepart");
        addStringValue("villeDepart",villeDepart);
        
        TextField villeArrive = new TextField("", "entrer villeArrive!!");
        villeArrive.setUIID("villeArrive");
        addStringValue("villeArrive",villeArrive);
     
        
        
      
         
         
      

        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
             String errorMessage = validateFields(reference, prix, poids,id_produit,villeDepart,villeArrive);
            if (!errorMessage.isEmpty()) {
                Dialog.show("Erreur", errorMessage, "OK", null);
                return;
            }
         
            try {
                
                if(reference.getText().equals("") || prix.getText().equals("")|| poids.getText().equals("")|| villeDepart.getText().equals("")|| villeArrive.getText().equals("")|| id_produit.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    int prixEntier = Integer.parseInt(prix.getText());
                     int poidsEntier = Integer.parseInt(poids.getText());
                     int poidsId = Integer.parseInt(id_produit.getText());
                    

                    
                    //njibo iduser men session (current user)
                    Reservation r = new Reservation(String.valueOf(reference.getText()).toString(),
                            prixEntier,
                            poidsEntier,
                            poidsId,
                                  String.valueOf(villeDepart.getText()).toString(),
                             String.valueOf(villeArrive.getText()).toString()
                           
                                  );
                    
//                    System.out.println("data  reclamation == "+r);
//    
//               String etaat = r.getEtat();
//String[] badWords = {"fuck", "lol", "loool"}; // List of bad words
//
//for (String word : badWords) {
//    if (etaat.contains(word)) {
//        // Alert the user about the bad word
//        Dialog.show("Alerte", "Le mot " + word + " est interdit", "OK", null);
        //new AjoutReservationForm(res).show();
//        return; // Stop the method execution
//    } 
//}

// If the loop completes without returning, it means the message doesn't contain any bad words
ServiceReservation.getInstance().ajoutReservation(r);
Dialog.show("Confirmation", "Reservation Ajoutée avec succès", "OK", null);
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
//ba3d ajout net3adaw lel ListREclamationForm
new ListReservationForm(res).show();
refreshTheme(); //Actualisation
        
                
                } 
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
    }
private void addStringValue2( ComboBox comboBox, Container container) {
    
}


    
    
    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        
        add(createLineSeparator(0xeeeeee));
  
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
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
