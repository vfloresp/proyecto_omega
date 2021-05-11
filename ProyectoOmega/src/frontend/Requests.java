package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static proyectoomega.BaseDeDatos.*;

public class Requests extends JFrame{

    public Requests(String id){
        this.setTitle("Requests");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        
        //RADIO BUTTONS DE LAS SOLICITUDES
        
        JButton accept = new JButton("Accept");
        JButton decline = new JButton("Decline");
        JButton contacts = new JButton("Contacts");
        accept.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                //ACEPTAR SOLICITUD DEL ID DEL RADIOBUTTON SELECCIONADO
            }
        });
        decline.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                //RECHAZAR SOLICITUD DEL ID DEL RADIOBUTTON SELECCIONADO
            }
        });
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contactos contactos = new Contactos(id);
                dispose();
            }
        });
        
        add(accept);
        add(decline);
        add(contacts);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
         Requests requests = new Requests("Susy1");
    }

}
