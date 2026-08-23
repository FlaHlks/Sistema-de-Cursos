package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.repository.CursoRepository;
import com.flahlks.cursos.dto.CursoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private CursoRepository cursoRepository;

    public void criarCurso(CursoDto cursoDto) {
        cursoRepository.save(CursoEntity.builder().
                nome(cursoDto.nome()).
                materia(cursoDto.materia())
                .build());
    }
}
