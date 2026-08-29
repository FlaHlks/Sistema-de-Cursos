package com.flahlks.cursos.database.model;

import com.flahlks.cursos.exception.AtribuicaoInvalidaException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "professor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "professorEntity", fetch = FetchType.LAZY)
    private List<CursoEntity> cursos = new ArrayList<>();

    public void adicionarCurso(CursoEntity curso) {
        this.cursos.add(curso);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorEntity professor = (ProfessorEntity) o;
        return id != null && id.equals(professor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
