package com.quora.app.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {
    @Id
    private String questionId;
    private String questionType;
    private String questionContent;
    private LocalDate questionAddedOn;
    private Boolean questionActiveState;
    private String questionAskedBy;
    private List<Category> categories;
}
