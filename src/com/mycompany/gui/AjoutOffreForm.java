/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Offre;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceOffre;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AjoutOffreForm extends BaseForm {
    
    
    Form current;
    public AjoutOffreForm(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Offre");
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
        RadioButton mesListes = RadioButton.createToggle("Mes Offres", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("offre", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Mes Offres", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        ListOffreForm a = new ListOffreForm(res);
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
        
      
        ComboBox<String> metier = new ComboBox<>("Chauffeur-livreur", "Agent administratif", "Gestionnaire des vehicules","Agent de service client");
         metier.setUIID("metier");
        addStringValue("metier",metier);
        ComboBox<String> ville = new ComboBox<>("Tunis", "Sfax ", "Ettadhamen","Bizerte","Gabès","Ariana","Mohamedia","Gafsa");
        ville.setUIID("ville");
        addStringValue("ville",ville);
        TextField secteur = new TextField("", "entrer secteur!!");
        secteur.setUIID("secteur");
        addStringValue("secteur",secteur);
        
          secteur.addDataChangedListener((i, ii) -> {
    if (secteur.getText().length() < 3) {
        ToastBar.showErrorMessage("Le champ secteur doit contenir au moins 3 caractères");
    }
});
        
          TextField nombredeposte = new TextField("", "entrer nombredeposte!!");
        nombredeposte.setUIID("nombredeposte");
        addStringValue("nombredeposte",nombredeposte);
        
         nombredeposte.addDataChangedListener((i, ii) -> {
    try {
        int nbPoste = Integer.parseInt(nombredeposte.getText());
        if (nbPoste <= 0) {
            ToastBar.showErrorMessage("Le nombre de postes doit être supérieur à zéro");
        }
    } catch (NumberFormatException e) {
        ToastBar.showErrorMessage("Le champ nombre de poste doit contenir un nombre entier");
    }
});
        
         TextField salaire = new TextField("", "entrer salaire!!");
        salaire.setUIID("salaire");
        addStringValue("salaire",salaire);
         salaire.addDataChangedListener((i, ii) -> {
    try {
        int sal = Integer.parseInt(salaire.getText());
        if (sal <= 0) {
            ToastBar.showErrorMessage("Le salaire doit être supérieur à zéro");
        }
    } catch (NumberFormatException e) {
        ToastBar.showErrorMessage("Le champ salaire doit contenir un nombre entier");
    }
});
      

        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
             String errorMessage = validateFields(secteur, nombredeposte, salaire);
            if (!errorMessage.isEmpty()) {
                Dialog.show("Erreur", errorMessage, "OK", null);
                return;
            }
         
            try {
                
                if(secteur.getText().equals("") || nombredeposte.getText().equals("")|| salaire.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
    Offre offre = new Offre(
        String.valueOf(metier.getSelectedItem()),
        String.valueOf(ville.getSelectedItem()),
        String.valueOf(secteur.getText()),
        Integer.parseInt(nombredeposte.getText()),
        Integer.parseInt(salaire.getText()),
        format.format(new Date())
    );
                    
                   // System.out.println("data  reclamation == "+);
    
     
// If the loop completes without returning, it means the message doesn't contain any bad words
ServiceOffre.getInstance().ajoutOffre(offre);
Dialog.show("Confirmation", "Réclamation Ajoutée avec succès", "OK", null);
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
//ba3d ajout net3adaw lel ListREclamationForm
new ListOffreForm(res).show();
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
    
   
   public String validateFields(TextField nom, TextField sujet, TextField etat) {
    StringBuilder errorMessage = new StringBuilder();

    if (nom.getText().isEmpty()) {
        errorMessage.append("Le champ nom est obligatoire.\n");
    }
    if (sujet.getText().isEmpty()) {
        errorMessage.append("Le champ sujet est obligatoire.\n");
    }
    if (etat.getText().isEmpty()) {
        errorMessage.append("Le champ etat est obligatoire.\n");
   
    }
      
    return errorMessage.toString();
}
    
}
