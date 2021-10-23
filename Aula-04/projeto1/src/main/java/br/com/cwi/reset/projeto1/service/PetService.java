package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetExistsException;
import br.com.cwi.reset.projeto1.exception.PetDoesNotExistException;
import br.com.cwi.reset.projeto1.repository.PetRepositoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepositoryDb petRepository;

    public Pet save(Pet pet) throws PetExistsException {
        Pet savedPet = petRepository.findByNome(pet.getNome());

        if (savedPet != null) {
            throw new PetExistsException("Pet com o nome " + pet.getNome() + " já existe");
        }
        return petRepository.save(pet);
    }

    public Pet findByName(String name) throws PetDoesNotExistException {
        Pet pet = petRepository.findByNome(name);

        if (pet == null) {
            throw new PetDoesNotExistException("Pet com o nome " + name + " não existe");
        }
        return pet;
    }

    public void delete(String petName) throws PetDoesNotExistException {
        Pet pet = petRepository.findByNome(petName);
        if (pet == null) {
            throw new PetDoesNotExistException("Pet com o nome " + petName + " não existe");
        }
        petRepository.delete(pet);
    }

    public Pet update(Pet pet) throws PetDoesNotExistException {
        Pet savedPet = petRepository.findByNome(pet.getNome());
        if (savedPet == null) {
            throw new PetDoesNotExistException("Pet com o nome " + pet.getNome() + " não existe");
        }
        return petRepository.save(pet);
    }

    public List<Pet> findByEspecieNomeIgnoringCase(String nomeEspecie) {
        return petRepository.findByEspecieNomeIgnoringCase(nomeEspecie);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

}
