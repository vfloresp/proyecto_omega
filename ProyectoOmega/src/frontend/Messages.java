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

public class Messages extends JFrame {
    
    public Messages(String id){
        createConnection(); 
        this.setTitle("Messages");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        
        //MENSAJES
        /*ArrayList<String> messages = AllMessages(id); 
        for(String message:messages){
            JLabel JLmessage = new JLabel(message);
            add(JLmessage); 
        }*/
        
        JButton newMessage = new JButton("New Message");
        JButton mainMenu = new JButton("Main Menu");
        JButton clearMessages = new JButton("Clear Messages");
        newMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                NewMessage newMessage = new NewMessage(id);
                dispose();
            }
        });
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu mainMenu = new MainMenu(id);
                dispose();
            }
        });
        clearMessages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //LIMPIA MENSAJES EXISTENTES
            }
        });
        add(newMessage);
        add(mainMenu);
        add(clearMessages);
    
        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        shutdown();
    }

    public static void main(String[] args){
      
        Messages messages = new Messages("Susy1");
        
    }
}
