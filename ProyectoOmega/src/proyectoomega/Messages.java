package proyectoomega;
import frontend.MainMenu;
import frontend.PopUp;
import proyectoomega.*;


public class Messages {
    private Queue queue;

    public Messages(Queue queue) {
        this.queue = queue;
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
        PopUp pop = new PopUp("Nuevo mensaje "+queue.getId(), "NuevoMensaje,"+from+"-"+ msg,queue);
        sendAck(from);
    }

    public void messageAck(String idFrom){
        System.out.println("message received by "+ idFrom);
        PopUp pop = new PopUp("Mensaje Recibido "+queue.getId(), idFrom + " Recibio tu mensaje", queue);
    }

    public void messageRead(String idFrom){
        System.out.println("message read by " + idFrom);
        PopUp pop = new PopUp("Mensaje Leido "+queue.getId(), idFrom + " Leyo tu mensaje", queue);
    }



}
