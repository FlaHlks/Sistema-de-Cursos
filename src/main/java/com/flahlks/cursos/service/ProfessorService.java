package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.model.ProfessorEntity;
import com.flahlks.cursos.database.repository.ProfessorRepository;
import com.flahlks.cursos.dto.CursoDto;
import com.flahlks.cursos.dto.ProfessorDadosDto;
import com.flahlks.cursos.dto.ProfessorDto;
import com.flahlks.cursos.exception.NotFoundException;
import com.flahlks.cursos.exception.ProfessorInvalidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public Long cadastrarProfessor(ProfessorDto professorDto) {
        return professorRepository.save(ProfessorEntity.builder()
                        .nome(professorDto.nome())
                        .build())
                .getId();
    }

    public List<ProfessorDto> professoresCadastrados() {

        List<ProfessorDto> professorDtoLista = new ArrayList<>();

        for (ProfessorEntity professor : professorRepository.findAll()) {
            ProfessorDto professorDto = ProfessorDto.builder()
                    .nome(professor.getNome())
                    .build();
            professorDtoLista.add(professorDto);
        }
        return professorDtoLista;
    }

    public ProfessorDadosDto dadosProfessor(Long professorId) {
        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado"));

        List<CursoDto> cursoDtoLista = new ArrayList<>();

        for (CursoEntity curso : professor.getCursos()) {
            CursoDto cursoDto = CursoDto.builder()
                    .nome(curso.getNome())
                    .materia(curso.getMateria())
                    .build();
            cursoDtoLista.add(cursoDto);
        }
        return ProfessorDadosDto.builder()
                .nome(professor.getNome())
                .curso(cursoDtoLista)
                .build();
    }
}
