package com.jellobird.yourdata.plz.webapp.models;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Optional;
import java.util.UUID;

@Data(staticConstructor = "of")
@Table("plz")
public class Plz implements Persistable<UUID> {

    @Id
    UUID id;
    @NonNull
    String osm_id;
    @NonNull
    String ort;
    @NonNull
    String plz;
    @NonNull
    String bundesland;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {

        if (Optional.ofNullable(id).isPresent()) {
            return false;
        }
        else {
            id = UUID.randomUUID();
            return true;
        }
    }

    @Value(staticConstructor = "of")
    public static class Output {

        String ort;
        String plz;
        String bundesland;

    }
}
