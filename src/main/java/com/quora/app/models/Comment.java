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
public class Comment {
    @Id
    private String commentId;
    private String questionId;
    private String commentContent;
    private LocalDate commentAddedOn;
    private String commentAddedBy;
}
