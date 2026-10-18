package com.thaua.ifrn.chamados.persistence.repositories;

import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("""
        SELECT u FROM UserEntity u
        WHERE u.email = :email
        AND u.active IS TRUE
    """)
    Optional<UserEntity> findByEmailAndActive(@Param("email") String email);

    @Query("""
        SELECT u FROM UserEntity u
        INNER JOIN u.token t
        WHERE t.accessToken = :token
        AND t.expired IS NOT TRUE
        AND u.active IS TRUE
    """)
    Optional<UserEntity> findActiveUserByValidToken(@Param("token") String token);
}
