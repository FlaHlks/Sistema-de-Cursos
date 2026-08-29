package com.flahlks.cursos.service;

import com.flahlks.cursos.database.model.CursoEntity;
import com.flahlks.cursos.database.model.EstudanteEntity;
import com.flahlks.cursos.database.model.ProfessorEntity;
import com.flahlks.cursos.database.repository.CursoRepository;
import com.flahlks.cursos.database.repository.ProfessorRepository;
import com.flahlks.cursos.dto.CursoDadosDto;
import com.flahlks.cursos.dto.CursoDto;
import com.flahlks.cursos.dto.EstudanteDtoCurso;
import com.flahlks.cursos.dto.ProfessorDto;
import com.flahlks.cursos.exception.CursoInvalidoException;
import com.flahlks.cursos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;

    public Long criarCurso(CursoDto cursoDto) {
        return cursoRepository.save(CursoEntity.builder()
                        .nome(cursoDto.nome())
                        .materia(cursoDto.materia())
                        .build())
                .getId();
    }

    @Transactional
    public void atribuirProfessor(Long cursoId, Long professorId) {
        CursoEntity curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado"));

        curso.adicionarProfessor(professor);
    }

    public List<CursoDto> cursosCadastrados() {
        List<CursoDto> cursoDtoLista = new ArrayList<>();

        for (CursoEntity curso : cursoRepository.findAll()) {
            CursoDto cursoDto = CursoDto.builder()
                    .nome(curso.getNome())
                    .materia(curso.getMateria())
                    .build();
            cursoDtoLista.add(cursoDto);
        }
        return cursoDtoLista;
    }

    public CursoDadosDto dadosDoCurso(Long cursoId) {
        CursoEntity curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        List<EstudanteDtoCurso> estudantesLista = new ArrayList<>();

        for (EstudanteEntity estudante : curso.getEstudantesEntity()) {
            EstudanteDtoCurso estudanteDtoCurso = EstudanteDtoCurso.builder()
                    .nome(estudante.getNome())
                    .build();
            estudantesLista.add(estudanteDtoCurso);
        }

        ProfessorDto professorDto = ProfessorDto.builder()
                .nome(curso.getProfessorEntity().getNome())
                .build();

        return CursoDadosDto.builder()
                .nome(curso.getNome())
                .materia(curso.getMateria())
                .professor(professorDto)
                .estudantes(estudantesLista)
                .build();
    }

    public List<CursoDto> procurarCursosPorProfessor(Long professorId) {
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
        return cursoDtoLista;
    }
}
