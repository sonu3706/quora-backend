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
public class Category {
    @Id
    private String categoryId;
    private String categoryType;
    private LocalDate categoryAddedOn;
    private Boolean categoryActiveState;
}
