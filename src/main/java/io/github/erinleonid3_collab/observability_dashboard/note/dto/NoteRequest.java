package io.github.erinleonid3_collab.observability_dashboard.note.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class NoteRequest {

    @NotBlank(message = "Title must not be empty")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;

    @Size(max = 10000, message = "Content must not exceed 10000 characters")
    private String content;

}
