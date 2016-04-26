package MClient.controllers;

import MClient.models.Message;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Ben on 26/04/2016.
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @PersistenceContext
    public EntityManager em;

    @RequestMapping(value = "/sqlDatabase", method = RequestMethod.POST)
    @ResponseBody
    public Message processDatabaseQuery(@RequestBody Message instruction, Model model) {

        Message received = instruction;
        if(received!= null){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(instruction.toString());
            System.out.println("FINISHED PRINT INSTRUCTION");

            received.setClientResponse("WE GOT IT!!!!!!!");

        }
        return received;
    }
}
