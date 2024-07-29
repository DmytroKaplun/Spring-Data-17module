package org.spring.demo.springdata16module.model.dto;

import lombok.Data;

@Data
public class NoteRequest {
    private String title;
    private String content;
}
