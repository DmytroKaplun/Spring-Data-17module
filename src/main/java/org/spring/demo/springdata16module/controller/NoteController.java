package org.spring.demo.springdata16module.controller;

import lombok.RequiredArgsConstructor;
import org.spring.demo.springdata16module.service.NoteService;
import org.spring.demo.springdata16module.model.Note;
import org.spring.demo.springdata16module.model.dto.NoteRequest;
import org.spring.demo.springdata16module.model.dto.NoteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse create(@RequestBody NoteRequest note) {
        return noteService.add(note);
    }

    @GetMapping("/{id}")
    public NoteResponse getById(@PathVariable Long id) {
        return noteService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        noteService.deleteById(id);
    }

    @PutMapping("/{id}")
    public NoteResponse update(@PathVariable Long id,
                               @RequestBody NoteRequest note) {
        return noteService.update(id, note);
    }


    @GetMapping("/findAll")
    public Page<NoteResponse> findAll(@RequestParam(defaultValue = "0") int offset,
                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(offset, size);
        return noteService.findAll(pageable);
    }

    @GetMapping("/findAll/{title}")
    public List<NoteResponse> getNoteByTitle(@PathVariable String title) {
        return noteService.getNotesByTitle(title);
    }

}
