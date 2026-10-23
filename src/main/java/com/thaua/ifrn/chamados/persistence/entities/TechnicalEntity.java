package com.thaua.ifrn.chamados.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "technicals_tb")
@SuperBuilder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class TechnicalEntity extends UserEntity{
    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorEntity sector;
}
