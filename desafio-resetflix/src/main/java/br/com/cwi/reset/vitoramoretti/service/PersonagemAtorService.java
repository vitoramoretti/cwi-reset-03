package br.com.cwi.reset.vitoramoretti.service;

import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.model.PersonagemAtor;
import br.com.cwi.reset.vitoramoretti.request.PersonagemRequest;

import java.util.List;

public class PersonagemAtorService {

    private FakeDatabase fakeDatabase;
    private AtorService atorService;

    public PersonagemAtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(fakeDatabase);
    }

    public List<PersonagemAtor> createPersonagensFilme(List<PersonagemRequest> personagens) {
        return null;
    }
}
