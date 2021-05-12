
package frontend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import proyectoomega.*;


public class MainMenu extends JFrame {
    private Queue queue;
    private Request request;
    private Messages messages;
    private ArrayList<String> pendingRequests;  
    private ArrayList<String[]> pendingMessages; 

            
    public MainMenu(String id){
        this.pendingRequests = new ArrayList<String>();
        this.pendingMessages =new ArrayList<String[]>();
        this.setTitle("Omega Turbo Message");
        this.queue = new Queue(id, this);
        this.request = new Request(queue, this);
        this.messages = new Messages(queue, this);
        this.queue.receivedMessages();
        this.queue.startListeningForMessages();
        MainMenu();
    }
    
    public void MainMenu(){
        getContentPane().removeAll();
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,60);
        setLayout(layout);
        JButton contacts = new JButton("Contacts");
        JButton messages = new JButton("Messages");
        JButton logOut = new JButton("logOut");
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contact();
            }
        });
        messages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Messages();
            }
        });
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               queue.stopListeningForMessages();
               queue.closeSession();
               StartMenu st = new StartMenu();
               dispose();
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
    
    public void Contact (){
        getContentPane().removeAll();
        repaint(); 
        createConnection(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        ArrayList<String> contactos = AllContacts(queue.getId()); 
        for(String contacto:contactos){
            JLabel JLcontacto = new JLabel(contacto);
            add(JLcontacto); 
        }

        JButton addContact = new JButton("Add Contact");
        JButton requests = new JButton("Requests");
        JButton mainMenu = new JButton("Main Menu");
        addContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                AddContact(); 
            }
        });
        requests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Request(); 
            }
        });
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu(); 
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
       
    
    public void Messages(){
        getContentPane().removeAll(); 
        repaint(); 
         createConnection(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        
        for(String message[]:pendingMessages){
            String mssg = message[1];
            if(mssg.split("-")[0].equals("Message")){
                messages.sendMsgRead(message[0]);
                mssg = mssg.split("-")[1];
            }
            JLabel JLcontacto = new JLabel("Message from "+message[0]+ ": "+mssg);
            
            add(JLcontacto);
        }
        
        JButton newMessage = new JButton("New Message");
        JButton mainMenu = new JButton("Main Menu");
        JButton clearMessages = new JButton("Clear Messages");
        newMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                NewMessage(); 
            }
        });
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu();
            }
        });
        clearMessages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                pendingMessages.clear();
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
    
    public void NewMessage(){
        getContentPane().removeAll(); 
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 100, 40);
        setLayout(layout);
        createConnection();
        ArrayList<String> contacts = AllContacts(queue.getId());
        shutdown();
        String[] cantactsArray = new String[contacts.size()];
        int i = 0;
        for(String contact:contacts){
            cantactsArray[i] = contact;
            i++;
        }
        
        JComboBox<String> contactList = new JComboBox<>(cantactsArray);

        JLabel textMessageLabel = new JLabel("Mensaje:");
        JTextField textMessage = new JTextField(40);
        JButton send = new JButton("Send");
        JButton messageButton = new JButton("Messages");
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createConnection();
                String id = (String) contactList.getSelectedItem();
                String mensaje = textMessage.getText();
                messages.sendMessage(id,mensaje);
                shutdown();

            }
        });
        messageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Messages(); 
            }
        });
        
        add(contactList);
        add(textMessageLabel);
        add(textMessage);
        add(send);
        add(messageButton);

        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void Request (){
        getContentPane().removeAll();
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        
        //RADIO BUTTONS DE LAS SOLICITUDES
        String[] ids = new String[pendingRequests.size()];
        int i = 0;
        for(String idRequest:pendingRequests){
            ids[i] = idRequest;
            i++;
        }
        
        JComboBox<String> idList = new JComboBox<>(ids);
        add(idList);
        
        JButton accept = new JButton("Accept");
        JButton decline = new JButton("Decline");
        JButton contacts = new JButton("Contacts");
        accept.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                createConnection();
                String selectedId = (String) idList.getSelectedItem();
                request.respondRequest(selectedId,true);
                pendingRequests.remove(selectedId);
                shutdown();
            }
        });
        decline.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                createConnection();
                String selectedId = (String) idList.getSelectedItem();
                request.respondRequest(selectedId,false);
                pendingRequests.remove(selectedId);
                shutdown();
            }
        });
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contact(); 
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

    public void AddContact (){
        getContentPane().removeAll(); 
        repaint(); 
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,100,40);
        setLayout(layout);
        JLabel descripcion = new JLabel("Nombre de usuario que quiere agregar:");
        JTextField textField = new JTextField(15);
        JButton addContact = new JButton("Add Contact");
        JButton contacts = new JButton("Contacts");
        addContact.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                createConnection();
                String id = textField.getText();
                if(login(id)){
                    request.sendRequest(id);
                }else{
                    System.out.println("El nombre de usuario no esta registrado");
                }
            }
        });
        contacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Contact(); 
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
    
    public void addRequest(String id){
        pendingRequests.add(id);
    }
    
    public void addMessage(String id, String message){
        String[] tuple = new String[2];
        tuple[0] = id;
        tuple[1] = message;
        pendingMessages.add(tuple);
    }
    
}
