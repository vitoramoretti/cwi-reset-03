package br.com.cwi.reset.vitoramoretti.repository;

import br.com.cwi.reset.vitoramoretti.model.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonagemAtorRepository extends CrudRepository<PersonagemAtor, Integer> {

    PersonagemAtor findByNomePersonagem(String nomePersonagem);

    List<PersonagemAtor> findAll();
}
