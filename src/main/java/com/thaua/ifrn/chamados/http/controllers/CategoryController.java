package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.services.CategoryService;
import com.thaua.ifrn.chamados.http.dto.request.categories.CreateCategoryRequest;
import com.thaua.ifrn.chamados.http.dto.request.categories.UpdateCategoryRequest;
import com.thaua.ifrn.chamados.http.dto.response.categories.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(this.categoryService.create(request));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> fetch(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(this.categoryService.fetch(categoryId));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> list(
            @ParameterObject @PageableDefault Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String sectorName,
            @RequestParam(required = false) UUID sectorId
    ) {
        return ResponseEntity.ok(this.categoryService.list(pageable, name, description, sectorName, sectorId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable UUID categoryId,
            @RequestBody UpdateCategoryRequest request
    ) {
        return ResponseEntity.ok(this.categoryService.update(categoryId, request));
    }

}
