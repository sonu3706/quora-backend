package com.quora.app.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class UserAuth {
    @Id
    private Integer id;
    private String userEmail;
    private String userPassword;
    private Boolean userActiveState;
    private LocalDate accountModifiedOn;
}
