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

/**
 *
 * @author susy_
 */
public class MainMenu extends JFrame{
    
    public MainMenu(String id){
        this.setTitle("Omega Turbo Message");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,60);
        setLayout(layout);
        JButton contacts = new JButton("Contacts");
        JButton messages = new JButton("Messages");
        JButton logOut = new JButton("logOut");
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contactos contactos = new Contactos(id);
                dispose();
            }
        });
        messages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Messages messages = new Messages(id);
                dispose();
            }
        });
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                LogIn logIn = new LogIn();
                dispose();
            }
        });
        add(contacts);
        add(messages);
        add(logOut);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        MainMenu mainMenu = new MainMenu("Susy1");
    }
    
}
