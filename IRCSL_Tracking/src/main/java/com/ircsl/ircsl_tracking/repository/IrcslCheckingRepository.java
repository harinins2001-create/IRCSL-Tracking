package com.ircsl.ircsl_tracking.repository;

import com.ircsl.ircsl_tracking.model.IrcslChecking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IrcslCheckingRepository extends MongoRepository<IrcslChecking, String> {
    // filter to due date
    List<IrcslChecking> findByDueDate(LocalDate dueDate);
}
