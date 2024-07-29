package org.spring.demo.springdata16module.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.springdata16module.model.Note;
import org.spring.demo.springdata16module.model.dto.NoteRequest;
import org.spring.demo.springdata16module.model.dto.NoteResponse;
import org.spring.demo.springdata16module.repository.NoteRepository;
import org.spring.demo.springdata16module.util.NoteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public NoteResponse add(NoteRequest noteRequest) {
        Note note = noteMapper.toEntity(noteRequest);
        noteRepository.save(note);
        logger.info("Note added: {}", note);
        return noteMapper.toNoteResponse(note);
    }

    public NoteResponse getById(Long id) {
        return noteMapper.toNoteResponse(noteRepository.findById(id).orElseThrow());
    }

    @Transactional
    public NoteResponse update(Long id, NoteRequest request) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return noteMapper.toNoteResponse(note);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public Page<NoteResponse> findAll(Pageable pageable) {
        Page<Note> notesPage = noteRepository.findAll(pageable);
        List<NoteResponse> noteResponses = notesPage.stream()
                .map(noteMapper::toNoteResponse)
                .toList();
        return new PageImpl<>(noteResponses, pageable, notesPage.getTotalElements());
    }

    public List<NoteResponse> getNotesByTitle(String title) {
        return noteRepository.findAll(getNoteByTitle(title)).stream()
                .map(noteMapper::toNoteResponse)
                .toList();
    }

    private Specification<Note> getNoteByTitle(String title) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(title)
                .map(t -> criteriaBuilder.equal(root.get("title"), title))
                .orElseGet(criteriaBuilder::conjunction);
    }


}
