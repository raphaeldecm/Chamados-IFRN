package com.thaua.ifrn.chamados.persistence.repositories;

import com.thaua.ifrn.chamados.persistence.entities.CalledEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CalledRepository extends JpaRepository<CalledEntity, UUID>, JpaSpecificationExecutor<CalledEntity> {
    @Query("""
        SELECT c FROM CalledEntity c
        JOIN FETCH c.interactions
        WHERE c.id = :calledId
    """)
    Optional<CalledEntity> findByIdWithInteractions(@Param("calledId") UUID calledId);
}
