package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.http.dto.request.categories.CreateCategoryRequest;
import com.thaua.ifrn.chamados.http.dto.request.categories.UpdateCategoryRequest;
import com.thaua.ifrn.chamados.http.dto.response.categories.CategoryResponse;
import com.thaua.ifrn.chamados.http.exceptions.CategoryNotFoundException;
import com.thaua.ifrn.chamados.http.exceptions.SectorNotFoundException;
import com.thaua.ifrn.chamados.persistence.entities.CategoryEntity;
import com.thaua.ifrn.chamados.persistence.entities.SectorEntity;
import com.thaua.ifrn.chamados.persistence.mappers.CategoryMapper;
import com.thaua.ifrn.chamados.persistence.repositories.CategoryRepository;
import com.thaua.ifrn.chamados.persistence.repositories.SectorRepository;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SectorRepository sectorRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse create(CreateCategoryRequest request) {
        SectorEntity sectorFound = this.sectorRepository.findById(request.sectorId())
                .orElseThrow(SectorNotFoundException::new);

        CategoryEntity preSaved = this.categoryMapper.map(request);
        preSaved.setSector(sectorFound);

        return this.categoryMapper.map(this.categoryRepository.save(preSaved));
    }

    public CategoryResponse fetch(UUID categoryId) {
        CategoryEntity categoryFound = this.categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        return this.categoryMapper.map(categoryFound);
    }

    public Page<CategoryResponse> list(Pageable pageable, String name, String description, String sectorName, UUID sectorId) {
        Specification<CategoryEntity> byName = ((root, query, criteriaBuilder) ->
                name == null || name.isBlank()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(("name"))),
                        "%" + name + "%"
                )
        );

        Specification<CategoryEntity> byDescription = ((root, query, criteriaBuilder) ->
                    description == null || description.isBlank()
                            ? criteriaBuilder.conjunction()
                            : criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(("description"))),
                            "%" + description + "%"
                    )
                );

        Specification<CategoryEntity> bySectorName = (root, query, criteriaBuilder) -> {
            if (sectorName == null || sectorName.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            Join<CategoryEntity, SectorEntity> sector = root.join("sector");

            return criteriaBuilder.like(
                    criteriaBuilder.lower(sector.get("name")),
                    "%" + sectorName.toLowerCase() + "%"
            );
        };

        Specification<CategoryEntity> bySectorId = (root, query, criteriaBuilder) -> {
            if (sectorId == null) {
                return criteriaBuilder.conjunction();
            }

            Join<CategoryEntity, SectorEntity> sector = root.join("sector");

            return criteriaBuilder.equal(sector.get("id"), sectorId);
        };

        Specification<CategoryEntity> specification = byName.and(byDescription).and(bySectorName).and(bySectorId);

        return this.categoryRepository.findAll(specification, pageable)
                .map(this.categoryMapper::map);
    }

    public CategoryResponse update(UUID categoryId, UpdateCategoryRequest request) {
        CategoryEntity categoryFound = this.categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        categoryFound.update(this.categoryMapper.map(request));

        if (!categoryFound.getSector().getId().equals(request.sectorId())) {
            SectorEntity sectorFound = this.sectorRepository.findById(request.sectorId())
                    .orElseThrow(SectorNotFoundException::new);
            categoryFound.setSector(sectorFound);
        }

        return this.categoryMapper.map(this.categoryRepository.save(categoryFound));
    }
}
