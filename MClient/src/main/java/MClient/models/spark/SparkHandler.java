package MClient.models.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ben on 13/05/2016.
 */
public class SparkHandler {

    private static SparkConf conf = new SparkConf().setMaster("local").setAppName("Work Count App");
    private static JavaSparkContext sc = new JavaSparkContext(conf);

    public static String process(ClientWithSparkInstruction clientWithSparkInstruction){

        String filePath = clientWithSparkInstruction.getSingleClientSparkInstruction().getFileDirectory();
        SparkType type = clientWithSparkInstruction.getSingleClientSparkInstruction().getSparkType();
        boolean sorted = clientWithSparkInstruction.getSingleClientSparkInstruction().isSorted();

        //String t = "d:\\Ben\\Desktop\\test-small.txt";
        //String t2 = "d:/Ben/Desktop/test-small.txt";

        if(validateFile(filePath)){
            if(type == SparkType.WORDCOUNT){
                return wordCount(filePath, sorted);
            }
            else if(type == SparkType.WORDSEARCH){
                return wordSearch(filePath, sorted);
            }else{
                return "Unknown Spark Command";
            }
        }else{
            return "The file could not be found or could not be read at directory: " + filePath;
        }
    }

    private static String wordCount(String filePath, boolean sorted){
        String log = ""; //used incase of exception
        try{

            // Define a configuration to use to interact with Spark
            System.out.println("==========================PHASE 1 ================");
            log += "Phase one - Initializing Spark: Complete\n";

            System.out.println("==========================PHASE 2 ================");
//        // Load the input data, which is a text file read from the command line
            log += "Phase Two - loading the input data into textfile: ";
            JavaRDD<String> input = sc.textFile( filePath );
            log += "Complete\n";
//
            System.out.println("==========================PHASE 3 ================");
            log += "Phase Three - Processing the data: ";
            JavaRDD<String> words = input.flatMap(
                    new FlatMapFunction<String, String>() {
                        public Iterable<String> call(String s) {
                            return Arrays.asList(s.split(" "));
                        }
                    } );
//
            log += "Complete\n";

            System.out.println("==========================PHASE 4 ================");
//        // Java 7 and earlier: transform the collection of words into pairs (word and 1)
            log += "Phase Four - Counting the data: ";
            JavaPairRDD<String, Integer> counts = words.mapToPair(
                    new PairFunction<String, String, Integer>(){
                        public Tuple2<String, Integer> call(String s){
                            return new Tuple2(s, 1);
                        }
                    } );
//
            log += "Complete\n";

            System.out.println("==========================PHASE 5 ================");
            log += "Phase Five - Reducing the data: ";
            JavaPairRDD<String, Integer> reducedCounts = counts.reduceByKey(
                    new Function2<Integer, Integer, Integer>(){
                        public Integer call(Integer x, Integer y){ return x + y; }
                    } );

            log += "Complete\n";
//
            System.out.println("==========================PHASE 6 ================");
            log += "Phase Six - Moving data to a List: ";
            List arr = reducedCounts.collect();

            log += "Complete \n";
//        // Save the word count back out to a text file, causing evaluation.
//
//
            System.out.println("==========================PHASE 7 ================");
            log += "Phase Seven - Sorting and Packing data: ";
            //TODO need to check if sorted, and if so, sort the results
            String resultSet = "";
            for(Object a : arr){
                Tuple2<String, Integer> tuple = (Tuple2)a;
                System.out.println(tuple);
                resultSet += tuple + ", ";
            }
            log += "Complete\n";

            return resultSet;

        }catch (Exception e){
            System.out.println("Error. Spark Exception.");
            log += "Error (Spark Exception): " + e.getStackTrace()[0] + ", CAUSE: " + e.getCause();
            return log;
        }
    };


    private static String wordSearch(String filePath, boolean sorted){return "TODO implementation";};

    private static boolean validateFile(String filePath){

        File file = new File(filePath);
        return file.exists() && file.canRead();
//        System.out.println(file.toString()); //d:\Ben\Desktop\test-small.txt
//        System.out.println(file.exists()); //bool
//        System.out.println(file.getParentFile()); //d:\Ben\Desktop
//        System.out.println(file.getAbsoluteFile()); //d:\Ben\Desktop\test-small.txt
    }



    public static boolean validatePort(int port) {
        if (port < 2000 || port > 50000) {
            return false;
        }
        //implementation from Apache camel project
        //http://svn.apache.org/viewvc/camel/trunk/components/camel-test/src/main/java/org/apache/camel/test/AvailablePortFinder.java?view=markup#l130

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                /* should not be thrown */
                }
            }
        }

        return false;
    }
}
