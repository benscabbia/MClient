package MClient.controllers;

import MClient.models.spark.ClientWithSparkInstruction;
import MClient.models.spark.SparkHandler;
import MClient.models.spark.SparkType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ben on 11/05/2016.
 */
@RestController
@RequestMapping("/spark")
public class SparkController {


    //   ------------------------------------- POST spark/wordcount ---------------------------------------------------
//    @RequestMapping(value = "/wordcount", method = RequestMethod.POST)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ClientWithSparkInstruction processSpark(@RequestBody ClientWithSparkInstruction instruction, Model model) {

        ClientWithSparkInstruction received = instruction;

        if (received != null) {
            String filePath = received.getSingleClientSparkInstruction().getFileDirectory();
            SparkType type = received.getSingleClientSparkInstruction().getSparkType();
            boolean sorted = received.getSingleClientSparkInstruction().isSorted();

            String sparkResult = "";
            try{

                sparkResult += SparkHandler.process(received);

            }catch (Exception e){
                System.out.println("SPARK EXCEPTION");
                sparkResult += "SPARK EXCEPTION: " + e.getStackTrace()[0];
            }

            received.setResponse(sparkResult);

            return received;
        }else{
            throw new NullPointerException();        }
    }
}