package com.flahlks.cursos.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespostaErro {
    private String mensagem;
    private Integer status;
}
