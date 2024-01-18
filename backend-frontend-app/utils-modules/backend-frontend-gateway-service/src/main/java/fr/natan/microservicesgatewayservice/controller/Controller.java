package fr.natan.microservicesgatewayservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
    @Value("${message.value}")
    private String [] welcome;
    @GetMapping("/")
    public Map<String, String> getWelcome(){
        return Map.of(welcome[0], welcome[1]);
    }
}
