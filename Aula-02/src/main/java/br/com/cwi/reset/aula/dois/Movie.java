package br.com.cwi.reset.aula.dois;

public class Movie {
    private String name;
    private String description;
    private Integer duration;
    private Integer releaseYear;
    private Integer rating;
    private Director director;

    public Movie(String name, String description, Integer duration, Integer releaseYear, Integer rating, Director director) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.director = director;
    }


    public void playMovie() throws RatingOutOfPatternException{
        if (rating <= 0 || rating < 5) {
            throw new RatingOutOfPatternException();
        }
        System.out.println("Movie - " + name);
        System.out.println("Description - " + description);
        System.out.println("Duration - " + duration + "min.");
        System.out.println("Director - " + director.getName());
        System.out.println("Rating - " + rating);

    }
}

