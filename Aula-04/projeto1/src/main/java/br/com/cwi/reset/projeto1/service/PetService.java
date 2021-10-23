package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet save(Pet pet) throws PetJaExistenteException {
        Pet savedPet = petRepository.findByName(pet.getNome());

        if (savedPet != null) {
            throw new PetJaExistenteException("Pet com o nome " + pet.getNome() + " já existe");
        }
        return petRepository.save(pet);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findByName(String name) throws PetNaoExistenteException {
        Pet pet = petRepository.findByName(name);

        if (pet == null) {
            throw new PetNaoExistenteException("Pet com o nome " + name + " não existe");
        }
        return pet;
    }

    public void delete(String petName) throws PetNaoExistenteException {
        Pet pet = petRepository.findByName(petName);
        if (pet == null) {
            throw new PetNaoExistenteException("Pet com o nome " + petName + " não existe");
        }
        petRepository.delete(pet);
    }

    public Pet update(Pet pet) throws PetNaoExistenteException {
        Pet savedPet = petRepository.findByName(pet.getNome());
        if (savedPet == null) {
            throw new PetNaoExistenteException("Pet com o nome " + pet.getNome() + " não existe");
        }
        return petRepository.update(pet);
    }
}
