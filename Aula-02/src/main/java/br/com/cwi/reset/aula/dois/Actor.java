package br.com.cwi.reset.aula.dois;

public class Actor extends Person{
    private String name;
    private Integer age;
    private Integer oscarsWon;
    private Gender gender;

    public Actor(String name, Integer age, Integer oscarsWon, Gender gender) {
        super(name, age, gender);
        this.gender = gender;
    }

}
