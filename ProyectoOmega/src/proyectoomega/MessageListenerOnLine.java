package proyectoomega;

import frontend.MainMenu;
import proyectoomega.Queue;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class MessageListenerOnLine implements MessageListener{
private Queue queue;
private MessageClasification messageClasification;

    public MessageListenerOnLine(Queue queue, MainMenu menu) {
        this.queue = queue;
        this.messageClasification = new MessageClasification(this.queue, menu);
    }

    @Override
    public void onMessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try {
            this.messageClasification.classifyMessage(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
