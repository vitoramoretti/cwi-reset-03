package br.com.cwi.reset.vitoramoretti.repository;

import br.com.cwi.reset.vitoramoretti.model.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends CrudRepository<Filme, Integer> {

    Filme findByNome(String nome);

    List<Filme> findAll();

}
