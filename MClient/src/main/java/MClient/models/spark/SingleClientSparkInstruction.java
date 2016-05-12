package MClient.models.spark;

/**
 * Created by Ben on 12/05/2016.
 */
public class SingleClientSparkInstruction {
    String fileDirectory;
    SparkType sparkType;
    boolean sorted;

    public SingleClientSparkInstruction() {
    }

    public SingleClientSparkInstruction(String fileDirectory, SparkType sparkType, boolean sorted) {
        this.fileDirectory = fileDirectory;
        this.sparkType = sparkType;
        this.sorted = sorted;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public SparkType getSparkType() {
        return sparkType;
    }

    public void setSparkType(SparkType sparkType) {
        this.sparkType = sparkType;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }
}
