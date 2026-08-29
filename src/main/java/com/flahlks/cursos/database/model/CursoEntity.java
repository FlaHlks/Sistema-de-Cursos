package com.flahlks.cursos.database.model;

import com.flahlks.cursos.exception.MatriculaInvalidaException;
import com.flahlks.cursos.exception.AtribuicaoInvalidaException;
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
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professorEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inscricao",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudante_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"curso_id", "estudante_id"}))
    private List<EstudanteEntity> estudantesEntity = new ArrayList<>();

    public void adicionarEstudante(EstudanteEntity estudante) {
        estudantesEntity.add(estudante);
    }

    public void adicionarProfessor(ProfessorEntity professor) {
        if (professorEntity != null) {
            throw new AtribuicaoInvalidaException("Este curso já tem um professor");
        }
        this.professorEntity = professor;
        professor.adicionarCurso(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CursoEntity curso = (CursoEntity) o;
        return id != null && id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
