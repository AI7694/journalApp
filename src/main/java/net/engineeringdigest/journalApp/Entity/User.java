package net.engineeringdigest.journalApp.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String userName;
    private String email;
    private boolean sentimentAnalysis;

    @NonNull
    private String password;
    private LocalDateTime date;
    @DBRef
    private List<JournalApp> journalEntries= new ArrayList<>();
    private List<String>roles;

//    @Override
//   public String toString(){
//       return "User{userName "+userName+",password "+password;
//   }
}
