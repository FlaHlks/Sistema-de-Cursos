package com.flahlks.cursos.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfessorDto(@NotBlank String nome) {
}
