package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.services.CategoryService;
import com.thaua.ifrn.chamados.http.dto.request.categories.CreateCategoryRequest;
import com.thaua.ifrn.chamados.http.dto.response.categories.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(this.categoryService.create(request));
    }
}
