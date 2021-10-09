package br.com.cwi.reset.aula.dois;

public class App {

    public static void main(String[] args) {
//        Director director = new Director("James Wan", 44, 5, Gender.MASCULINE);
//        Director director2 = new Director("A director", 32, 12, Gender.MASCULINE);

        Actor actor = new Actor("Keanu Rieves", 55,0,Gender.MASCULINE);
        Director director3 = new Director("Steven Spilberg", 67, 28, Gender.FEMININE);

//        Movie saw = new Movie("Saw", "A great movie", 120, 2004, 5,director);
//        Movie joker = new Movie("Joker", "A good movie", 140, 2018, 5,director2);


        actor.printInfo();
        director3.printInfo();
    }


}
