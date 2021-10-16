package br.com.cwi.reset.vitoramoretti.exception;

public class FiltroNomeNaoEncontrado extends Exception {

    public FiltroNomeNaoEncontrado(String tipo, String paramentro) {
        super(String.format("%s não encontrato com o filtro %s, favor informar outro filtro.", tipo, paramentro));
    }
}