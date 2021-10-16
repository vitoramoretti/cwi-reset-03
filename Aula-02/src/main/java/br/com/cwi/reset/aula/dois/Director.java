package br.com.cwi.reset.aula.dois;

public class Director extends Person {

    private Integer moviesDirected;


    public Director(String name, Integer age, Integer moviesDirected, Gender gender) {
        super(name, age, gender);
        this.moviesDirected = moviesDirected;
    }

}
