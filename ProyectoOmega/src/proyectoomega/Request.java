package proyectoomega;
import static proyectoomega.BaseDeDatos.insertContacto;
import proyectoomega.Queue;


public class Request {
    Queue queue;

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
        }
    }

    public void receiveRequest(String request){
        String idRequest = request;
        System.out.println("Received request from: "+idRequest);

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
