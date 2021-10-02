package com.jellobird.yourdata.plz.webapp.controllers;

import com.jellobird.yourdata.plz.webapp.models.Plz;
import com.jellobird.yourdata.plz.webapp.repositories.PlzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/v1/plz")
public class RequestController {

    @Autowired
    PlzRepository plzRepository;

    @GetMapping("")
    public Flux<Plz.Output> getPlz(@RequestParam("search") String partialString) {
        return plzRepository.search(partialString).map(plz -> Plz.Output.of(plz.getOrt(), plz.getPlz(), plz.getBundesland()));
    }

}
