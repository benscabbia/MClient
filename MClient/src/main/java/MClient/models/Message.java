package MClient.models;

/**
 * Created by Ben on 13/04/2016.
 */

public class Message {
    public static int id;
    public String instruction;
    private String clientResponse;

    public Message(String instruction) {
        this.id = ++id;
        this.instruction = instruction;

    }

    public int getId() {
        return id;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getClientResponse() {
        return clientResponse;
    }

    public void setClientResponse(String clientResponse) {
        this.clientResponse = clientResponse;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", instruction='" + instruction + '\'' +
                '}';
    }

    //required default constructor
    public Message(){

    }
}
