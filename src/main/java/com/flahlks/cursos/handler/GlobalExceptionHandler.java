package com.flahlks.cursos.handler;

import com.flahlks.cursos.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> exceptionHandle(Exception exception) {
        log.error("Erro inesperado", exception);
        return criarResposta("Ocorreu um erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MatriculaInvalidaException.class)
    public ResponseEntity<RespostaErro> matriculaInvalidaHandler(MatriculaInvalidaException exception) {
        criarLogWarn("Recurso inválido", exception);
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaErro> notFoundHandler(NotFoundException exception) {
        criarLogWarn("Recurso não encontrado", exception);
        return criarResposta(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CursoInvalidoException.class)
    public ResponseEntity<RespostaErro> cursoInvalidoHandler(CursoInvalidoException exception){
        criarLogWarn("Recurso inválido", exception);
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EstudanteInvalidoException.class)
    public ResponseEntity<RespostaErro> estudanteInvalidoHandler(EstudanteInvalidoException exception) {
        criarLogWarn("Recurso inválido", exception);
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProfessorInvalidoException.class)
    public ResponseEntity<RespostaErro> professorInvalidoHandler(ProfessorInvalidoException exception) {
        criarLogWarn("Recurso inválido", exception);
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AtribuicaoInvalidaException.class)
    public ResponseEntity<RespostaErro> professorJaAtribuidoHandler(AtribuicaoInvalidaException exception) {
        criarLogWarn("Recurso não permitido", exception);
        return criarResposta(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RespostaErro> criarResposta(String mensagem, HttpStatus status) {
        RespostaErro resposta = RespostaErro.builder()
                .mensagem(mensagem)
                .status(status.value())
                .build();
        return ResponseEntity.status(status).body(resposta);
    }

    private void criarLogWarn(String mensagem, Exception exception) {
        log.warn("{} : {}",mensagem, exception.getMessage());
    }
}
