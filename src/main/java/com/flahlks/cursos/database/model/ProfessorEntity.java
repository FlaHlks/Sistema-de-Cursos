package com.flahlks.cursos.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "professorEntity", fetch = FetchType.LAZY)
    private Set<CursoEntity> cursos = new HashSet<>();

}
