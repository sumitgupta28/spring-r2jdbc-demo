package com.spring.react.demo.jpa;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
@Table
@Builder
public class Book {

    @Id
    private Long id;

    private String title;

    private String author;

    @Version
    private int version; // This field is for optimistic locking

}