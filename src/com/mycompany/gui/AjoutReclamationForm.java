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
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AjoutReclamationForm extends BaseForm {
    
    
    Form current;
    public AjoutReclamationForm(Resources res ) {
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
        RadioButton mesListes = RadioButton.createToggle("Mes Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Reclamer", barGroup);
        liste.setUIID("SelectBar");
      //  RadioButton partage = RadioButton.createToggle("Mes reclamations", barGroup);
     //   partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
         ListReclamationForm a = new ListReclamationForm(res);
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
          //  updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
     //   bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      
        TextField nom = new TextField("", "entrer nom!!");
        nom.setUIID("nom");
        addStringValue("nom",nom);
        
        TextField prenom = new TextField("", "entrer prenom!!");
        prenom.setUIID("prenom");
        addStringValue("prenom",prenom);
        
          TextField email = new TextField("", "entrer Email!!");
        email.setUIID("email");
        addStringValue("email",email);
        
          TextField numtel = new TextField("", "entrer Numero!!");
        numtel.setUIID("numtel");
        addStringValue("numtel",numtel);
        
          TextField message = new TextField("", "entrer Message!!");
        message.setUIID("message");
        addStringValue("message",message);
        
//           TextField typer = new TextField("", "entrer Type de reclamation!!");
//        typer.setUIID("typer");
//        addStringValue("typer",typer);

ComboBox<String> typer = new ComboBox<>("Problèmes achat en ligne", "Problèmes de navigation sur le site", "Problèmes de qualité de produits ou de services", "Problèmes de confidentialité et de sécurité des données");
typer.setUIID("typer");

Container comboBoxContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
comboBoxContainer.add(new Label("Domaine:"));
comboBoxContainer.add(typer);

// add the comboBoxContainer to the form
this.add(comboBoxContainer);






//        ComboBox typer = new ComboBox();
//        
//        typer.addItem("Non Traiter");
//        
//        typer.addItem("Traiter");
//        
//        if(r.getTyper() == 0 ) {
//            typer.setSelectedIndex(0);
//        }
//        else 
//            typer.setSelectedIndex(1);
//        
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
             String errorMessage = validateFields(nom, prenom, email, numtel, message);
            if (!errorMessage.isEmpty()) {
                Dialog.show("Erreur", errorMessage, "OK", null);
                return;
            }
             int selectedIndex = typer.getSelectedIndex();
        String domaine = selectedIndex >= 0 ? typer.getModel().getItemAt(selectedIndex) : null;
            
            try {
                
                if(nom.getText().equals("") || prenom.getText().equals("")|| email.getText().equals("")|| numtel.getText().equals("")|| message.getText().equals("")|| domaine.equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Reclamation r = new Reclamation(String.valueOf(nom.getText()).toString(),
                                  String.valueOf(prenom.getText()).toString(),
                             String.valueOf(email.getText()).toString(),
                              Integer.parseInt(numtel.getText()),
                             String.valueOf(message.getText()).toString(),
                                  format.format(new Date()),
                             String.valueOf(domaine).toString()
                                  );
                    
                    System.out.println("data  reclamation == "+r);
    
               String messagee = r.getMessage();
String[] badWords = {"zibi", "fuck", "aasba"}; // List of bad words

for (String word : badWords) {
    if (messagee.contains(word)) {
        // Alert the user about the bad word
        Dialog.show("Alerte", "Le mot " + word + " est interdit", "OK", null);
        new AjoutReclamationForm(res).show();
        return; // Stop the method execution
    } 
}

// If the loop completes without returning, it means the message doesn't contain any bad words
ServiceReclamation.getInstance().ajoutReclamation(r);
Dialog.show("Confirmation", "Réclamation Ajoutée avec succès", "OK", null);
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
//ba3d ajout net3adaw lel ListREclamationForm
new ListReclamationForm(res).show();
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