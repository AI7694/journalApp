package net.engineeringdigest.journalApp.Scheduler;

import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.Entity.JournalApp;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Enum.Sentiments;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.SentimentalAnalysisService;
import org.jetbrains.annotations.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentalAnalysisService sentimentalAnalysisService;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendMail(){
     List<User> users= userRepository.getUserForSA();
     for(User user:users){
         List<JournalApp> journalEntries = user.getJournalEntries();

         List<Sentiments> sentiments = journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7,ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
         Map<Sentiments, Integer> sentimentCounts = new HashMap<>();
         for(Sentiments sentiment:sentiments){
             if(sentiment !=null){
            sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0)+1);
             }

             Sentiments mostFrequentSentiment = null;
             int maxCount =0;
             for(Map.Entry<Sentiments, Integer> entry:sentimentCounts.entrySet()){
                 if(entry.getValue() > maxCount){
                     maxCount = entry.getValue();
                     mostFrequentSentiment = entry.getKey();
                 }
             }
             if(mostFrequentSentiment!=null) {
                 emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days ", mostFrequentSentiment.toString());
             }
         }
//       List<String> filteredEntries=  journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
//      String entry =String.join(" ", filteredEntries);
//      String sentiment = sentimentalAnalysisService.getSentiment(entry);
//       emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days",sentiment);

     }


    }

    @Scheduled(cron = "0 0/10 * ? * * SUN")
    public void clearAppCache(){
        appCache.init();
    }
}

