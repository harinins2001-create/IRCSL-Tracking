package com.ircsl.ircsl_tracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "ircsl_checkings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IrcslChecking {

    @Id
    private String id;
    private String ircslDescription;
    private String responsiblePerson;
    private LocalDate sentDateToResponsiblePerson;
    private LocalDate dueDate;
    private LocalDate sentDateToIrcsl;
}
