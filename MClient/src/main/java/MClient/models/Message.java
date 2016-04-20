package MClient.models;

/**
 * Created by Ben on 13/04/2016.
 */
public class Message {
    private final int id;
    private final String instruction;
    private InstructionStatus instructionStatus;

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

    public InstructionStatus getInstructionStatus() {
        return instructionStatus;
    }

    public void setInstructionStatus(InstructionStatus instructionStatus) {
        this.instructionStatus = instructionStatus;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", instruction='" + instruction + '\'' +
                ", instructionStatus=" + instructionStatus +
                '}';
    }
}
