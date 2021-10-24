package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.domain.Especie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepositoryDb extends CrudRepository<Pet, Integer> {
    Pet findByNome(String nome);

    List<Pet> findAll();

    List<Pet> findByIdade (Integer idade);

    List<Pet> findByEspecieNomeIgnoringCase (String nome);

}