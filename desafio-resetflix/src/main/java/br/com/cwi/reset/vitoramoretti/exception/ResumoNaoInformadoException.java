package br.com.cwi.reset.vitoramoretti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResumoNaoInformadoException extends CampoNaoInformadoException {
    public ResumoNaoInformadoException() {
        super("resumo");
    }
}
