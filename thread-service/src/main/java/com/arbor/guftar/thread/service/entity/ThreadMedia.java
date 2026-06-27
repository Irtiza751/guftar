package com.arbor.guftar.thread.service.entity;

import com.arbor.guftar.thread.service.valueobjects.MediaType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "thread_medias")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ThreadMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thread_media_seq_gen")
    @SequenceGenerator(
            name = "thread_media_seq_gen",
            sequenceName = "thread_media_seq",
            allocationSize = 1
    )
    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    @Column(columnDefinition = "TEXT")
    private String url;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thread_id", nullable = false)
    private Thread thread;

    private Integer position = 0;

    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
