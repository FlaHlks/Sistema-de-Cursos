package com.flahlks.cursos.dto;

import jakarta.validation.constraints.Email;

public record EstudanteDto( String nome, @Email String email) {
}
