package com.thaua.ifrn.chamados.persistence.repositories;

import com.thaua.ifrn.chamados.persistence.entities.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SectorRepository extends JpaRepository<SectorEntity, UUID> {
}
