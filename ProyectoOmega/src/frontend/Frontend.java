
package frontend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static proyectoomega.BaseDeDatos.AllContacts;
import static proyectoomega.BaseDeDatos.createConnection;
import static proyectoomega.BaseDeDatos.insertRegistro;
import static proyectoomega.BaseDeDatos.login;
import static proyectoomega.BaseDeDatos.shutdown;


public class Frontend extends JFrame {
    
    public Frontend(){
        this.setTitle("Omega Turbo Message");
        StartMenu(); 
    }
    
    public void StartMenu(){
       
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
                String nombre = textField.getText();
                if(login(nombre)){
                    System.out.println("Si estas");
                    MainMenu(nombre);
                }else{
                    System.out.println("El nombre de usuario no esta registrado");
                }

            }
        });
         startMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                StartMenu();
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
    
    public void MainMenu(String id){
        getContentPane().removeAll();
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,60);
        setLayout(layout);
        JButton contacts = new JButton("Contacts");
        JButton messages = new JButton("Messages");
        JButton logOut = new JButton("logOut");
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contact(id);
            }
        });
        messages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Messages(id);
            }
        });
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               Login(); 
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
    
    public void Contact (String id){
        getContentPane().removeAll();
        repaint(); 
        createConnection(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        ArrayList<String> contactos = AllContacts(id); 
        for(String contacto:contactos){
            JLabel JLcontacto = new JLabel(contacto);
            add(JLcontacto); 
        }

        JButton addContact = new JButton("Add Contact");
        JButton requests = new JButton("Requests");
        JButton mainMenu = new JButton("Main Menu");
        addContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                AddContact(id); 
            }
        });
        requests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Request(id); 
            }
        });
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu(id); 
            }
        });
        add(addContact);
        add(requests);
        add(mainMenu);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        shutdown();
    }
       
    
    public void Messages(String id){
        getContentPane().removeAll(); 
        repaint(); 
         createConnection(); 
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
                NewMessage(id); 
            }
        });
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu(id);
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
    
    public void NewMessage(String id){
        getContentPane().removeAll(); 
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 100, 40);
        setLayout(layout);
        JLabel descripcion = new JLabel("Nombre de usuario del destinatario:");
        JTextField textField = new JTextField(20);
        JLabel descripcion2 = new JLabel("Mensaje:");
        JTextField textField2 = new JTextField(40);
        JButton send = new JButton("Send");
        JButton messages = new JButton("Messages");
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ENVIAR MENSAJE
            }
        });
        messages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Messages(id); 
            }
        });

        add(descripcion);
        add(textField);
        add(descripcion2);
        add(textField2);
        add(send);
        add(messages);

        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void Request (String id){
        getContentPane().removeAll();
        repaint(); 
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
                Contact(id); 
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

    public void AddContact (String id){
        getContentPane().removeAll(); 
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        JLabel descripcion = new JLabel("Nombre de usuario que quiere agregar:");
        JTextField textField = new JTextField(20);
        JButton addContact = new JButton("Add Contact");
        JButton contacts = new JButton("Contacts");
        addContact.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                //AÑADIR CONTACTO
            }
        });
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contact(id); 
            }
        });
        
        add(descripcion);
        add(textField);
        add(addContact);
        add(contacts);

        setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        Frontend front = new Frontend();
    }
    
}
