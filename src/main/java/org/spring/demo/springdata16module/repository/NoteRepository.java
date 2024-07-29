package org.spring.demo.homework16springdata.repository;

import org.spring.demo.homework16springdata.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
