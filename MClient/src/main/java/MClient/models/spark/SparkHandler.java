package MClient.models.spark;

import MClient.models.FileOperator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.*;

/**
 * Created by Ben on 13/05/2016.
 */
public class SparkHandler {

    private static SparkConf conf = new SparkConf().setMaster("local").setAppName("Work Count App");
    private static JavaSparkContext sc = new JavaSparkContext(conf);

    public static String process(ClientWithSparkInstruction clientWithSparkInstruction){

        String input = clientWithSparkInstruction.getSingleClientSparkInstruction().getFileDirectory();

        String[] filePathAndSearchTerm = FileOperator.getStringAndSearchTerm(input);

        String filePath = filePathAndSearchTerm[0];
        String searchWord = filePathAndSearchTerm[1];
        SparkType type = clientWithSparkInstruction.getSingleClientSparkInstruction().getSparkType();
        boolean sorted = clientWithSparkInstruction.getSingleClientSparkInstruction().isSorted();


        //String t = "d:\\Ben\\Desktop\\test-small.txt";
        //String t2 = "d:/Ben/Desktop/test-small.txt";

        if(FileOperator.validateFile(filePath)){
            if(type == SparkType.WORDCOUNT){
                return wordCount(filePath, sorted);
            }
            else if(type == SparkType.WORDSEARCH){

                 if(searchWord != null && !searchWord.isEmpty()){
                    return wordSearch(filePath, searchWord, sorted);
                }else {
                    return "ERROR in issuing flag. Format is: 'directory [key]'";
                }

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
            System.out.println("==========================PHASE 1 ================");
            log += "Phase one - Initializing Spark: Complete\n";

            System.out.println("==========================PHASE 2 ================");
//        // Load the input data
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
            //List arr = reducedCounts.collect();
            Map<String, Integer> arr = reducedCounts.collectAsMap();

            log += "Complete \n";
//        // Save the word count back out to a text file, causing evaluation.

            System.out.println("==========================PHASE 7 ================");
            log += "Phase Seven - Sorting and Packing data: ";
            //TODO need to check if sorted, and if so, sort the results
            String resultSet = "";

            if(sorted){

                TreeMap<String, Integer> sortedResults = sortMapByValue(arr);
                log += "Complete\n";
                return sortedResults.toString();

            }else{
                log += "Complete\n";
                return arr.toString();
            }

        }catch (Exception e){
            System.out.println("Error. Spark Exception.");
            log += "Error (Spark Exception): " + e.getStackTrace()[0] + ", CAUSE: " + e.getCause();
            return log;
        }
    }

    private static TreeMap<String,Integer> sortMapByValue(Map<String, Integer> map) {
        Comparator<String> comparator = new sortMapByValueComparator(map);
        //TreeMap used to sort the keys and comparator sorts by keys
        TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
        result.putAll(map);
        return result;
    };


    private static String wordSearch(String filePath, final String searchWord, boolean sorted){
        String log = ""; //used incase of exception



        try{
            System.out.println("==========================PHASE 1 ================");
            log += "Phase one - Initializing Spark: Complete\n";

            System.out.println("==========================PHASE 2 ================");
            // Load the input data
            log += "Phase Two - loading the input data into textfile: ";
            //JavaRDD<String> input = sc.textFile( filePath );
            //JavaRDD<String> input = sc.textFile("d:/Ben/Desktop/test-small.txt");
            log += "Complete\n";

            System.out.println("==========================PHASE 3 ================");
            log += "Phase Three - Initializing Spark: Complete\n";

            JavaRDD<String> lines = sc.textFile("d:/Ben/Desktop/test-small.txt").filter(
                    new Function<String, Boolean>() {
                        public Boolean call(String s) {
                            return s.contains(searchWord);
                        }
                    });

            log += "Complete\n";
            return  searchWord + " was found: " + lines.count() + " times";

        }catch(Exception e){
            System.out.println("Error. Spark Exception.");
            log += "Error (Spark Exception): " + e.getStackTrace()[0] + ", CAUSE: " + e.getCause();
            return log;
        }
    };





    private static class sortMapByValueComparator implements Comparator<String> {
        Map<String, Integer> map = new HashMap<String, Integer>();

        public sortMapByValueComparator(Map<String, Integer> map) {
            this.map.putAll(map);
        }

        @Override
        public int compare(String o1, String o2) {
            if(map.get(o1) >= map.get(o2)){
                return -1;
            }else {
                return 1;
            }
        }
    }
}
