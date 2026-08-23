package com.flahlks.cursos.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoDto(@NotBlank String nome, @NotBlank String materia) {
}
