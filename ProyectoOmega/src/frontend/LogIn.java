package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static proyectoomega.BaseDeDatos.*;

public class LogIn  extends JFrame{

    public LogIn(){
        
        this.setTitle("Omega Turbo Message");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        JLabel descripcion = new JLabel("Ingrese su Id:");
        JTextField textField = new JTextField(20);
        JButton logIn = new JButton("LogIn");
        logIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                createConnection(); 
                String nombre = textField.getText();
                if(login(nombre)){
                    System.out.println("Si estas");
                }else{
                    System.out.println("No estas");
                }
                shutdown();
                dispose();
            }
        });
        
        add(descripcion);
        add(textField);
        add(logIn);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public static void main(String[] args){
        /*createConnection(); 
        System.out.println(login("Susy1"));
        shutdown();*/
        LogIn LogIn = new LogIn();
    }
}
