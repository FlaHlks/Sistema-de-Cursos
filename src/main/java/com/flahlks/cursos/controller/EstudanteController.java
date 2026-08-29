package com.flahlks.cursos.controller;

import com.flahlks.cursos.dto.CursoDto;
import com.flahlks.cursos.dto.EstudanteDadosDto;
import com.flahlks.cursos.dto.EstudanteDto;
import com.flahlks.cursos.service.EstudanteService;
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
@RequestMapping("/v1/estudante")
@Validated
public class EstudanteController {

    private final EstudanteService estudanteService;

    @GetMapping
    public ResponseEntity<List<EstudanteDto>> estudantesCadastrados() {
        return ResponseEntity.ok().body(estudanteService.estudantesCadastrados());
    }

    @GetMapping("/{estudanteId}")
    public ResponseEntity<EstudanteDadosDto> estudanteDados(@PathVariable Long estudanteId) {
        return ResponseEntity.ok().body(estudanteService.estudanteDados(estudanteId));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarEstudante(@Valid @RequestBody EstudanteDto estudanteDto) {
        Long estudanteId = estudanteService.cadastrarEstudante(estudanteDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(estudanteId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{estudanteId}/curso/{cursoId}")
    public ResponseEntity<Void> matricularEstudanteAoCurso(@PathVariable Long estudanteId, @PathVariable Long cursoId) {
        estudanteService.matricularCurso(estudanteId, cursoId);
        return ResponseEntity.noContent().build();
    }
}
