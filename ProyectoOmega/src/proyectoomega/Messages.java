package proyectoomega;
import frontend.MainMenu;
import proyectoomega.Queue;


public class Messages {
    private Queue queue;
    private MainMenu menu;

    public Messages(Queue queue, MainMenu menu) {
        this.queue = queue;
        this.menu = menu;
    }

    public void sendMessage(String idToSend, String message){
        queue.sendMessage(idToSend,"Message,"+queue.getId()+","+message);
    }

    public void sendAck(String idToSend){
        queue.sendMessage(idToSend, "MessageReceived,"+ queue.getId());
    }

    public void sendMsgRead(String idToSend){
        queue.sendMessage(idToSend,"MessageRead,"+queue.getId());
    }

    public void receiveMessage(String message){
        String[] parts = message.split(",",2);
        String from = parts[0];
        String msg = parts[1];
        System.out.println("Message from: "+from+" said: "+msg);
        menu.addMessage(from, "Message-"+msg);
        sendAck(from);
    }

    public void messageAck(String idFrom){
        System.out.println("message received by "+ idFrom);
        menu.addMessage(idFrom, " Received your message.");
    }

    public void messageRead(String idFrom){
        System.out.println("message read by " + idFrom);
        menu.addMessage(idFrom, " Read your message.");
    }



}
