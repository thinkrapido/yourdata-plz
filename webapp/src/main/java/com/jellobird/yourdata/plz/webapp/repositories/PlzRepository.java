package com.jellobird.yourdata.plz.webapp.repositories;

import com.jellobird.yourdata.plz.webapp.models.Plz;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.UUID;

public interface PlzRepository  extends ReactiveCrudRepository<Plz, UUID> {

    @Query("select * from plz where plz like concat(:partialString, '%') limit 30")
    Flux<Plz> search(String partialString);

}
