package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Pet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepositoryImpl implements PetRepository {

    private static List<Pet> pets = new ArrayList<>();

    public Pet findByName(String name) {
        for (Pet pet : pets) {
            if (pet.getNome().equals(name)) {
                return pet;
            }
        }
        return null;
    }

    public Pet save(Pet pet) {
        pets.add(pet);
        return pet;
    }

    public void delete(Pet pet) {
        pets.remove(pet);
    }

    public Pet update(Pet pet) {
        Pet savedPet = findByName(pet.getNome());

        if (savedPet != null) {
            pets.remove(savedPet);
            pets.add(pet);
            return pet;
        } else {
            return null;
        }
    }

    public List<Pet> findAll() {
        return pets;
    }
}