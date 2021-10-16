package br.com.cwi.reset.vitoramoretti.exception;

public class ListaVaziaException extends Exception {
    public ListaVaziaException(String tipo, String tipoPlural) {
        super(String.format("Nenhum %s cadastrado, favor cadastar %s.", tipo, tipoPlural));
    }
}