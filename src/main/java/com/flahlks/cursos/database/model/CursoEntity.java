package com.flahlks.cursos.database.model;

import com.flahlks.cursos.exception.MatriculaInvalidaException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professorEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inscricao",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudante_id"))
    private List<EstudanteEntity> estudantesEntity = new ArrayList<>();

    public void adicionarEstudante(EstudanteEntity estudante) throws MatriculaInvalidaException {
        if (estudantesEntity.contains(estudante)) {
            throw new MatriculaInvalidaException("O estudante já está matriculado neste curso");
        }
        estudantesEntity.add(estudante);
    }
}
