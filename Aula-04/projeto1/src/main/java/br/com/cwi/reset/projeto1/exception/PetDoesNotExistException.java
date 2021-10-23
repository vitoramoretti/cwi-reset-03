package br.com.cwi.reset.projeto1.exception;



public class PetDoesNotExistException extends Exception {
    public PetDoesNotExistException(String message) {
        super(message);
    }
}
