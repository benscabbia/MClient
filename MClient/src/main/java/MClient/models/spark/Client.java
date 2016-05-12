package MClient.models.spark;

/**
 * Created by Ben on 12/05/2016.
 */
public class Client {
    public int clientID;
    public String ipAddress;
    public String portNumber;
    public String description;
    private String operatingSystem;
    private String processor;
    private String architecture;
    private String version;
    private String jvmVersion;

    public Client(){}

    public Client(int clientID, String ipAddress, String portNumber, String description){
        this.clientID = clientID;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.description = description;
    }

    public Client(int clientID, String ipAddress, String portNumber) {
        this.clientID = clientID;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getJvmVersion() {
        return jvmVersion;
    }

    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", ipAddress='" + ipAddress + '\'' +
                ", portNumber='" + portNumber + '\'' +
                ", description='" + description + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", processor='" + processor + '\'' +
                ", architecture='" + architecture + '\'' +
                ", version='" + version + '\'' +
                ", jvmVersion='" + jvmVersion + '\'' +
                '}';
    }
}
