package com.jellobird.yourdata.plz.webapp.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Data(staticConstructor = "of")
@Table("log")
public class LogEntry implements Persistable<UUID> {

    @Id
    UUID id;
    @NonNull
    Timestamp timestamp;
    @NonNull
    String content;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {

        if (Optional.ofNullable(id).isPresent()) {
            return false;
        }

        id = UUID.randomUUID();
        return true;
    }
}
