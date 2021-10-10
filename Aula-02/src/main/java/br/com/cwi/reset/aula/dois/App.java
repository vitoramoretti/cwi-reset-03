package br.com.cwi.reset.aula.dois;

public class App {

    public static void main(String[] args) {
        Director director = new Director("James Wan", 44, 5, Gender.MASCULINE);
//        Director director2 = new Director("A director", 32, 12, Gender.MASCULINE);

//        Actor actor = new Actor("Keanu Rieves", 55,0,Gender.MASCULINE);
//        Director director3 = new Director("Steven Spilberg", 67, 28, Gender.FEMININE);

        Movie movieTestOne = new Movie("Movie 1", "A great movie", 120, 2004, 5,director);
        Movie movieTestTwo = new Movie("Movie 2", "A good movie", 140, 2018,10 ,director);

        // Error handling
        try {
//            movieTestOne.playMovie();
            movieTestTwo.playMovie();
        } catch (RatingOutOfPatternException e) {
            System.out.println(e.getMessage());
        }


//        actor.printInfo();
//        director3.printInfo();
    }


}
