package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String nombre = textField.getText();
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

    /*public static void main(String[] args){
        SignIn signIn = new SignIn();
    }*/
}
