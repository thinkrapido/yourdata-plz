package com.jellobird.yourdata.plz.webapp.services;

import com.jellobird.yourdata.plz.webapp.models.Plz;
import com.jellobird.yourdata.plz.webapp.repositories.PlzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class PlzSearchService {

    @Autowired
    PlzRepository plzRepository;

    public Flux<Plz> search(String partialString) {
        return plzRepository.search(partialString);
    }

    public void store(MultipartFile file) throws IOException {
        plzRepository.deleteAll();
        plzRepository.saveAll(new BufferedReader(new InputStreamReader(file.getInputStream())).lines()
                .skip(1)
                .filter(line -> !"".equals(line))
                .map(line -> line.split(","))
                .map(parts -> Plz.of(parts[0], parts[1], parts[2], parts[3]))
                .collect(Collectors.toList())).blockLast()
                ;
    }

}
