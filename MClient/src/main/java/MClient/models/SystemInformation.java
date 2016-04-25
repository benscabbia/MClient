package MClient.models;

/**
 * Created by Ben on 23/04/2016.
 */
public class SystemInformation {
    private String operatingSystem;
    private String jvmVersion;
    private String processor;
    private String architecture;
    private String version; //32/64bit

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getJvmVersion() {
        return jvmVersion;
    }

    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
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

    @Override
    public String toString() {
        return "SystemInformation{" +
                "operatingSystem='" + operatingSystem + '\'' +
                ", jvmVersion='" + jvmVersion + '\'' +
                ", processor='" + processor + '\'' +
                ", architecture='" + architecture + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
