package hellospring.greeting.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hellospring.greeting.services.GreetingService;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping("")
    public String greeting(
        @RequestParam(value = "name", required = false) String name
    ) {
        return greetingService.getGreeting(name);
    }
}