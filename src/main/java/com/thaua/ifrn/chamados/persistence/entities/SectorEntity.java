package com.thaua.ifrn.chamados.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Table(name = "sectors_tb")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "sector_id")
    private UUID id;
    private String name;
    private String acronym;

    @OneToMany(mappedBy = "sector")
    private List<TechnicalEntity> technicals;

    @OneToMany(mappedBy = "sector")
    private List<CategoryEntity> categories;
}
