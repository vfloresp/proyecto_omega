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
        JButton startMenu = new JButton("Start Menu");
        logIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                createConnection(); 
                String nombre = textField.getText();
                if(login(nombre)){
                    System.out.println("Si estas");
                    MainMenu mainMenu = new MainMenu(nombre);
                    dispose();
                }else{
                    System.out.println("El nombre de usuario no esta registrado");
                }
                //shutdown();
                //dispose();
            }
        });
        startMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                StartMenu startMenu = new StartMenu();
                dispose();
            }
        });
        
        add(descripcion);
        add(textField);
        add(logIn);
        add(startMenu);

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
