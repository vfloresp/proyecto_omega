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
import proyectoomega.*;

/**
 *
 * @author vflores
 */
public class PopUp extends JFrame{
    private String idRes;

    public PopUp(String titulo, String texto,Queue queue) {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        this.setTitle(titulo);
        idRes = "";
        String indicador = texto.split("-")[0];
        if(indicador.split(",")[0].equals("NuevoMensaje")){
            idRes = indicador.split(",")[1];
            texto = texto.split("-")[1];
        }
        
        
        JLabel label = new JLabel(texto);
        add(label);
        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               if(idRes.length()>0){
                   Messages msg = new Messages(queue);
                   msg.sendMsgRead(idRes);
               }
               
               dispose();
            }
        });
        add(cerrar);
        setSize(300,300);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    
    
}
