package io.github.erinleonid3_collab.observability_dashboard.note;

import io.github.erinleonid3_collab.observability_dashboard.note.dto.NoteRequest;
import io.github.erinleonid3_collab.observability_dashboard.note.dto.NoteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(noteService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<NoteResponse>> getAllNotes(
            @PageableDefault(page = 0, size = 10, sort = "createdAt") Pageable pageable
    ) {
        Page<NoteResponse> notes = noteService.getAll(pageable);
        return ResponseEntity.ok(notes);
    }

    @PostMapping()
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody NoteRequest noteRequest
    ) {
        NoteResponse createdNoteResponse = noteService.create(noteRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdNoteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable UUID id,
            @Valid @RequestBody NoteRequest noteRequest
    ) {
        NoteResponse noteResponse = noteService.update(id, noteRequest);
        return ResponseEntity.ok(noteResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable UUID id) {
        noteService.delete(id);
    }
}
