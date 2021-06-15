package com.quora.app.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {
    private Integer categoryId;
    private String categoryType;
    private LocalDate categoryAddedOn;
    private Boolean categoryActiveState;
}
