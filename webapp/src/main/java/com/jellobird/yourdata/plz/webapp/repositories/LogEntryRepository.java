package com.jellobird.yourdata.plz.webapp.repositories;

import com.jellobird.yourdata.plz.webapp.models.LogEntry;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.sql.Timestamp;

public interface LogEntryRepository extends ReactiveCrudRepository<LogEntry, Timestamp> {
}
