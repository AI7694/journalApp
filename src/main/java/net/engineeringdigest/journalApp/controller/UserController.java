package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.Api.response.WeatherResponse;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userinDB = userService.findByUserName(userName);

        userinDB.setUserName(user.getUserName());
        userinDB.setPassword(user.getPassword());
        userService.saveNewUser(userinDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserbyId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 @GetMapping("/greetings")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        userRepository.deleteByUserName(authentication.getName());
     WeatherResponse weatherResponse = weatherService.getWeather("Indore");
     String greetings ="Hello "+authentication.getName()+" !";
     if(weatherResponse!=null && weatherResponse.getCurrent() !=null){
         greetings += "Weather feels like "+weatherResponse.getCurrent().getFeelslike();
     }
     else{
         greetings +=" weather data is unavailable";
     }

     return new ResponseEntity<>( greetings,HttpStatus.OK);
    }
//

}
