package com.spring.react.demo.jpa;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
@Table
public class Book {

    @Id
    private Long id;

    private String title;

    private String author;

}