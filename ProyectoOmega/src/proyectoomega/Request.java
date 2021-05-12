package proyectoomega;
import frontend.MainMenu;
import frontend.PopUp;
import frontend.PopUpBotones;
import static proyectoomega.BaseDeDatos.insertContacto;
import proyectoomega.Queue;


public class Request {
    private Queue queue;

    public Request(Queue queue) {
        this.queue = queue;
        
    }

    public void sendRequest(String id){
        queue.sendMessage(id,"Request,"+queue.getId());
    }

    public void respondRequest(String id, boolean accepted){
        String response = accepted ? "accepted" : "declined";
        queue.sendMessage(id,"ResponseRequest,"+response+","+queue.getId());
        if(accepted){
            insertContacto(queue.getId(),id);
            PopUp pop = new PopUp("Solicitud de "+queue.getId(),id+" Acepto tu solicitud",queue);
        }else{
            PopUp pop = new PopUp("Solicitud de "+queue.getId(),id+" Rechazo tu solicitud",queue);
        }
    }

    public void receiveRequest(String request){
        String idRequest = request;
        System.out.println("Received request from: "+idRequest);
        PopUpBotones pop = new PopUpBotones("Solicitud para "+queue.getId(),idRequest+" Te envió una solicitud",queue);
    }

    public void responseRequest(String request){
        String[] parts = request.split(",");
        System.out.println(parts[0]);
        if(parts[0].equals("accepted")){
            System.out.println("Request accepted by: "+parts[1]);
            insertContacto(queue.getId(),parts[1]);
        }else{
            System.out.println("Request declined by: "+parts[1]);
        }
    }
}
