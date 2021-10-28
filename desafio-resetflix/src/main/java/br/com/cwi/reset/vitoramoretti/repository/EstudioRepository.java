package br.com.cwi.reset.vitoramoretti.repository;

import br.com.cwi.reset.vitoramoretti.model.Estudio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudioRepository extends CrudRepository<Estudio, Integer> {

    Estudio findByNome(String nome);

    List<Estudio> findAll();

    List<Estudio> findEstudioById(Integer id);
}
