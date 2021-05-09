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
            //System.out.print("Received the following message: ");
            //System.out.println(textMessage.getText());
            //System.out.println();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    /*
    public void processByCase(String message){
        String[] parts = message.split(",",2);
        switch (parts[0]){
            case "Request":
                System.out.println("Request");
                request.receiveRequest(parts[1]);
                break;
            case "ResponseRequest":
                System.out.println("ResponseRequest");
                request.responseRequest(parts[1]);
                break;
            case "Message":
                System.out.println("Message");
                messages.receiveMessage(parts[1]);
                break;

        }
    }*/
}
