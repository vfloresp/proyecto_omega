
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
    }

    public void receiveRequest(String request){
        String idRequest = request;
        System.out.println("Received request from: "+idRequest);

    }

    public void responseRequest(String request){
        String[] parts = request.split(",");
        if(parts[0]=="accepted"){
            System.out.println("Request accepted by: "+parts[1]);
        }else{
            System.out.println("Request declined by: "+parts[1]);
        }
    }
}
