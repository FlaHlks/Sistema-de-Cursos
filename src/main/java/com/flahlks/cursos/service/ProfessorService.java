package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.ProfessorEntity;
import com.flahlks.cursos.database.repository.ProfessorRepository;
import com.flahlks.cursos.dto.ProfessorDto;
import com.flahlks.cursos.exception.ProfessorInvalidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public Long cadastrarProfessor(ProfessorDto professorDto) {
        if (professorDto == null) {
            throw new ProfessorInvalidoException("Professor não pode ser nulo");
        }
        if (professorDto.nome() == null || professorDto.nome().isBlank()) {
            throw new ProfessorInvalidoException("Nome de professor inválido");
        }
        if (professorDto.materia() == null || professorDto.materia().isBlank()) {
            throw new ProfessorInvalidoException("Matéria de professor inválida");
        }
        if (professorRepository.existsByMateria(professorDto.materia())) {
            throw new ProfessorInvalidoException("Um professor com essa matéria já está cadastrado");
        }
        return professorRepository.save(ProfessorEntity.builder()
                        .nome(professorDto.nome())
                        .build())
                .getId();
    }
}
