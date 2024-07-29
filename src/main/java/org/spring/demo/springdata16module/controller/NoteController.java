package org.spring.demo.homework16springdata.controller;

import lombok.RequiredArgsConstructor;
import org.spring.demo.homework16springdata.NoteService;
import org.spring.demo.homework16springdata.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note create(@RequestBody Note note) {
        return noteService.add(note);
    }

    @GetMapping("/{id}")
    public Note getById(@PathVariable long id) {
        return noteService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        noteService.deleteById(id);
    }

}
