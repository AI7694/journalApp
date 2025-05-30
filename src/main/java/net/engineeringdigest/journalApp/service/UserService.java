package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import java.util.Arrays;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public boolean saveNewUser(User user){
     try{
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Arrays.asList("USER"));
         userRepository.save(user);
         return true;
     }
     catch(Exception e){
        log.error("Error occured for {} :" ,user.getUserName(),e);
//        logger.warn("Hi I am string");
//        logger.info("hi I am information");
//        logger.trace("Hi i am doing trace");
//        logger.debug("Hi I am debug");
         return false;
     }

    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
         userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}
