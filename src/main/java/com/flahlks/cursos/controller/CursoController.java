package com.flahlks.cursos.controller;

import com.flahlks.cursos.dto.CursoDadosDto;
import com.flahlks.cursos.dto.CursoDto;
import com.flahlks.cursos.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/curso")
@Validated
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDto>> cursosCadastrados() {
        return ResponseEntity.ok().body(cursoService.cursosCadastrados());
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<CursoDadosDto> dadosDoCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok().body(cursoService.dadosDoCurso(cursoId));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<CursoDto>> procurarCursosPeloProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok().body(cursoService.procurarCursosPorProfessor(professorId));
    }

    @PostMapping
    public ResponseEntity<Void> criarCurso(@Valid @RequestBody CursoDto cursoDto) {
        Long cursoId = cursoService.criarCurso(cursoDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cursoId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{cursoId}/professor/{professorId}")
    public ResponseEntity<Void> atribuirProfessor(@PathVariable Long cursoId, @PathVariable Long professorId) {
        cursoService.atribuirProfessor(cursoId, professorId);
        return ResponseEntity.ok().build();
    }
}
