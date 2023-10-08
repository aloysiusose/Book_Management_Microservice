package dev.aloyisius.App_Auth_Server.Controllers;

import dev.aloyisius.App_Auth_Server.Models.ApplicationUsers;
import dev.aloyisius.App_Auth_Server.Service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {
    private final RegistrationService service; 

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/")
    public void adduser(@RequestBody ApplicationUsers users){
        service.registerUser(users);

    }
    @PostMapping
    public void registerClient(){

    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
