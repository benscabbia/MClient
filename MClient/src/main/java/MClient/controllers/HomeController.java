package MClient.controllers;

import MClient.models.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ben on 13/04/2016.
 */

@RestController
@RequestMapping("/")
public class HomeController {
    private static final String template = "The message says, %s!";
    private final AtomicInteger counter = new AtomicInteger();

    @RequestMapping(method = RequestMethod.GET)
    public Message message(@RequestParam(value="message", defaultValue = "nothing") String message){
        return new Message(counter.incrementAndGet(), String.format(template, message));
    }
}
