package com.ram.notes.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteRequest(
        @NotBlank(message = "title is required") String title,
        String content) {
}
