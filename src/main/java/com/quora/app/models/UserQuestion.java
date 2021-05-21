package com.quora.app.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
@ToString
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class UserQuestion {
    @Id
    private String userId;
    private List<Question> questions;
}
