package br.com.cwi.reset.aula.dois;

public class Person {

    private String name;
    private Integer age;
    private Gender gender;

    public Person(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void printInfo() {
        System.out.println("Name - " + name);
        System.out.println("Age - " + age);
        System.out.println("Gender - " + gender.getDescription());
    }
}
