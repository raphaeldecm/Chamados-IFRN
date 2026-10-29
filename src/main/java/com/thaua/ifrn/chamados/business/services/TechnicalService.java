package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.domain.enums.CalledStatus;
import com.thaua.ifrn.chamados.http.dto.request.CalledSolutionRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.http.exceptions.CalledNotFoundException;
import com.thaua.ifrn.chamados.http.exceptions.InvalidCalledOperation;
import com.thaua.ifrn.chamados.http.exceptions.TechnicalNotFoundException;
import com.thaua.ifrn.chamados.persistence.entities.CalledEntity;
import com.thaua.ifrn.chamados.persistence.entities.TechnicalEntity;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.mappers.CalledMapper;
import com.thaua.ifrn.chamados.persistence.repositories.CalledRepository;
import com.thaua.ifrn.chamados.persistence.repositories.TechnicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TechnicalService {
    private final CalledRepository calledRepository;
    private final TechnicalRepository technicalRepository;
    private final CalledMapper calledMapper;

    public CalledResponse assign(UserEntity currentUser, UUID calledId) {
        TechnicalEntity technicalFound = this.technicalRepository.findById(currentUser.getId())
                .orElseThrow(TechnicalNotFoundException::new);

        CalledEntity calledFound = this.calledRepository.findBySectorIdAndStatus(calledId, technicalFound.getSector().getId(), CalledStatus.OPENED)
                .orElseThrow(CalledNotFoundException::new);

        calledFound.moveToSolving();
        calledFound.setTechnical(technicalFound);

        return this.calledMapper.map(this.calledRepository.save(calledFound));
    }

    public CalledResponse solve(UserEntity userEntity, UUID calledId, CalledSolutionRequest request) {
        CalledEntity calledFound = this.calledRepository.findByIdAndTechnicalId(calledId, userEntity.getId())
                .orElseThrow(CalledNotFoundException::new);

        if (calledFound.isNotSolving()) throw new InvalidCalledOperation("Não é possível resolver um chamado que não está em atendimento");

        calledFound.setSolution(request.solution());
        calledFound.moveToSolved();

        return this.calledMapper.map(this.calledRepository.save(calledFound));
    }
}
