package com.quora.app.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class UserAuth {
    @Id
    private String userEmail;
    private String userPassword;
    private Boolean userActiveState;
    private LocalDate accountCreatedOn;
    private LocalDate accountModifiedOn;
}
