package br.com.cwi.reset.aula.dois;

public class App {

    public static void main(String[] args) {
        Director director = new Director("James Wan", 44, 5);
        Director director2 = new Director("A director", 32, 12);

        Movie saw = new Movie("Saw", "A great movie", 120, 2004, 5,director);
        Movie joker = new Movie("Joker", "A good movie", 140, 2018, 5,director2);

        saw.playMovie();
        joker.playMovie();
    }


}
