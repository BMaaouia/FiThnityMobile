

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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycomany.entities.Demande;
import com.mycompany.services.ServiceOffre;
import com.mycompany.services.ServiceDemande;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class AjoutDemandeForm extends BaseForm {
     Form current;
     Demande rec= new Demande();
       private Reclamation reclamation;
    public AjoutDemandeForm(Resources res ) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
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
        RadioButton mesListes = RadioButton.createToggle("Mes Demandes", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Demande", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        ListDemandeForm a = new ListDemandeForm(res);
           a.show();
                   mesListes.setSelected(true);

            refreshTheme();
        });
        
         partage.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        ListDemandeForm a = new ListDemandeForm(res);
           a.show();
                   partage.setSelected(true);

            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        liste.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      
        
       
        
       
        
          
        
        
        TextField cin = new TextField("", "Entrer votre CIN");
    cin.setUIID("cin");
    addStringValue("cin",cin);
    
    TextField competences = new TextField("", "Entrer vos compétences");
    competences.setUIID("competences");
        addStringValue("competences",competences);

    
    TextField cv = new TextField("", "Entrer le lien de votre CV");
cv.setUIID("cv");
        addStringValue("cv",cv);



Button ajouterCvBtn = new Button("Ajouter CV");
ajouterCvBtn.addActionListener(e -> {
    Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
        if (ev != null && ev.getSource() != null) {
            String filePath = (String) ev.getSource();
            cv.setText(filePath);
        }
    }, Display.GALLERY_IMAGE);
});
        addStringValue("", ajouterCvBtn);


TextField lettreMotivation = new TextField("", "Entrer le lien de votre lettre de motivation");
lettreMotivation.setUIID("lettreMotivation");
addStringValue("lettreMotivation",lettreMotivation);
Button ajouterLettreMotivationBtn = new Button("Ajouter lettre de motivation");
ajouterLettreMotivationBtn.addActionListener(e -> {
    Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
        if (ev != null && ev.getSource() != null) {
            String filePath = (String) ev.getSource();
            lettreMotivation.setText(filePath);
        }
     }, Display.GALLERY_IMAGE);
});
addStringValue("", ajouterLettreMotivationBtn);



    
    TextField carteGrise = new TextField("", "Sélectionner la carte grise");
    carteGrise.setUIID("carteGrise");
    addStringValue("carteGrise",carteGrise);

   Button ajouterCarteGriseBtn = new Button("Ajouter Carte grise");
ajouterCarteGriseBtn.addActionListener(e -> {
    Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
        if (ev != null && ev.getSource() != null) {
            String filePath = (String) ev.getSource();
            carteGrise.setText(filePath);
        }
    }, Display.GALLERY_IMAGE);
});
addStringValue("", ajouterCarteGriseBtn);


               
        ComboBox<String> offre = new ComboBox<>("Chauffeur-livreur", "Agent administratif", "Gestionnaire des vehicules","Agent de service client");
 offre.setUIID("metier");
        
        addStringValue("", offre);



        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            // String errorMessage = validateFields();
//            if (!errorMessage.isEmpty()) {
//                Dialog.show("Erreur", errorMessage, "OK", null);
//                return;
//            }
//         
            try {
                
                if( cin.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
               
Demande demande = new Demande(
               String.valueOf(cin.getText()),
                competences.getText(),
                cv.getText(),
                lettreMotivation.getText(),
                carteGrise.getText(),
                String.valueOf(offre.getSelectedItem())
            );                    
            //        System.out.println("data  repooooooooooonse == "+d);
    
           

// If the loop completes without returning, it means the message doesn't contain any bad words
ServiceDemande.getInstance().ajoutDemande(demande);
Dialog.show("Confirmation", "Demande Ajoutée avec succès", "OK", null);
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
//ba3d ajout net3adaw lel ListREclamationForm
new ListDemandeForm(res).show();
refreshTheme(); //Actualisation
        
                
                } 
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
         
        });
        
        
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
    
   
   public String validateFields( TextField demande) {
    StringBuilder errorMessage = new StringBuilder();

   
   
   
    
    if (demande.getText().isEmpty()) {
        errorMessage.append("Le champ demande est obligatoire.\n");
    }
   
    return errorMessage.toString();
}

}
