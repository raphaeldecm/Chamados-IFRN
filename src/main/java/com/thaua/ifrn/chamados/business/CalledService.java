package com.thaua.ifrn.chamados.business;

import com.thaua.ifrn.chamados.http.dto.request.calleds.CalledRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.http.exceptions.CategoryNotFoundException;
import com.thaua.ifrn.chamados.persistence.entities.CalledEntity;
import com.thaua.ifrn.chamados.persistence.entities.CategoryEntity;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.mappers.CalledMapper;
import com.thaua.ifrn.chamados.persistence.repositories.CalledRepository;
import com.thaua.ifrn.chamados.persistence.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalledService {
    private final CalledRepository calledRepository;
    private final CategoryRepository categoryRepository;
    private final CalledMapper calledMapper;

    public CalledResponse openCalled(UserEntity currentUser, CalledRequest request) {
        CategoryEntity categoryFound = this.categoryRepository.findById(request.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        CalledEntity preSaved = this.calledMapper.map(request);
        preSaved.setRequester(currentUser);
        preSaved.setCategory(categoryFound);

        return this.calledMapper.map(this.calledRepository.save(preSaved));
    }
}
