package com.jellobird.yourdata.plz.webapp.repositories;

import com.jellobird.yourdata.plz.webapp.models.Plz;
import reactor.core.publisher.Flux;

public interface PlzRepository {

    Flux<Plz> search(String partialString);

}
