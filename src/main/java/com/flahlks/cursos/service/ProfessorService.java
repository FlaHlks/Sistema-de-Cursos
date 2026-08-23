package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.ProfessorEntity;
import com.flahlks.cursos.database.repository.ProfessorRepository;
import com.flahlks.cursos.dto.ProfessorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private ProfessorRepository professorRepository;

    public void cadastrarProfessor(ProfessorDto professorDto) {
        professorRepository.save(ProfessorEntity.builder()
                .nome(professorDto.nome())
                .build());
    }
}
