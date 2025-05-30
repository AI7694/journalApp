package net.engineeringdigest.journalApp.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    @NotNull
    private String title;
    private String content;
    private LocalDateTime date;



//    @Override
//    public String toString(){
//        return "JournalEntry{"+"id="+id+",title "+title+",content "+content;
//    }
}
