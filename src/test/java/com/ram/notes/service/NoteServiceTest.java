package com.ram.notes.service;

import com.ram.notes.dto.NoteRequest;
import com.ram.notes.exception.NoteNotFoundException;
import com.ram.notes.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    private NoteService service;

    @BeforeEach
    void setUp() {
        service = new NoteService();
    }

    @Test
    void createAssignsIdAndStoresNote() {
        Note note = service.create(new NoteRequest("Title", "Content"));

        assertNotNull(note.id());
        assertEquals("Title", note.title());
        assertEquals("Content", note.content());
        assertEquals(1, service.findAll().size());
    }

    @Test
    void findByIdReturnsExistingNote() {
        Note created = service.create(new NoteRequest("Title", "Content"));

        Note found = service.findById(created.id());

        assertEquals(created, found);
    }

    @Test
    void findByIdThrowsWhenNoteDoesNotExist() {
        assertThrows(NoteNotFoundException.class, () -> service.findById(999L));
    }
}
