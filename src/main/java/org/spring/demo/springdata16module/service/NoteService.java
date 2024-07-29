package org.spring.demo.springdata16module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.springdata16module.model.Note;
import org.spring.demo.springdata16module.model.dto.NoteRequest;
import org.spring.demo.springdata16module.model.dto.NoteResponse;
import org.spring.demo.springdata16module.repository.NoteRepository;
import org.spring.demo.springdata16module.util.NoteMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public Note add(Note note) {
        noteRepository.save(note);
        logger.info("Note added: {}", note);
        return note;
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



//    public Optional<List<Note>> listAll() {
//        Pageable pageable =
//        noteRepository.findAll(p)
//        logger.info("Listing all notes: {}", noteList);
//        return Optional.of(noteList);
//    }
}
