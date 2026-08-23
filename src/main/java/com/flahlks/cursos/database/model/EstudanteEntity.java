package com.flahlks.cursos.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    private Set<CursoEntity> cursos = new HashSet<>();
}
