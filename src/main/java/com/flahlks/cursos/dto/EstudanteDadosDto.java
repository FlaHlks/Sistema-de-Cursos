package com.flahlks.cursos.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record EstudanteDadosDto(String nome, String email, List<CursoDto> cursos) {
}
