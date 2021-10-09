package br.com.cwi.reset.aula.dois;

public class Actor {
    private String name;
    private Integer age;
    private Integer oscarsWon;
    private Gender gender;

    public Actor(String name, Integer age, Integer oscarsWon, Gender gender) {
        this.name = name;
        this.age = age;
        this.oscarsWon = oscarsWon;
        this.gender = gender;
    }

    public void printActor() {
        System.out.println("Actor - " + name);
        System.out.println("Age - " + age);
        System.out.println("Gender - " + gender.getDescription());
    }
}
