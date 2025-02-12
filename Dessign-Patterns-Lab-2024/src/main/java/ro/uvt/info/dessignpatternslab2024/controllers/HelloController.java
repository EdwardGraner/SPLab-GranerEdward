package ro.uvt.info.dessignpatternslab2024.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uvt.info.dessignpatternslab2024.difexample.ClientComponent;

@RestController
@RequestMapping("/")
public record HelloController(ClientComponent clientComponent) {

    @GetMapping
    public String sayHello() {
        return "Hello from ClientComponent = " + clientComponent;
    }
}
