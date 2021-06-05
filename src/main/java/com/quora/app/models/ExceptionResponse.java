package com.quora.app.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    /*TODO:- Implement Builder pattern here*/
    private String exceptionMessage;
    private LocalDateTime dateTime;
}
