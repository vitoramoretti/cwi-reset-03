package br.com.cwi.reset.aula.dois;

public class Director {
    private String name;
    private Integer age;
    private Integer moviesDirected;
    private Gender gender;


    public Director(String name, Integer age, Integer moviesDirected, Gender gender) {
        this.name = name;
        this.age = age;
        this.moviesDirected = moviesDirected;
        this.gender = gender;
    }

    public void printDirector() {
        System.out.println("Director - " + name);
        System.out.println("Age - " + age);
        System.out.println("Gender - " + gender);
    }

    public String getName() {
        return name;
    }
}
