package io.github.erinleonid3_collab.observability_dashboard.note;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    @CreationTimestamp
    @Column(name = "created_At", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
