package com.thaua.ifrn.chamados.persistence.repositories;

import com.thaua.ifrn.chamados.persistence.entities.TechnicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TechnicalRepository extends JpaRepository<TechnicalEntity, UUID> {
}
