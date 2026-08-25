package com.flahlks.cursos.controller;

import com.flahlks.cursos.dto.EstudanteDto;
import com.flahlks.cursos.exception.EstudanteInvalidoException;
import com.flahlks.cursos.service.EstudanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/estudante")
@Validated
public class EstudanteController {

    private final EstudanteService estudanteService;

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
}
