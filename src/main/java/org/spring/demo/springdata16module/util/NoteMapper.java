package org.spring.demo.springdata16module.util;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.spring.demo.springdata16module.model.Note;
import org.spring.demo.springdata16module.model.dto.NoteRequest;
import org.spring.demo.springdata16module.model.dto.NoteResponse;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface NoteMapper {
    NoteResponse toNoteResponse(Note note);
    Note toEntity(NoteRequest noteRequest);
}
