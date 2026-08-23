package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.model.EstudanteEntity;
import com.flahlks.cursos.database.repository.EstudanteRepository;
import com.flahlks.cursos.dto.EstudanteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    private EstudanteRepository estudanteRepository;

    public void cadastrarEstudante(EstudanteDto estudanteDto) {
        estudanteRepository.save(EstudanteEntity.builder().
                nome(estudanteDto.nome()).
                email(estudanteDto.email()).
                build());
    }

    public List<CursoEntity> cursosEstudante(Long estudanteId) {
        EstudanteEntity estudante = estudanteRepository.findById(estudanteId).
                orElseThrow(() -> new RuntimeException("temporario"));

        if (estudante.getCursos() == null) {
            throw new IllegalArgumentException("temporario");
        }
        return new ArrayList<>(estudante.getCursos());
    }
}
