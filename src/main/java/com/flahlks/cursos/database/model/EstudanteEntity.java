package com.flahlks.cursos.database.model;

import com.flahlks.cursos.exception.MatriculaInvalidaException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EstudanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "estudantesEntity")
    private List<CursoEntity> cursos = new ArrayList<>();

    public void adicionarCurso (CursoEntity curso) throws MatriculaInvalidaException {
        if (cursos.contains(curso)) {
            throw new MatriculaInvalidaException("Esse estudante já está matriculado neste curso");
        }
        cursos.add(curso);
        curso.adicionarEstudante(this);
    }
}
