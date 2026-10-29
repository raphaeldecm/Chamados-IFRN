package com.thaua.ifrn.chamados.persistence.repositories;

import com.thaua.ifrn.chamados.domain.enums.CalledStatus;
import com.thaua.ifrn.chamados.persistence.entities.CalledEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Optional<CalledEntity> findByIdWithInteractions(@Param(value = "calledId") UUID calledId);

    @Query("""
        SELECT c FROM CalledEntity c
        INNER JOIN c.requester r
        INNER JOIN c.technical t
        WHERE r.id = :userID
        OR t.id = :userId
    """)
    Page<CalledEntity> findAllByUserId(@Param(value = "userId") UUID userId, @Param(value = "pageable") Pageable pageable);

    @Query("""
        SELECT c FROM CalledEntity c
        INNER JOIN c.technical t
        INNER JOIN t.sector s
        WHERE c.id = :calledId
        AND s.id = :sectorId
        AND c.status = :status
    """)
    Optional<CalledEntity> findBySectorIdAndStatus(@Param(value = "calledId") UUID calledId, @Param(value = "sectorId") UUID sectorId, @Param(value = "status") CalledStatus calledStatus);

    @Query("""  
        SELECT c FROM CalledEntity c
        INNER JOIN c.technical t
        WHERE c.id = :calledId
        AND t.id = :technicalId
        AND c.status = :status
    """)
    Optional<CalledEntity> findByIdAndTechnicalIdAndStatus(@Param(value = "calledId") UUID calledId, @Param(value = "technicalId") UUID technicalId, @Param(value = "status") CalledStatus status);
}
