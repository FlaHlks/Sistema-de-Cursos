package com.flahlks.cursos.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record CursoDadosDto(String nome, String materia, ProfessorDto professor, List<EstudanteDtoCurso> estudantes) {
}
