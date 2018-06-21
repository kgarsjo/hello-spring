package hellospring.greeting.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String getGreeting() {
        return "Hello!";
    }

    public String getGreeting(String name) {
        if (name == null) {
            return getGreeting();
        }
        return String.format("Hello, %s!", name);
    }
}