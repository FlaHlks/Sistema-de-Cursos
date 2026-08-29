package com.flahlks.cursos.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProfessorDadosDto(String nome, List<CursoDto> curso) {
}
