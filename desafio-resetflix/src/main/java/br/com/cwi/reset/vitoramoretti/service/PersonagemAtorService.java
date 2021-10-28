package br.com.cwi.reset.vitoramoretti.service;

import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.exception.UniversalUseException;
import br.com.cwi.reset.vitoramoretti.model.Ator;
import br.com.cwi.reset.vitoramoretti.model.PersonagemAtor;
import br.com.cwi.reset.vitoramoretti.repository.AtorRepository;
import br.com.cwi.reset.vitoramoretti.repository.PersonagemAtorRepository;
import br.com.cwi.reset.vitoramoretti.request.PersonagemRequest;
import br.com.cwi.reset.vitoramoretti.validator.PersonagemRequestCamposObrigatoriosValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonagemAtorService {

    private PersonagemAtorRepository personagemAtorRepository;
    private AtorService atorService;

    @Autowired
    public PersonagemAtorService(PersonagemAtorRepository personagemAtorRepository) {
        this.personagemAtorRepository = personagemAtorRepository;
        //this.atorService = new AtorService(AtorRepository);
    }

    public PersonagemAtor cadastrarPersonagemAtor(PersonagemRequest personagemRequest) throws Exception {
        new PersonagemRequestCamposObrigatoriosValidator().accept(personagemRequest);


        final Ator ator = atorService.consultarAtor(personagemRequest.getIdAtor());

        final PersonagemAtor personagemAtor = new PersonagemAtor(ator, personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());

        personagemAtorRepository.save(personagemAtor);

        return personagemAtor;
    }

    public List<PersonagemAtor> consultarPersonagemAtor(String nome) throws Exception {
        return personagemAtorRepository.findAll();
    }

    private void validarPersonagensAtoresFilme(final List<PersonagemRequest> personagens) throws Exception {
        if (personagens.isEmpty()) {
            throw new UniversalUseException("Esta validação não estava nas regras.");
        }

        final Set<PersonagemRequest> personagemRequestSet = new HashSet<>();

        for (PersonagemRequest personagemRequest : personagens) {
            new PersonagemRequestCamposObrigatoriosValidator().accept(personagemRequest);

            // Implementação do Jose Alencar

            if (personagemRequestSet.contains(personagemRequest)) {
                throw new UniversalUseException("Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme.");
            } else {
                personagemRequestSet.add(personagemRequest);
            }
        }
    }

    public List<PersonagemAtor> cadastrarPersonagensFilme(final List<PersonagemRequest> personagens) throws Exception {
        validarPersonagensAtoresFilme(personagens);

        final List<PersonagemAtor> personagensAtores = new ArrayList<>();

        for (PersonagemRequest request : personagens) {
            personagensAtores.add(cadastrarPersonagemAtor(request));
        }

        return personagensAtores;
    }
}
