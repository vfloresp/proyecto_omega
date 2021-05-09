package proyectoomega;
import static proyectoomega.BaseDeDatos.*;
import proyectoomega.Queue;



public class Main {

    public static void main(String[] args){
        /*
        -----------------------------------------------------------
        TESTS PARA BASE DE DATOS
        ------------------------------------------------------------
*/
        createConnection();
        System.out.println(countRegistros());
        //insertRegistro("Victor");
        //insertContacto("Susy1", "Tabata2"); 
        //System.out.println(AllContacts("Susy1")); 
        //System.out.println(login("Tabata2")); 
        System.out.println(name("Susy1"));
        shutdown();
    
        /*
        -----------------------------------------------------------
        TESTS PARA QUEUE
        ------------------------------------------------------------

        Queue pA = new Queue("colaA");
        //pA.newConnection();
        //pA.newQueue("colaA");

        Queue pB = new Queue("colaB");
        //pB.newConnection();
        //pB.newQueue("colaB");

        pB.sendMessage("colaA","mensaje de B a A 1");
        pB.sendMessage("colaA","mensaje de B a A 2");

        pA.receivedMessages();

        pA.startListeningForMessages();

        pB.sendMessage("colaA","mensaje de B a A instantaneo");

        pA.stopListeningForMessages();

        pA.closeSession();
        pB.closeSession();
        */
        /*
        -----------------------------------------------------------
        TESTS PARA MESSAGES/REQUEST
        ------------------------------------------------------------
        */

        Queue pA = new Queue("colaA");
        Queue pB = new Queue("colaB");

        Messages messagesA = new Messages(pA);
        Messages messagesB = new Messages(pB);
        Request requestA = new Request(pA);
        Request requestB = new Request(pB);

        messagesB.sendMessage("colaA", "Message while A disconected");

        pA.receivedMessages();
        pB.receivedMessages();

        pA.startListeningForMessages();

        messagesB.sendMessage("colaA","Message while A connected");
        messagesB.sendMessage("colaA","Message2 while A connected");
        requestB.sendRequest("colaA");
        requestA.respondRequest("colaB",true);

        pA.stopListeningForMessages();

        pA.closeSession();
        pB.closeSession();
    }

}
