package br.com.cwi.reset.aula.dois;

public enum Gender {
    MASCULINE("Masculine"),
    FEMININE("Feminine"),
    NON_BINARY("Non-Binary");

    private String description;

    Gender(String description){
        this.description = description;
    }
}
