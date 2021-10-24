package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Ator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtorRepositoryDb extends CrudRepository<Ator, Integer> {

    Ator findByNome(String nome);

    List<Ator> findAll();

    List<Ator> findByNumeroOscars (Integer numeroOscars);

}
