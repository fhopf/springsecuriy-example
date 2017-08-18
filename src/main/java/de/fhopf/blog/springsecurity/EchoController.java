package de.fhopf.blog.springsecurity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @RequestMapping("/echo")
    public String echo(@RequestParam("message") String message) {
        return message;
    }

    @RequestMapping("/secret/echo")
    public String secretEcho(@RequestParam("message") String message) {
        return String.format("Secret %s", message);
    }

}
