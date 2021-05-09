package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame{

    public StartMenu(){
        this.setTitle("Omega Turbo Message");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,60);
        setLayout(layout);
        JButton logIn = new JButton("LogIn");
        JButton signIn = new JButton("SignIn");
        logIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                LogIn logIn = new LogIn();
                dispose();
            }
        });
        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SignIn signIn = new SignIn();
                dispose();
            }
        });
        add(logIn);
        add(signIn);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        StartMenu sMenu = new StartMenu();
    }

}
