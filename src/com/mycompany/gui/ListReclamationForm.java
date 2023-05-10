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
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ListReclamationForm extends BaseForm{
        private Reclamation reclamation;

    Form current;
  public ListReclamationForm(Resources res ) {
          super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste des Reclamations");
        getContentPane().setScrollVisible(false); 
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        

        
        
        
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
        RadioButton mesListes = RadioButton.createToggle("Les Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        TextField search = new TextField("","search");
        search.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          ListReclamationForm a = new ListReclamationForm(res);
            a.show();
            refreshTheme();
        });
        
        partage.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          AjoutReclamationForm a = new AjoutReclamationForm(res);
            a.show();
            refreshTheme();
        });
        

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, search, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
Container reclamationContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
add(reclamationContainer);

// Appel affichage methode
ArrayList<Reclamation> listR = ServiceReclamation.getInstance().affichageReclamations();

showReclamations(reclamationContainer, listR, res);

search.setShouldCalcPreferredSize(true);
search.addDataChangedListener((i, ii) -> {
    String filter = search.getText();
    if (filter.isEmpty()) {
        System.out.println("emptyyyyyyyyyyy");
        showReclamations(reclamationContainer, listR, res);
    } else {
        System.out.println("not empty ");
        ArrayList<Reclamation> filteredList = ServiceReclamation.getInstance().filterReclamations(filter);
        updateReclamationsUI(reclamationContainer, filteredList, res);
    }
    revalidate();
});


    }
    
    private void showReclamations(Container container, ArrayList<Reclamation> reclamations, Resources res) {
    // Remove the previous reclamations
    container.removeAll();

    for (Reclamation rec : reclamations) {
        String urlImage = "back-logo.jpeg"; // Image path (static for now)

        Image placeHolder = Image.createImage(120, 90);
        EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
        URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

        Component reclamationComponent = createReclamationComponent(urlim, rec, res);
        container.add(reclamationComponent);
    }

    // Revalidate the layout to reflect the changes
    container.revalidate();
}

// Method to update the UI with the filtered list of reclamations
private void updateReclamationsUI(Container container, ArrayList<Reclamation> filteredList, Resources res) {
    // Remove the previous reclamations
    container.removeAll();

    for (Reclamation rec : filteredList) {
        String urlImage = "back-logo.jpeg"; // Image path (static for now)

        Image placeHolder = Image.createImage(120, 90);
        EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
        URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

        Component reclamationComponent = createReclamationComponent(urlim, rec, res);
        container.add(reclamationComponent);
    }

    // Revalidate the layout to reflect the changes
    container.revalidate();
}

private Component createReclamationComponent(Image img, Reclamation rec, Resources res) {
    
     
    
         int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
Container reclamationComponent = BorderLayout.west(image);
    reclamationComponent.setUIID("ReclamationComponent");        
        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
               Label nomtTxt = new Label("Nom : "+rec.getNom(),"NewsTopLine2");
                Label prenomTxt = new Label("Prenom : "+rec.getPrenom(),"NewsTopLine2");
                        Label emailText = new Label("Email : "+rec.getEmail(),"NewsTopLine2");
                                Label numtelText = new Label("Numero : "+rec.getNumtel(),"NewsTopLine2");
        Label messagetxt = new Label("Message : "+rec.getMessage(),"NewsTopLine2");
        Label datetxt = new Label("Date : "+rec.getDate(),"NewsTopLine2" );
                Label Typetxt = new Label("type : "+rec.getTyper(),"NewsTopLine2" );
                                
        createLineSeparator();
        
        
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
         
        
        lSupprimer.addPointerPressedListener(l -> {
            
             boolean isDeleted = ServiceReclamation.getInstance().deleteReclamation(rec.getId());
    if (isDeleted) {
        Dialog.show("Succès", "REclamation supprimé avec succé", "OK", null);
        new ListReclamationForm(res).show();
        // call a method to refresh the user list
    } else {
        Dialog.show("Erreur", "Échec de la suppression de la reclamation ", "OK", null);
    }
           
        });
        
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            System.out.println("hello update");
            new ModifierReclamationForm(res,rec).show();
        });
        
        
        reclamationComponent.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(nomtTxt),
                BoxLayout.encloseX(prenomTxt),
         BoxLayout.encloseX(emailText),
          BoxLayout.encloseX(numtelText),
           BoxLayout.encloseX(messagetxt),
            BoxLayout.encloseX(datetxt),
            BoxLayout.encloseX(Typetxt),
     
                    //   BoxLayout.encloseX(Typetxt,lModifier);
                     BoxLayout.encloseX(lModifier,lSupprimer)));

     return reclamationComponent;

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
   
   
}
