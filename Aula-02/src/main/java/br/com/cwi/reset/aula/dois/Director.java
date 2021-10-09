package br.com.cwi.reset.aula.dois;

public class Director {
    private String name;
    private Integer age;
    private Integer moviesDirected;

    public Director(String name, Integer age, Integer moviesDirected) {
        this.name = name;
        this.age = age;
        this.moviesDirected = moviesDirected;
    }

    public String getName() {
        return name;
    }
}
