package net.engineeringdigest.journalApp.Entity;

import lombok.*;
import net.engineeringdigest.journalApp.Enum.Sentiments;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalApp {
    @Id
    private ObjectId id;
    @NotNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiments sentiment;



//    @Override
//    public String toString(){
//        return "JournalEntry{"+"id="+id+",title "+title+",content "+content;
//    }
}
