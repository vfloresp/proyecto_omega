package proyectoomega;

import frontend.MainMenu;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Enumeration;

public class Queue {
    // URL of the JMS server
    private String id;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    private Destination destination;
    private MessageClasification messageClasification;
    private MainMenu menu;
    //private Request request;
    //private Messages messages;


    public Queue(String id, MainMenu menu) {
        this.id = id;
        this.newConnection();
        this.newQueue(this.id);
        this.messageClasification = new MessageClasification(this,menu);
        //this.request = new Request(this);
        //this.messages = new Messages(this);
    }

    private void newConnection(){
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void newQueue(String id){
        try{
            destination = session.createQueue(id);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receivedMessages(){
        try {
            MessageConsumer messageConsumer = session.createConsumer(destination);
            Boolean moreMsg = true;
            while(moreMsg){
                Message msg = messageConsumer.receive(5000);
                if(msg instanceof TextMessage){
                    TextMessage txtMessage = (TextMessage) msg;
                    messageClasification.classifyMessage(txtMessage.getText());
                }else{
                    moreMsg = false;
                }
            }
            messageConsumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String idToSend, String message ){
        try {
            Destination destination = session.createQueue(idToSend);
            MessageProducer messageProducer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
            messageProducer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void startListeningForMessages(){
        try {
            messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListenerOnLine(this, menu));
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void stopListeningForMessages(){
        try {
            messageConsumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void closeSession(){
        try {
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return id;
    }
}



