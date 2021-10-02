package com.jellobird.yourdata.plz.webapp.services;

import com.jellobird.yourdata.plz.webapp.models.Plz;
import com.jellobird.yourdata.plz.webapp.repositories.PlzRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PlzSearchService implements PlzRepository {
    @Override
    public Flux<Plz> search(String partialString) {
        return null;
    }
}
