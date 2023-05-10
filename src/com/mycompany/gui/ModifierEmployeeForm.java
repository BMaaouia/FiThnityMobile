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
import com.mycomany.entities.Employee;
import com.mycompany.services.ServiceEmployee;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ChatGPT
 */
public class ModifierEmployeeForm extends BackForm {

    private Form current;

    public ModifierEmployeeForm(Resources res, Employee e) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(event -> {});
        
        TextField firstname_employeeField = new TextField(e.getFirstname_employee(), "Firstname", 20, TextField.ANY);
        firstname_employeeField.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to red

        TextField lastname_employeeField = new TextField(e.getLastname_employee(), "Lastname", 20, TextField.ANY);
        lastname_employeeField.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to green

        TextField email_employeeField = new TextField(e.getEmail_employee(), "Email", 20, TextField.ANY);
        email_employeeField.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue

        TextField address_employeeField = new TextField(e.getAddress_employee(), "Adresse", 20, TextField.ANY);
        address_employeeField.getUnselectedStyle().setFgColor(0xf21f1f); // set text color to blue

        


        firstname_employeeField.setSingleLineTextArea(true);
        lastname_employeeField.setSingleLineTextArea(true);
        email_employeeField.setSingleLineTextArea(true);
        address_employeeField.setSingleLineTextArea(true);
    

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        btnModifier.addActionListener(l -> {
           
       

            e.setFirstname_employee(firstname_employeeField.getText());
            e.setLastname_employee(lastname_employeeField.getText());
            e.setEmail_employee(email_employeeField.getText());
            e.setAddress_employee(address_employeeField.getText());
            

            if (ServiceEmployee.getInstance().modifierEmployee(e)) {
                Dialog.show("Confirmation", "Réclamation modifiée avec succès", "OK", null);
                new ListEmployeeForm(res).show();
            } else {
                Dialog.show("Confirmation", "Réclamation modifiée avec succès", "OK", null);
                new ListEmployeeForm(res).show();
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(event -> {
            new ListEmployeeForm(res).show();
        });

        Label l1 = new Label("Firstname");
        Label l2 = new Label("Lastname");
        Label l3 = new Label("Email");
        Label l4 = new Label("Adress");
    






    Container content = BoxLayout.encloseY(
            BorderLayout.center(firstname_employeeField).
                    add(BorderLayout.WEST, l1),
            BorderLayout.center(lastname_employeeField).
                    add(BorderLayout.WEST, l2),
            BorderLayout.center(email_employeeField).
                    add(BorderLayout.WEST, l3),
            BorderLayout.center(address_employeeField).
                    add(BorderLayout.WEST, l4)
           
    );

    content.setScrollableY(true);
    add(content);

    add(BoxLayout.encloseX(btnModifier, btnAnnuler));
}




}

