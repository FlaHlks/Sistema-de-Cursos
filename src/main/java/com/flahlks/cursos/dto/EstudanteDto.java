package com.flahlks.cursos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record EstudanteDto(@NotBlank @Size(min = 3, max = 50) String nome,@NotBlank @Email String email) {
}
