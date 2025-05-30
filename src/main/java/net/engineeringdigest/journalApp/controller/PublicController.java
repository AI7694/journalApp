package net.engineeringdigest.journalApp.controller;
import net.engineeringdigest.journalApp.service.UserService;

import net.engineeringdigest.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

@Autowired
private UserService userService;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user){
        System.out.println("user creation started");
        userService.saveNewUser(user);
        return "User created";
    }
}
