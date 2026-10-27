package com.thaua.ifrn.chamados.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "interactions_tb")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InteractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "interaction_id")
    private UUID id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "called_id")
    private CalledEntity called;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
