package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.model.EstudanteEntity;
import com.flahlks.cursos.database.repository.CursoRepository;
import com.flahlks.cursos.database.repository.EstudanteRepository;
import com.flahlks.cursos.dto.CursoDto;
import com.flahlks.cursos.dto.EstudanteDadosDto;
import com.flahlks.cursos.dto.EstudanteDto;
import com.flahlks.cursos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;

    public Long cadastrarEstudante(EstudanteDto estudanteDto) {
        return estudanteRepository.save(EstudanteEntity.builder()
                        .nome(estudanteDto.nome())
                        .email(estudanteDto.email())
                        .build())
                .getId();
    }

    public EstudanteDadosDto estudanteDados(Long estudanteId) {
        EstudanteEntity estudante = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));

        List<CursoDto> cursoDtoLista = new ArrayList<>();

        for (CursoEntity curso : estudante.getCursos()) {
            CursoDto cursoDto = CursoDto.builder()
                    .nome(curso.getNome())
                    .materia(curso.getMateria())
                    .build();
            cursoDtoLista.add(cursoDto);
        }
        return EstudanteDadosDto.builder()
                .nome(estudante.getNome())
                .email(estudante.getEmail())
                .cursos(cursoDtoLista)
                .build();
    }

    @Transactional
    public void matricularCurso(Long estudanteId, Long cursoId) {
        EstudanteEntity estudante = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));

        CursoEntity curso = cursoRepository
                .findById(cursoId)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        estudante.adicionarCurso(curso);
    }

    public List<EstudanteDto> estudantesCadastrados() {
        List<EstudanteDto> estudanteDtoLista = new ArrayList<>();

        for (EstudanteEntity estudante : estudanteRepository.findAll()) {
            EstudanteDto estudanteDto = EstudanteDto.builder()
                    .nome(estudante.getNome())
                    .email(estudante.getEmail())
                    .build();
            estudanteDtoLista.add(estudanteDto);
        }
        return estudanteDtoLista;
    }
}
