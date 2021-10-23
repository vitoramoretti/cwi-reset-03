package br.com.cwi.reset.aula.dois;

public class RatingOutOfPatternException extends Exception {

    public RatingOutOfPatternException() {
        super("Your rating should be a number between 1 and 5.");
    }

}
