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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static proyectoomega.BaseDeDatos.createConnection;
import static proyectoomega.BaseDeDatos.insertRegistro;
import static proyectoomega.BaseDeDatos.login;

/**
 *
 * @author vflores
 */
public class StartMenu extends JFrame{
    
    public StartMenu(){
        this.setTitle("Omega Turbo Message");
        StartScreen();
    }
    
    public void StartScreen(){
       
        getContentPane().removeAll();
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,60);
        setLayout(layout);
        JButton logIn = new JButton("LogIn");
        JButton signIn = new JButton("SignIn");
      
        logIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Login();
            }
        });
        
        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Signin();
            }
        });
        
        add(logIn);
        add(signIn);
        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
     
    }
  
    public void Login(){ 
        getContentPane().removeAll(); 
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        JLabel descripcion = new JLabel("Ingrese su Id:");
        JTextField textField = new JTextField(20);
        JButton logIn = new JButton("LogIn");
        JButton startMenu = new JButton("Start Menu");
        logIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                createConnection(); 
                String id = textField.getText();
                if(login(id)){
                    System.out.println("Si estas");
                    MainMenu mm = new MainMenu(id);
                    dispose();
                }else{
                    System.out.println("El nombre de usuario no esta registrado");
                }

            }
        });
         startMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                StartScreen();
               }
        });

        add(descripcion);
        add(textField);
        add(logIn);
        add(startMenu);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
    }
       
    
    
    public void Signin(){
        getContentPane().removeAll();
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        JLabel descripcion = new JLabel("Ingrese su nombre:");
        JTextField textField = new JTextField(20);
        JButton signIn = new JButton("SignIn");
        JButton logIn = new JButton("LogIn");
        signIn.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                createConnection(); 
                String nombre = textField.getText();
                String id = insertRegistro(nombre); 
                System.out.println(id);
            }
        });
        logIn.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                Login();
            }
        });

        add(descripcion);
        add(textField);
        add(signIn);
        add(logIn);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public static void main(String[] args){
        StartMenu front = new StartMenu();
    }
}
