package br.com.cwi.reset.vitoramoretti.exception;

public class CadastroDuplicadoException extends Exception {
    public CadastroDuplicadoException(String tipo, String parametro) {
        super(String.format("Já existe um %s cadastrado para o nome %s.", tipo, parametro));
    }
}