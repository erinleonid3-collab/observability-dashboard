package io.github.erinleonid3_collab.observability_dashboard.note;

import io.github.erinleonid3_collab.observability_dashboard.exception.NoteNotFoundException;
import io.github.erinleonid3_collab.observability_dashboard.note.dto.NoteRequest;
import io.github.erinleonid3_collab.observability_dashboard.note.dto.NoteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
public class NoteService {

    private final NoteRepository noteRepository;


    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional
    public NoteResponse create(NoteRequest noteRequest) {
        Note note = mapToEntity(noteRequest);
        noteRepository.save(note);
        return mapToResponse(note);
    }

    public NoteResponse getById(UUID id) {
        NoteResponse noteResponse = mapToResponse(noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note with id " + id + " not found")));
        return noteResponse;
    }

    public Page<NoteResponse> getAll(Pageable pageable) {
        Page<Note> notePage = noteRepository.findAll(pageable);

        return notePage.map(this::mapToResponse);
    }

    @Transactional
    public NoteResponse update(UUID id, NoteRequest updateRequest) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note with id " + id + " not found"));
        note.setTitle(updateRequest.getTitle());
        note.setContent(updateRequest.getContent());

        return mapToResponse(noteRepository.save(note));
    }

    @Transactional
    public void delete(UUID id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException("Note with id " + id + " not found");
        }
        noteRepository.deleteById(id);
    }

    private NoteResponse mapToResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

    private Note mapToEntity(NoteRequest noteRequest) {
        Note note = new Note();
        note.setContent(noteRequest.getContent());
        note.setTitle(noteRequest.getTitle());
        return note;
    }
}
