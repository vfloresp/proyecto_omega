package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static proyectoomega.BaseDeDatos.*;

public class NewMessage extends JFrame {

    public NewMessage(String id) {
        this.setTitle("New Message");
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
                Messages messages = new Messages(id);
                dispose();
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

    public static void main(String[] args) {
        NewMessage newMessage = new NewMessage("Susy1");
    }

}
