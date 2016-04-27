package MClient.controllers;

import MClient.models.Message;
import MClient.models.databaseSample.Person;
import MClient.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @RequestMapping(value = "/sqlDatabase", method = RequestMethod.POST)
    @ResponseBody
    public Message processDatabaseQuery(@RequestBody Message instruction, Model model) {

        Message received = instruction;
        if(received!= null){
            String sqlQuery = received.getInstruction();

            try{


//                Person p = new Person();
//                javax.persistence.Query query = em.createNativeQuery(sqlQuery, p.getClass());
//                p = (Person)query.getSingleResult();
                                                    //"SELECT p FROM Person p"
                TypedQuery<Person> q = em.createQuery(sqlQuery, Person.class);

                List<Person> people = q.getResultList();

                for(Person p : people){
                    System.out.println(p.toString());
                }

                received.setClientResponse(people.toString());

            }catch (Exception e){
                received.setClientResponse(e.getStackTrace() + " error");
            }
        }
        return received;
    }
}
