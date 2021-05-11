package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static proyectoomega.BaseDeDatos.*;

public class AddContact extends JFrame{

    public AddContact(String id){
        this.setTitle("Add Contact");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        JLabel descripcion = new JLabel("Nombre de usuario que quiere agregar:");
        JTextField textField = new JTextField(20);
        JButton addContact = new JButton("Add Contact");
        JButton contacts = new JButton("Contacts");
        addContact.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                //AÑADIR CONTACTO
            }
        });
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contactos contactos = new Contactos(id);
                dispose();
            }
        });
        
        add(descripcion);
        add(textField);
        add(addContact);
        add(contacts);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        AddContact addContact = new AddContact("Susy1");
    }

}
