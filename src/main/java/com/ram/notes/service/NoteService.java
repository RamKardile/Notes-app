package com.ram.notes.service;

import com.ram.notes.dto.NoteRequest;
import com.ram.notes.exception.NoteNotFoundException;
import com.ram.notes.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {

    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    public Note findById(Long id) {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoteNotFoundException(id);
        }
        return note;
    }

    public Note create(NoteRequest request) {
        Long id = idSequence.incrementAndGet();
        Note note = new Note(id, request.title(), request.content());
        notes.put(id, note);
        return note;
    }

    public void delete(Long id) {
        if (notes.remove(id) == null) {
            throw new NoteNotFoundException(id);
        }
    }
}
