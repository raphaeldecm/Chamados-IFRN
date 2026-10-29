package com.thaua.ifrn.chamados.persistence.entities;

import com.thaua.ifrn.chamados.domain.enums.CalledStatus;
import com.thaua.ifrn.chamados.domain.enums.Priority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Table(name = "calleds_tb")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalledEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "called_id")
    private UUID id;

    private String title;

    private String description;

    private String place;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CalledStatus status = CalledStatus.OPENED;

    private String solution;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private UserEntity requester;

    @ManyToOne
    @JoinColumn(name = "technical_id")
    private TechnicalEntity technical;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "called")
    List<InteractionEntity> interactions;

    @Builder.Default
    @Column(name = "opening_date")
    @CreationTimestamp
    private LocalDateTime openingDate = LocalDateTime.now();

    @Builder.Default
    @CreationTimestamp
    @Column(name = "closed_date")
    private LocalDateTime closedDate = LocalDateTime.now();

    public void moveToSolving() {
        this.status = CalledStatus.SOLVING;
    }

    public void moveToSolved() {
        this.status = CalledStatus.SOLVED;
    }

    public void moveToCancelled() {
        this.status = CalledStatus.CANCELLED;
    }

    public boolean isOpen() {
        return this.status.equals(CalledStatus.OPENED);
    }

    public boolean isNotOpen() {
        return !this.isOpen();
    }

    public boolean isSolving() {
        return this.status.equals(CalledStatus.SOLVED);
    }

    public boolean isNotSolving() {
        return !this.isSolving();
    }
}
