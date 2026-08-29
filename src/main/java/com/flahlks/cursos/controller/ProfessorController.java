package com.flahlks.cursos.controller;

import com.flahlks.cursos.dto.ProfessorDadosDto;
import com.flahlks.cursos.dto.ProfessorDto;
import com.flahlks.cursos.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/v1/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> professoresCadastrados() {
        return ResponseEntity.ok().body(professorService.professoresCadastrados());
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorDadosDto> dadosProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok().body(professorService.dadosProfessor(professorId));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarProfessor(@Valid @RequestBody ProfessorDto professorDto) {
        Long professorId = professorService.cadastrarProfessor(professorDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professorId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
