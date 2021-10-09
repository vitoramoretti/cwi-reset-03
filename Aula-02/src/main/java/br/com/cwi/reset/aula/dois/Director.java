package br.com.cwi.reset.aula.dois;

public class Director extends Person {
    private String name;
    private Integer age;
    private Integer moviesDirected;
    private Gender gender;


    public Director(String name, Integer age, Integer moviesDirected, Gender gender) {
        super(name, age, gender);
        this.gender = gender;
    }


    public String getName() {
        return name;
    }
}
