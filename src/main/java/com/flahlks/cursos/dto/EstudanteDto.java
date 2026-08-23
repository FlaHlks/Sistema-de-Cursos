package com.flahlks.cursos.dto;

import com.flahlks.cursos.database.model.CursoEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record EstudanteDto(@NotBlank String nome, @NotBlank String email, @NotBlank Set<CursoEntity> cursos) {
}
