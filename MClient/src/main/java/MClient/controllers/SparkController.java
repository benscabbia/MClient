package MClient.controllers;

import MClient.models.spark.ClientWithSparkInstruction;
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
    @RequestMapping(value = "/wordcount", method = RequestMethod.POST)
    @ResponseBody
    public ClientWithSparkInstruction processSpark(@RequestBody ClientWithSparkInstruction instruction, Model model) {

        ClientWithSparkInstruction received = instruction;


        if (received != null) {
            String filePath = received.getSingleClientSparkInstruction().getFileDirectory();
            SparkType type = received.getSingleClientSparkInstruction().getSparkType();
            boolean sorted = received.getSingleClientSparkInstruction().isSorted();

            received.setResponse("WE HAVE RECEIVED IT AND SENT IT BACK");

            //  try {

//
////                Person p = new Person();
////                javax.persistence.Query query = em.createNativeQuery(sqlQuery, p.getClass());
////                p = (Person)query.getSingleResult();
//                //"SELECT p FROM Person p"
//                TypedQuery<Person> q = em.createQuery(sqlQuery, Person.class);
//
//                List<Person> people = q.getResultList();
//
//                for(Person p : people){
//                    System.out.println(p.toString());
//                }
//
//                received.setClientResponse(people.toString());
//
//            }catch (Exception e){
//                received.setClientResponse(e.getStackTrace() + " error");
//            }
            //}
            return received;
        }else{
            return received;
        }
    }
}