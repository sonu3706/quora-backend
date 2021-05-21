package com.quora.app.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Question {
    @Id
    private String questionId;
    private String questionType;
    private String questionContent;
    private LocalDate questionAddedOn;
    private Boolean questionActiveState;
    private String questionAskedBy;
}
