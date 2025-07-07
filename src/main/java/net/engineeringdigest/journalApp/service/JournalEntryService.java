package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Entity.JournalApp;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

//   private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

//    @Transactional
    public void saveEntry(JournalApp journalEntry, String userName) {

        try {
            User user = userService.findByUserName(userName);//took user
            JournalApp saved = journalEntryRepository.save(journalEntry);//saved journal entry in loccal variable

            user.getJournalEntries().add(saved);//add the saved journal entry
//            user.setUserName(null);
            userService.saveUser(user);//and saved that journal entry of a specific user
        } catch (Exception e) {
//            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry ", e);
        }

    }

    public void saveEntry(JournalApp journalEntry) {

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalApp> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalApp> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

   @Transactional
    public boolean deleteById(ObjectId id, String userName) {
  boolean removed = false;
        try {
            User user = userService.findByUserName(userName);//find user
          removed = user.getJournalEntries().removeIf(x -> (x.getId().equals(id)));//removeif id already exist
            if (removed) {
                userService.saveUser(user);//then save it
                journalEntryRepository.deleteById(id);//delete  that user journal Entry
            }
        }catch(Exception e){
            log.error("Error",e
            );
            throw new RuntimeException("An error occured while delting the entry");

        }
        return removed;
    }


}
