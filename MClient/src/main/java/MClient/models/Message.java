package MClient.models;

/**
 * Created by Ben on 13/04/2016.
 */
public class Message {
    private final int id;
    private final String instruction;

    public Message(int id, String instruction) {
        this.id = id;
        this.instruction = instruction;
    }

    public int getId() {
        return id;
    }

    public String getInstruction() {
        return instruction;
    }
}
