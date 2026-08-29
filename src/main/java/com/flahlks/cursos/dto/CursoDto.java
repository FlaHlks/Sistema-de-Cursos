package com.flahlks.cursos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CursoDto(@NotBlank @Size(min = 3, max = 20) String nome, @NotBlank @Size(min = 3, max = 100) String materia) {
}
