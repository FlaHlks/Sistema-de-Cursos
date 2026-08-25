package com.flahlks.cursos.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String messagem) {
        super(messagem);
    }
}
