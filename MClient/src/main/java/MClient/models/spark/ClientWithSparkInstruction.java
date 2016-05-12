package MClient.models.spark;

/**
 * Created by Ben on 12/05/2016.
 */
public class ClientWithSparkInstruction extends Client{
    private SingleClientSparkInstruction singleClientSparkInstruction;

    private String response;

    public ClientWithSparkInstruction(Client client, SingleClientSparkInstruction singleClientSparkInstruction) {
        this.clientID = client.getClientID();
        this.ipAddress = client.getIpAddress();
        this.portNumber = client.getPortNumber();
        this.description = client.getDescription();

        this.singleClientSparkInstruction = singleClientSparkInstruction;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public SingleClientSparkInstruction getSingleClientSparkInstruction() {
        return singleClientSparkInstruction;
    }

    public void setSingleClientSparkInstruction(SingleClientSparkInstruction singleClientSparkInstruction) {
        this.singleClientSparkInstruction = singleClientSparkInstruction;
    }

    public ClientWithSparkInstruction(){}
}
