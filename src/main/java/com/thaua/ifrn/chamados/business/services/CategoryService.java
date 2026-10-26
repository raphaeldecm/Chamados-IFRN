package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.http.dto.request.categories.CreateCategoryRequest;
import com.thaua.ifrn.chamados.http.dto.response.categories.CategoryResponse;
import com.thaua.ifrn.chamados.http.exceptions.SectorNotFoundException;
import com.thaua.ifrn.chamados.persistence.entities.CategoryEntity;
import com.thaua.ifrn.chamados.persistence.entities.SectorEntity;
import com.thaua.ifrn.chamados.persistence.mappers.CategoryMapper;
import com.thaua.ifrn.chamados.persistence.repositories.CategoryRepository;
import com.thaua.ifrn.chamados.persistence.repositories.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
