import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class MessageListenerOnLine implements MessageListener{
private Queue queue;
private MessageClasification messageClasification;
//private Request request;
//private Messages messages;

    public MessageListenerOnLine(Queue queue) {
        this.queue = queue;
        this.messageClasification = new MessageClasification(this.queue);
        //this.request = new Request(queue);
        //this.messages = new Messages(queue);
    }

    @Override
    public void onMessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try {
            messageClasification.classifyMessage(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
