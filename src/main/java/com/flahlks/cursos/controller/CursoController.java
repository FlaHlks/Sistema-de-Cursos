package com.flahlks.cursos.controller;

import com.flahlks.cursos.dto.CursoDto;
import com.flahlks.cursos.exception.CursoInvalidoException;
import com.flahlks.cursos.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/curso")
@Validated
public class CursoController {

    private final CursoService cursoService;

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
}
