package com.flahlks.cursos.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "curso")
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professorEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inscricao",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudante_id"))
    private Set<EstudanteEntity> estudantesEntity;

}
