package com.thaua.ifrn.chamados.persistence.mappers;

import com.thaua.ifrn.chamados.http.dto.request.categories.CreateCategoryRequest;
import com.thaua.ifrn.chamados.http.dto.response.categories.CategoryResponse;
import com.thaua.ifrn.chamados.persistence.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity map(CreateCategoryRequest request);
    CategoryResponse map(CategoryEntity entity);
}
