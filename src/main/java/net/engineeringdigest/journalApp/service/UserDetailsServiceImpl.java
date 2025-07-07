package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Component
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private UserRepository userrepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepository.findByUserName(username);
        if (user != null) {
            UserDetails userdetails = org.springframework.security.core.userdetails.User.builder().
                    username(user.getUserName()).password(user.getPassword()).roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userdetails;
        }
        throw new UsernameNotFoundException("User no found with username" + username);
    }
}


