package proyectoomega;
import proyectoomega.Queue;


public class MessageClasification {
    private Request request;
    private Messages messages;
    private Queue queue;

    public MessageClasification( Queue queue) {
        this.queue = queue;
        this.request = new Request(this.queue);
        this.messages = new Messages(this.queue);
    }

    public void classifyMessage(String message){
        String[] parts = message.split(",",2);
        switch (parts[0]){
            case "Request":
                System.out.println("Request");
                request.receiveRequest(parts[1]);
                break;
            case "ResponseRequest":
                System.out.println("ResponseRequest ");
                request.responseRequest(parts[1]);
                break;
            case "Message":
                System.out.println("Message");
                messages.receiveMessage(parts[1]);
                break;
            case "MessageReceived":
                System.out.println("MessageReceived");
                messages.messageAck(parts[1]);
                break;
            case "MessageRead":
                System.out.println("MessageRead");
                messages.messageRead(parts[1]);
                break;

        }
    }

}
