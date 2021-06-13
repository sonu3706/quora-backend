package com.quora.app.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    private String userEmail;
    private String firstName;
    private String lastName;
    private String gender;
    private Boolean userActive;
    private LocalDate userAddedOn;
    private LocalDate profileModifiedOn;
}
