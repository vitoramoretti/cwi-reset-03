package br.com.cwi.reset.aula.dois;

public class Actor extends Person{

    private Integer oscarsWon;


    public Actor(String name, Integer age, Integer oscarsWon, Gender gender) {
        super(name, age, gender);
        this.oscarsWon = oscarsWon;
    }

}
