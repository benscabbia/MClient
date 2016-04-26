package MClient.controllers;

import MClient.models.SystemInformation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ben on 13/04/2016.
 */

@RestController
@RequestMapping("/")
public class HomeController {
    private static String osName = System.getProperty("os.name").toLowerCase();
    private static String osArchitecture = System.getProperty("os.arch").toLowerCase();
    private static String osVersion = System.getProperty("os.version").toLowerCase();
    private static String jvmModel = System.getProperty("sun.arch.data.model").toLowerCase();
    private static int logicalCores = Runtime.getRuntime().availableProcessors();


    private static final String template = "The message says, %s!";
    private final AtomicInteger counter = new AtomicInteger();

    @RequestMapping(method = RequestMethod.GET)
//    public Message message(@RequestParam(value="message", defaultValue = "nothing") String message){
//        return new Message(counter.incrementAndGet(), String.format(template, message));
//    }
    public String loadedPage(){
        return "Page loaded ok";
    }

    @RequestMapping(value="/information", method = RequestMethod.GET)
    public SystemInformation displayInfo(){
        SystemInformation systemInfo = new SystemInformation();
        systemInfo.setOperatingSystem(osName);
        systemInfo.setArchitecture(osArchitecture);
        systemInfo.setJvmVersion(jvmModel);
        systemInfo.setVersion(osVersion);
        systemInfo.setProcessor(logicalCores+"");

        return systemInfo;
        //return new Message(counter.incrementAndGet(), String.format(template, message));
    }
}
