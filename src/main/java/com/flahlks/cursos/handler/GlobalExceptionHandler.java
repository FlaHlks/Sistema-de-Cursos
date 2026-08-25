package com.flahlks.cursos.handler;

import com.flahlks.cursos.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> exceptionHandle(Exception exception) {
        return criarResposta("Ocorreu um erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MatriculaInvalidaException.class)
    public ResponseEntity<RespostaErro> matriculaInvalidaHandler(MatriculaInvalidaException exception) {
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaErro> notFoundHandler(NotFoundException exception) {
        return criarResposta(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CursoInvalidoException.class)
    public ResponseEntity<RespostaErro> cursoInvalidoHandler(CursoInvalidoException exception){
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EstudanteInvalidoException.class)
    public ResponseEntity<RespostaErro> estudanteInvalidoHandler(EstudanteInvalidoException exception) {
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProfessorInvalidoException.class)
    public ResponseEntity<RespostaErro> professorInvalidoHandler(ProfessorInvalidoException exception) {
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RespostaErro> criarResposta(String mensagem, HttpStatus status) {
        RespostaErro resposta = RespostaErro.builder()
                .mensagem(mensagem)
                .status(status.value())
                .build();
        return ResponseEntity.status(status).body(resposta);
    }
}
