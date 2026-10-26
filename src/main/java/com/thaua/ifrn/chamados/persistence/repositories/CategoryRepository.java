package com.thaua.ifrn.chamados.persistence.repositories;

import com.thaua.ifrn.chamados.persistence.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID>, JpaSpecificationExecutor<CategoryEntity> {
}
