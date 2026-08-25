package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.model.EstudanteEntity;
import com.flahlks.cursos.database.repository.CursoRepository;
import com.flahlks.cursos.database.repository.EstudanteRepository;
import com.flahlks.cursos.dto.EstudanteDto;
import com.flahlks.cursos.exception.EstudanteInvalidoException;
import com.flahlks.cursos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;

    public Long cadastrarEstudante(EstudanteDto estudanteDto) {
        if (estudanteDto == null) {
            throw new EstudanteInvalidoException("Estudante não pode ser nulo");
        }
        if (estudanteDto.nome() == null || estudanteDto.nome().isBlank()) {
            throw new EstudanteInvalidoException("Nome de estudante inválido");
        }
        if (estudanteDto.email() == null || estudanteDto.email().isBlank()) {
            throw new EstudanteInvalidoException("Email inválido");
        }
        if (estudanteRepository.existsByEmail(estudanteDto.email())) {
            throw new EstudanteInvalidoException("Um estudante com esse email já existe");
        }
        return estudanteRepository.save(EstudanteEntity.builder()
                        .nome(estudanteDto.nome())
                        .email(estudanteDto.email())
                        .build())
                .getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void matricularCurso(Long estudanteId, Long cursoId) {
        EstudanteEntity estudante = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));

        CursoEntity curso = cursoRepository
                .findById(cursoId)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        estudante.adicionarCurso(curso);
    }
}
