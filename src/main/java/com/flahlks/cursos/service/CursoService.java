package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.repository.CursoRepository;
import com.flahlks.cursos.dto.CursoDto;
import com.flahlks.cursos.exception.CursoInvalidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Long criarCurso(CursoDto cursoDto) {
        if (cursoDto == null) {
            throw new CursoInvalidoException("Curso não pode ser nulo");
        }
        if (cursoDto.nome() == null || cursoDto.nome().isBlank()) {
            throw new CursoInvalidoException("Nome de curso inválido");
        }
        if (cursoDto.materia() == null || cursoDto.materia().isBlank()) {
            throw new CursoInvalidoException("Matéria de curso inválida");
        }
        if (cursoRepository.existsByMateria(cursoDto.materia())) {
            throw new CursoInvalidoException("Um curso com está matéria já existe");
        }

        return cursoRepository.save(CursoEntity.builder()
                        .nome(cursoDto.nome())
                        .materia(cursoDto.materia())
                        .build())
                .getId();
    }
}
