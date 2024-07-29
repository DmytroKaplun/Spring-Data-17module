package org.spring.demo.homework16springdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "notes")
public class Note {
    @Id
    private Long id;
    private String title;
    private String content;
}
