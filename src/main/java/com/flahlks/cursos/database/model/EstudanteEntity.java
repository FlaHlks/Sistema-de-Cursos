package com.flahlks.cursos.database.model;

import jakarta.persistence.*;
import lombok.*;

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
}
