package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "config_journal_app_entity")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {

        private ObjectId id;

        private String key;
        private String value;







}
