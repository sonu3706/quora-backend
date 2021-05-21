package com.quora.app.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    private String userId;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String gender;
    private Boolean userActive;
    private LocalDate userAddedOn;
    private LocalDate profileModifiedOn;
}
