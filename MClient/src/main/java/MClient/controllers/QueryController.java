package MClient.controllers;

import MClient.models.FileOperator;
import MClient.models.Message;
import MClient.models.databaseSample.MongoPerson;
import MClient.models.databaseSample.Person;
import MClient.repository.MongoPersonRepository;
import MClient.repository.PersonRepository;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Ben on 26/04/2016.
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private PersonRepository personRepository;

    //   ------------------------------------- query/sqlDatabase ---------------------------------------------------
    @RequestMapping(value = "/sqlDatabase", method = RequestMethod.POST)
    @ResponseBody
    public Message processDatabaseQuery(@RequestBody Message instruction, Model model) {

        Message received = instruction;
        if(received!= null){
            String receivedInstruction = received.getInstruction();

            try{
                String[] queryAndResultType = FileOperator.getStringAndSearchTerm(receivedInstruction);
                String sqlQuery = queryAndResultType[0];
                String resultType = queryAndResultType[1];

                //"SELECT p FROM Person p"
                TypedQuery<Person> q = em.createQuery(sqlQuery, Person.class);

                List<Person> people = q.getResultList();

                for(Person p : people){
                    System.out.println(p.toString());
                }

                if (resultType != null && resultType.toLowerCase().equals("count")) {
                    received.setClientResponse(people.size() + "");
                }else{
                    received.setClientResponse(people.toString());
                }



            }catch (Exception e){
                received.setClientResponse(e.getStackTrace()[0] + "");
            }
        }
        return received;
    }

    //   ------------------------------------- query/nosqlDatabase ---------------------------------------------------
    @Autowired
    private MongoPersonRepository mongoPersonRepository;

    @RequestMapping(value = "/nosqlDatabase", method = RequestMethod.POST)
    @ResponseBody
    public Message processnosqlDatabaseQuery(@RequestBody Message instruction, Model model) {
        Message received = instruction;

        if(received != null){

            try {

                MongoClient mongoClient = new MongoClient("localhost", 27017);
                MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "MongoPerson");
                MongoOperations mongoOperations = (MongoOperations)mongoTemplate;
                //e.g. {firstName : 'Sarah'}, {firstName : 'Sarah', age:46}, {firstName : 'Sarah', age: {$gte: 20}}

                String[] queryAndResultType = FileOperator.getStringAndSearchTerm(received.getInstruction());
                String mongoQuery = queryAndResultType[0];
                String resultType = queryAndResultType[1];

                BasicQuery query  = new BasicQuery(mongoQuery);
                List<MongoPerson> people = mongoOperations.find(query, MongoPerson.class);

                if(people.size()>0){

                    if(resultType != null && resultType.toLowerCase().equals("count")){
                        received.setClientResponse(people.size() + "");
                    }else{
                        received.setClientResponse(people.toString());
                    }
                }else{
                    received.setClientResponse("Result set was Empty.");
                }

            }catch(UnknownHostException e){
                received.setClientResponse("NOSQL ERROR: " + e.getStackTrace()[0]);

            }catch (Exception e){
                received.setClientResponse("ERROR: " + e.getStackTrace()[0]);
            }

        }
        return received;
    }
}
