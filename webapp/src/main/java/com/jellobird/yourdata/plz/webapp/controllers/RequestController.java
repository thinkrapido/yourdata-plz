package com.jellobird.yourdata.plz.webapp.controllers;

import com.jellobird.yourdata.plz.webapp.models.LogEntry;
import com.jellobird.yourdata.plz.webapp.models.Plz;
import com.jellobird.yourdata.plz.webapp.repositories.LogEntryRepository;
import com.jellobird.yourdata.plz.webapp.repositories.PlzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/plz")
public class RequestController {

    @Autowired
    PlzRepository plzRepository;

    @Autowired
    LogEntryRepository logEntryRepository;

    @GetMapping("")
    public Flux<Plz.Output> getPlz(@RequestParam("search") String partialString) {
        var out = plzRepository.search(partialString).map(plz -> Plz.Output.of(plz.getOrt(), plz.getPlz(), plz.getBundesland()));

        if (out.hasElements().block()) {
            logEntryRepository.save(LogEntry.of(new Timestamp(new java.util.Date().getTime()), Arrays.stream(Mono.from(out.map(item -> item.toString()).buffer()).block().toArray()).collect(Collectors.toList()).toString())).block();
        }

        return out;
    }

}
