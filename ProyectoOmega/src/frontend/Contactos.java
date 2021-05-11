/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static proyectoomega.BaseDeDatos.*;


public class Contactos extends JFrame {
    
    public Contactos(String id){
        createConnection(); 
        this.setTitle("Contacts");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        ArrayList<String> contactos = AllContacts(id); 
        for(String contacto:contactos){
            JLabel JLcontacto = new JLabel(contacto);
            add(JLcontacto); 
        }
        
        JButton addContact = new JButton("Add Contact");
        JButton requests = new JButton("Requests");
        JButton mainMenu = new JButton("Main Menu");
        addContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                AddContact addContact = new AddContact(id);
                dispose();
            }
        });
        requests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Requests requests = new Requests(id);
                dispose();
            }
        });
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu mainMenu = new MainMenu(id);
                dispose();
            }
        });
        add(addContact);
        add(requests);
        add(mainMenu);
    
        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        shutdown();
    }

    public static void main(String[] args){
      
        Contactos contactos = new Contactos("Susy1");
        
    }
}
