package io.github.erinleonid3_collab.observability_dashboard.note.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.time.Instant;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class NoteResponse {
    UUID id;
    String title;
    String content;
    Instant createdAt;
    Instant updatedAt;
}