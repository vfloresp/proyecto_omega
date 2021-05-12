/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.ws.Response;
import proyectoomega.Messages;
import proyectoomega.Queue;
import proyectoomega.Request;

/**
 *
 * @author vflores
 */
public class PopUpBotones extends JFrame{
    private String idRes;
    
    public PopUpBotones(String titulo, String texto,Queue queue) {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        this.setTitle(titulo);
        
        idRes = texto.split(" ")[0];
                
        JLabel label = new JLabel(texto);
        add(label);
        JButton aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               Request r = new Request(queue);
               r.respondRequest(idRes, true);
               dispose();
            }
        });
        
        JButton rechazar = new JButton("Rechazar");
        rechazar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               Request r = new Request(queue);
               r.respondRequest(idRes, false);
               dispose();
            }
        });
        add(aceptar);
        add(rechazar);
        setSize(300,300);
        setVisible(true);
        
    }
}
