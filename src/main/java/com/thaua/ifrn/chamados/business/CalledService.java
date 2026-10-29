package com.thaua.ifrn.chamados.business;

import com.thaua.ifrn.chamados.domain.enums.CalledStatus;
import com.thaua.ifrn.chamados.domain.enums.Priority;
import com.thaua.ifrn.chamados.http.dto.request.calleds.CalledRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledWithInteractionsResponse;
import com.thaua.ifrn.chamados.http.exceptions.CalledNotFoundException;
import com.thaua.ifrn.chamados.http.exceptions.CategoryNotFoundException;
import com.thaua.ifrn.chamados.http.exceptions.InvalidCalledOperation;
import com.thaua.ifrn.chamados.persistence.entities.CalledEntity;
import com.thaua.ifrn.chamados.persistence.entities.CategoryEntity;
import com.thaua.ifrn.chamados.persistence.entities.TechnicalEntity;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.mappers.CalledMapper;
import com.thaua.ifrn.chamados.persistence.repositories.CalledRepository;
import com.thaua.ifrn.chamados.persistence.repositories.CategoryRepository;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public CalledWithInteractionsResponse fetch(UUID calledId) {
        CalledEntity calledFound = this.calledRepository.findByIdWithInteractions(calledId)
                .orElseThrow(CalledNotFoundException::new);

        return this.calledMapper.mapWithInteractions(calledFound);
    }

    public Page<CalledResponse> list(Pageable pageable, String title, String description, String place, Priority priority, CalledStatus status, String solution, String requesterName, String technicalName, String categoryName) {
        Specification<CalledEntity> byTitle = ((root, query, criteriaBuilder) ->
                (title == null || title.isBlank())
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title + "%"
                )
        );

        Specification<CalledEntity> byDescription = ((root, query, criteriaBuilder) ->
                (description == null || description.isBlank())
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("description")),
                        "%" + description + "%"
                )
        );

        Specification<CalledEntity> byPlace = ((root, query, criteriaBuilder) ->
                (place == null || place.isBlank())
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("place")),
                        "%" + place + "%"
                )
        );

        Specification<CalledEntity> byPriority = ((root, query, criteriaBuilder) ->
                (priority == null)
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get("priority"), priority)
        );

        Specification<CalledEntity> byStatus = ((root, query, criteriaBuilder) ->
                (status == null)
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get("status"), status)
        );

        Specification<CalledEntity> bySolution  = ((root, query, criteriaBuilder) ->
                (solution == null || solution.isBlank())
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("solution")),
                        "%" + solution + "%"
                )
        );

        Specification<CalledEntity> byRequester = (root, query, criteriaBuilder) -> {
            if (requesterName == null || requesterName.isBlank()) return criteriaBuilder.conjunction();

            Join<CalledEntity, UserEntity> user = root.join("requesterName");

            return criteriaBuilder.like(
                    criteriaBuilder.lower(user.get("name")),
                    "%" + requesterName + "%"
            );
        };

        Specification<CalledEntity> byTechnical = (root, query, criteriaBuilder) -> {
            if (technicalName == null || technicalName.isBlank()) return criteriaBuilder.conjunction();

            Join<CalledEntity, TechnicalEntity> technical = root.join("technicalName");

            return criteriaBuilder.like(
                    criteriaBuilder.lower(technical.get("name")),
                    "%" + technicalName + "%"
            );
        };

        Specification<CalledEntity> byCategory = (root, query, criteriaBuilder) -> {
            if (categoryName == null || categoryName.isBlank()) return criteriaBuilder.conjunction();

            Join<CalledEntity, CategoryEntity> category = root.join("categoryName");

            return criteriaBuilder.like(
                    criteriaBuilder.lower(category.get("name")),
                    "%" + categoryName + "%"
            );
        };

        Specification<CalledEntity> specification = byTitle.and(byDescription)
                .and(byPlace)
                .and(byPriority)
                .and(byStatus)
                .and(bySolution)
                .and(byRequester)
                .and(byTechnical)
                .and(byCategory);
        
        return this.calledRepository.findAll(specification, pageable)
                .map(this.calledMapper::map);
    }

    public Page<CalledResponse> listByUser(UUID userId, Pageable pageable) {
        return this.calledRepository.findAllByUserId(userId, pageable)
                .map(this.calledMapper::map);

    }

    public CalledResponse cancel(UserEntity currentUser, UUID calledId) {
        CalledEntity calledFound = this.calledRepository.findByIdAndRequesterId(calledId, currentUser.getId())
                .orElseThrow(CalledNotFoundException::new);

        if (calledFound.isNotOpen()) throw new InvalidCalledOperation("Não é possível cancelar um chamado que não está aberto");

        calledFound.moveToCancelled();

        return this.calledMapper.map(this.calledRepository.save(calledFound));
    }
}
